package org.gitvcs;

import org.gitvcs.exception.BranchAlreadyExistsException;
import org.gitvcs.exception.BranchNotFoundException;
import org.gitvcs.model.Commit;
import org.gitvcs.model.MergeResult;
import org.gitvcs.model.MergeStatus;
import org.gitvcs.model.User;
import org.gitvcs.repository.GitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class GitRepositoryTest {

    private GitRepository repo;
    private User alice;
    private User bob;

    @BeforeEach
    void setup() {
        repo = new GitRepository();
        alice = new User("u1", "Alice");
        bob = new User("u2", "Bob");
        repo.registerUser(alice);
        repo.registerUser(bob);
    }

    @Test
    void testCommitOnMain() {
        repo.stageFile(alice, "README.md", "v1");
        Commit commit = repo.commit(alice, "Initial commit");

        assertEquals(1, repo.log("main").size());
        assertEquals("v1", repo.getWorkingFiles(alice).get("README.md"));
        assertFalse(commit.isMergeCommit());
    }

    @Test
    void testCreateBranchInheritsSourceHead() {
        repo.stageFile(alice, "README.md", "v1");
        repo.commit(alice, "Initial commit");

        repo.createBranch(bob, "feature", "main");
        repo.checkout(bob, "feature");

        assertEquals("v1", repo.getWorkingFiles(bob).get("README.md"));
    }

    @Test
    void testDuplicateBranchNameThrows() {
        repo.createBranch(alice, "feature", "main");
        assertThrows(BranchAlreadyExistsException.class,
                () -> repo.createBranch(bob, "feature", "main"));
    }

    @Test
    void testCheckoutUnknownBranchThrows() {
        assertThrows(BranchNotFoundException.class, () -> repo.checkout(alice, "does-not-exist"));
    }

    @Test
    void testFastForwardMerge() {
        repo.stageFile(alice, "README.md", "v1");
        repo.commit(alice, "Initial commit");

        repo.createBranch(bob, "feature", "main");
        repo.checkout(bob, "feature");
        repo.stageFile(bob, "login.txt", "login page");
        repo.commit(bob, "Add login page");

        MergeResult result = repo.merge(alice, "feature", "main");

        assertEquals(MergeStatus.FAST_FORWARD, result.getStatus());
        assertEquals(2, repo.log("main").size());
    }

    @Test
    void testThreeWayMergeWithoutConflict() {
        repo.stageFile(alice, "README.md", "v1");
        repo.commit(alice, "Initial commit");

        repo.createBranch(bob, "feature", "main");
        repo.checkout(bob, "feature");
        repo.stageFile(bob, "login.txt", "login page");
        repo.commit(bob, "Add login page");

        // main moves forward too, so this can no longer fast-forward
        repo.stageFile(alice, "README.md", "v2");
        repo.commit(alice, "Update README");

        MergeResult result = repo.merge(alice, "feature", "main");

        assertEquals(MergeStatus.MERGED, result.getStatus());
        Map<String, String> mainFiles = repo.getWorkingFiles(alice);
        assertEquals("v2", mainFiles.get("README.md"));
        assertEquals("login page", mainFiles.get("login.txt"));
    }

    @Test
    void testMergeConflictLeavesTargetUntouched() {
        repo.stageFile(alice, "README.md", "v1");
        repo.commit(alice, "Initial commit");

        repo.createBranch(alice, "feature", "main");
        repo.checkout(alice, "feature");
        repo.stageFile(alice, "README.md", "from-branch");
        repo.commit(alice, "Tweak on branch");

        repo.checkout(bob, "main");
        repo.stageFile(bob, "README.md", "from-main");
        repo.commit(bob, "Tweak on main");

        int commitsBefore = repo.log("main").size();
        MergeResult result = repo.merge(bob, "feature", "main");

        assertEquals(MergeStatus.CONFLICT, result.getStatus());
        assertEquals(List.of("README.md"), result.getConflictedFiles());
        assertEquals(commitsBefore, repo.log("main").size());
    }

    @Test
    void testConcurrentCommitsToSameBranchAreAllPreserved() throws InterruptedException {
        repo.stageFile(alice, "README.md", "v1");
        repo.commit(alice, "Initial commit");
        int commitsBefore = repo.log("main").size();

        int concurrentUsers = 20;
        ExecutorService executor = Executors.newFixedThreadPool(concurrentUsers);
        CountDownLatch latch = new CountDownLatch(concurrentUsers);

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

        assertTrue(latch.await(10, TimeUnit.SECONDS));
        executor.shutdown();

        assertEquals(commitsBefore + concurrentUsers, repo.log("main").size());
    }
}
