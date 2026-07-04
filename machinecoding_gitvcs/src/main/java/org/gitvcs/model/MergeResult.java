package org.gitvcs.model;

import java.util.Collections;
import java.util.List;

public class MergeResult {

    private final MergeStatus status;
    private final String resultingCommitId;
    private final List<String> conflictedFiles;

    public MergeResult(MergeStatus status, String resultingCommitId, List<String> conflictedFiles) {
        this.status = status;
        this.resultingCommitId = resultingCommitId;
        this.conflictedFiles = Collections.unmodifiableList(conflictedFiles);
    }

    public MergeStatus getStatus() {
        return status;
    }

    public String getResultingCommitId() {
        return resultingCommitId;
    }

    public List<String> getConflictedFiles() {
        return conflictedFiles;
    }
}
