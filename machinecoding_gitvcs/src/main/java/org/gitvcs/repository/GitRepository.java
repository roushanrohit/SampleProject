package org.gitvcs.repository;

import org.gitvcs.exception.BranchAlreadyExistsException;
import org.gitvcs.exception.BranchNotFoundException;
import org.gitvcs.exception.UserNotRegisteredException;
import org.gitvcs.model.Branch;
import org.gitvcs.model.Commit;
import org.gitvcs.model.MergeResult;
import org.gitvcs.model.MergeStatus;
import org.gitvcs.model.User;
import org.gitvcs.model.Workspace;
import org.gitvcs.util.HashUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/*
    Single in-memory, non-distributed git-like repository shared by every registered user.
    There is no disk/network I/O and no server process - everything lives in the maps below
    for the lifetime of this object.

    Concurrency management:
    - Branches live in a ConcurrentHashMap and each Branch owns its own ReentrantLock, so
      commits/merges on different branches never block each other.
    - A commit only reads a branch's HEAD after acquiring that branch's lock, so two users
      committing to the same branch at the same time are serialized instead of racing on a
      stale parent (which would otherwise silently drop one of the commits).
    - A merge locks both participating branches, always in name-sorted order, so merging
      A into B on one thread and B into A on another can never deadlock.
 */
public class GitRepository {

    private static final String MAIN_BRANCH = "main";

    private final Map<String, Branch> branches = new ConcurrentHashMap<>();
    private final Map<String, Commit> commitStore = new ConcurrentHashMap<>();
    private final Map<String, Workspace> workspaces = new ConcurrentHashMap<>();

    public GitRepository() {
        branches.put(MAIN_BRANCH, new Branch(MAIN_BRANCH, null));
    }

    public void registerUser(User user) {
        workspaces.putIfAbsent(user.getId(), new Workspace(user.getId(), MAIN_BRANCH));
    }

    public void createBranch(User user, String newBranchName, String fromBranchName) {
        Branch source = getBranch(fromBranchName);
        Branch created = new Branch(newBranchName, source.getHead().get());
        if (branches.putIfAbsent(newBranchName, created) != null) {
            throw new BranchAlreadyExistsException(newBranchName);
        }
    }

    public void checkout(User user, String branchName) {
        getBranch(branchName); // validates the branch exists before switching to it
        getWorkspace(user).setCurrentBranch(branchName);
    }

    public void stageFile(User user, String path, String content) {
        getWorkspace(user).stageFile(path, content);
    }

    public void stageDeletion(User user, String path) {
        getWorkspace(user).stageDeletion(path);
    }

    // The materialized view of a user's working directory: current branch HEAD overlaid
    // with whatever they have staged but not committed yet.
    public Map<String, String> getWorkingFiles(User user) {
        Workspace workspace = getWorkspace(user);
        Branch branch = getBranch(workspace.getCurrentBranch());
        Commit head = branch.getHead().get();
        Map<String, String> files = head != null ? new HashMap<>(head.getSnapshot()) : new HashMap<>();
        applyStagedChanges(files, workspace.getStagedChanges());
        return files;
    }

    public Commit commit(User user, String message) {
        Workspace workspace = getWorkspace(user);
        Branch branch = getBranch(workspace.getCurrentBranch());

        branch.getLock().lock();
        try {
            Commit parent = branch.getHead().get();
            Map<String, String> snapshot = parent != null ? new HashMap<>(parent.getSnapshot()) : new HashMap<>();
            applyStagedChanges(snapshot, workspace.getStagedChanges());

            List<Commit> parents = parent != null ? List.of(parent) : List.of();
            String id = HashUtil.nextCommitId(user.getId() + message);
            Commit newCommit = new Commit(id, message, user, parents, snapshot, System.currentTimeMillis());

            commitStore.put(newCommit.getId(), newCommit);
            branch.getHead().set(newCommit);
            workspace.clearStagedChanges();
            return newCommit;
        } finally {
            branch.getLock().unlock();
        }
    }

    public MergeResult merge(User user, String sourceBranchName, String targetBranchName) {
        Branch source = getBranch(sourceBranchName);
        Branch target = getBranch(targetBranchName);

        Branch first = source.getName().compareTo(target.getName()) <= 0 ? source : target;
        Branch second = first == source ? target : source;

        first.getLock().lock();
        try {
            second.getLock().lock();
            try {
                return doMerge(user, source, target);
            } finally {
                second.getLock().unlock();
            }
        } finally {
            first.getLock().unlock();
        }
    }

    public List<Commit> log(String branchName) {
        Branch branch = getBranch(branchName);
        List<Commit> history = new ArrayList<>();
        Commit current = branch.getHead().get();
        while (current != null) {
            history.add(current);
            // first-parent history: on a merge commit this follows the branch that received the merge
            current = current.getParents().isEmpty() ? null : current.getParents().get(0);
        }
        return history;
    }

    private MergeResult doMerge(User user, Branch source, Branch target) {
        Commit sourceHead = source.getHead().get();
        Commit targetHead = target.getHead().get();

        if (sourceHead == null || Objects.equals(sourceHead, targetHead)) {
            return new MergeResult(MergeStatus.ALREADY_UP_TO_DATE,
                    targetHead != null ? targetHead.getId() : null, List.of());
        }

        if (targetHead == null || ancestryOf(sourceHead).contains(targetHead.getId())) {
            // target has no commits yet, or its whole history is already part of source
            target.getHead().set(sourceHead);
            return new MergeResult(MergeStatus.FAST_FORWARD, sourceHead.getId(), List.of());
        }

        if (ancestryOf(targetHead).contains(sourceHead.getId())) {
            // source's history is already fully contained in target - nothing to bring in
            return new MergeResult(MergeStatus.ALREADY_UP_TO_DATE, targetHead.getId(), List.of());
        }

        Commit base = findCommonAncestor(sourceHead, targetHead);
        Map<String, String> baseFiles = base != null ? base.getSnapshot() : Map.of();
        Map<String, String> oursFiles = targetHead.getSnapshot();
        Map<String, String> theirsFiles = sourceHead.getSnapshot();

        Set<String> allPaths = new HashSet<>();
        allPaths.addAll(oursFiles.keySet());
        allPaths.addAll(theirsFiles.keySet());

        Map<String, String> merged = new HashMap<>();
        List<String> conflicts = new ArrayList<>();

        for (String path : allPaths) {
            String baseContent = baseFiles.get(path);
            String oursContent = oursFiles.get(path);
            String theirsContent = theirsFiles.get(path);

            if (Objects.equals(oursContent, theirsContent)) {
                putIfPresent(merged, path, oursContent);
            } else if (Objects.equals(oursContent, baseContent)) {
                // only theirs changed this file
                putIfPresent(merged, path, theirsContent);
            } else if (Objects.equals(theirsContent, baseContent)) {
                // only ours changed this file
                putIfPresent(merged, path, oursContent);
            } else {
                conflicts.add(path);
            }
        }

        if (!conflicts.isEmpty()) {
            Collections.sort(conflicts);
            return new MergeResult(MergeStatus.CONFLICT, null, conflicts);
        }

        String id = HashUtil.nextCommitId(user.getId() + "merge" + sourceHead.getId() + targetHead.getId());
        Commit mergeCommit = new Commit(id, "Merge " + source.getName() + " into " + target.getName(),
                user, List.of(targetHead, sourceHead), merged, System.currentTimeMillis());

        commitStore.put(mergeCommit.getId(), mergeCommit);
        target.getHead().set(mergeCommit);
        return new MergeResult(MergeStatus.MERGED, mergeCommit.getId(), List.of());
    }

    private void putIfPresent(Map<String, String> target, String path, String content) {
        if (content != null) {
            target.put(path, content);
        }
    }

    private Set<String> ancestryOf(Commit commit) {
        Set<String> visited = new HashSet<>();
        if (commit == null) {
            return visited;
        }
        Deque<Commit> queue = new ArrayDeque<>();
        queue.add(commit);
        while (!queue.isEmpty()) {
            Commit current = queue.poll();
            if (visited.add(current.getId())) {
                queue.addAll(current.getParents());
            }
        }
        return visited;
    }

    private Commit findCommonAncestor(Commit a, Commit b) {
        Set<String> ancestorsOfA = ancestryOf(a);
        Set<String> visited = new HashSet<>();
        Deque<Commit> queue = new ArrayDeque<>();
        queue.add(b);
        while (!queue.isEmpty()) {
            Commit current = queue.poll();
            if (!visited.add(current.getId())) {
                continue;
            }
            if (ancestorsOfA.contains(current.getId())) {
                return current;
            }
            queue.addAll(current.getParents());
        }
        return null;
    }

    private void applyStagedChanges(Map<String, String> files, Map<String, Optional<String>> staged) {
        for (Map.Entry<String, Optional<String>> entry : staged.entrySet()) {
            if (entry.getValue().isPresent()) {
                files.put(entry.getKey(), entry.getValue().get());
            } else {
                files.remove(entry.getKey());
            }
        }
    }

    private Branch getBranch(String name) {
        Branch branch = branches.get(name);
        if (branch == null) {
            throw new BranchNotFoundException(name);
        }
        return branch;
    }

    private Workspace getWorkspace(User user) {
        Workspace workspace = workspaces.get(user.getId());
        if (workspace == null) {
            throw new UserNotRegisteredException(user.getId());
        }
        return workspace;
    }
}
