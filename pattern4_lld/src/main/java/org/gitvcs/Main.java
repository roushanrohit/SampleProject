package org.gitvcs;

import org.gitvcs.model.Commit;
import org.gitvcs.model.MergeResult;
import org.gitvcs.model.User;
import org.gitvcs.repository.GitRepository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
    Design and implement an in-memory, non-distributed version control system (git-like)
    that supports multiple users working concurrently.

    1. Multiple users can be registered, each with their own working directory (workspace).
    2. Users can create branches, checkout branches, stage file changes and commit them.
    3. Branches can be merged: fast-forward where possible, three-way merge with conflict
       detection otherwise.
    4. All state lives in memory only - no disk/network I/O, no central server process.
    5. Concurrent commits/merges from different users must not corrupt repository state
       or silently lose commits.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        GitRepository repo = new GitRepository();

        User alice = new User("u1", "Alice");
        User bob = new User("u2", "Bob");
        repo.registerUser(alice);
        repo.registerUser(bob);

        // Alice sets up main
        repo.stageFile(alice, "README.md", "v1");
        repo.commit(alice, "Initial commit");

        // Bob branches off to work on a feature, touching a different file
        repo.createBranch(bob, "feature/login", "main");
        repo.checkout(bob, "feature/login");
        repo.stageFile(bob, "login.txt", "login page");
        repo.commit(bob, "Add login page");

        // Alice keeps working on main in parallel
        repo.stageFile(alice, "README.md", "v2");
        repo.commit(alice, "Update README");

        // Clean merge: no overlapping files between the two branches
        MergeResult cleanMerge = repo.merge(alice, "feature/login", "main");
        System.out.println("Merge status: " + cleanMerge.getStatus());

        System.out.println("main history:");
        for (Commit commit : repo.log("main")) {
            System.out.println("  " + commit);
        }

        // Demonstrate conflict detection: same file edited differently on both branches
        repo.createBranch(alice, "feature/readme-tweak", "main");
        repo.checkout(alice, "feature/readme-tweak");
        repo.stageFile(alice, "README.md", "v3-from-branch");
        repo.commit(alice, "Tweak README on branch");

        repo.checkout(bob, "main");
        repo.stageFile(bob, "README.md", "v3-from-main");
        repo.commit(bob, "Tweak README on main");

        MergeResult conflictResult = repo.merge(bob, "feature/readme-tweak", "main");
        System.out.println("Conflicting merge status: " + conflictResult.getStatus()
                + ", conflicts: " + conflictResult.getConflictedFiles());

        // Demonstrate concurrency management: many users committing to the same
        // branch at the same time should not corrupt state or lose any commit.
        repo.checkout(alice, "main");
        int concurrentUsers = 10;
        ExecutorService executor = Executors.newFixedThreadPool(concurrentUsers);
        CountDownLatch latch = new CountDownLatch(concurrentUsers);
        int commitsBefore = repo.log("main").size();

        for (int i = 0; i < concurrentUsers; i++) {
            int idx = i;
            executor.submit(() -> {
                User user = new User("concurrent-" + idx, "User" + idx);
                repo.registerUser(user);
                repo.checkout(user, "main");
                repo.stageFile(user, "file-" + idx + ".txt", "content-" + idx);
                repo.commit(user, "Concurrent commit " + idx);
                latch.countDown();
            });
        }

        latch.await(10, TimeUnit.SECONDS);
        executor.shutdown();

        int commitsAfter = repo.log("main").size();
        System.out.println("Commits on main before: " + commitsBefore + ", after "
                + concurrentUsers + " concurrent commits: " + commitsAfter);
    }
}
