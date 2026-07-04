package org.gitvcs.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/*
    Immutable snapshot of every file in the repository at the time it was made.
    Regular commits have a single parent; merge commits have two.
 */
public class Commit {

    private final String id;
    private final String message;
    private final User author;
    private final List<Commit> parents;
    private final Map<String, String> snapshot;
    private final long timestamp;

    public Commit(String id, String message, User author, List<Commit> parents,
                   Map<String, String> snapshot, long timestamp) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.parents = Collections.unmodifiableList(parents);
        this.snapshot = Collections.unmodifiableMap(snapshot);
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }

    public List<Commit> getParents() {
        return parents;
    }

    public Map<String, String> getSnapshot() {
        return snapshot;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isMergeCommit() {
        return parents.size() > 1;
    }

    @Override
    public String toString() {
        return id + " - " + message + " (" + author + ")";
    }
}
