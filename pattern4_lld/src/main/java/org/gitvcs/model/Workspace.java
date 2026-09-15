package org.gitvcs.model;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/*
    A single user's working directory: which branch they currently have checked out,
    plus whatever edits they have staged but not yet committed. Optional.empty() marks
    a staged deletion so a ConcurrentHashMap (which rejects null values) can still be used.
 */
public class Workspace {

    private final String userId;
    private volatile String currentBranch;
    private final Map<String, Optional<String>> stagedChanges = new ConcurrentHashMap<>();

    public Workspace(String userId, String currentBranch) {
        this.userId = userId;
        this.currentBranch = currentBranch;
    }

    public String getUserId() {
        return userId;
    }

    public String getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(String currentBranch) {
        this.currentBranch = currentBranch;
        this.stagedChanges.clear();
    }

    public void stageFile(String path, String content) {
        stagedChanges.put(path, Optional.of(content));
    }

    public void stageDeletion(String path) {
        stagedChanges.put(path, Optional.empty());
    }

    public Map<String, Optional<String>> getStagedChanges() {
        return stagedChanges;
    }

    public void clearStagedChanges() {
        stagedChanges.clear();
    }
}
