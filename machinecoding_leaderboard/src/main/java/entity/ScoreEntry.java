package entity;

import java.util.Objects;

public class ScoreEntry {

    public String userId;
    public int score;
    public long timestamp;

    public ScoreEntry(String userId, int score, long timestamp) {
        this.userId = userId;
        this.score = score;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreEntry that)) return false;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "ScoreEntry{" +
                "userId='" + userId + '\'' +
                ", score=" + score +
                ", timestamp=" + timestamp +
                '}';
    }
}
