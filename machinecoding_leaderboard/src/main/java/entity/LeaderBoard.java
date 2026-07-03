package entity;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class LeaderBoard {

    private String leaderboardId;
    private String gameId;
    private long startTime;
    private long endTime;

    private final RankingInterface strategy;

    Map<String, ScoreEntry> userScoreMap = new HashMap<>();
    TreeSet<ScoreEntry> rankingSet = null;

    ReentrantLock lock = new ReentrantLock();

    public LeaderBoard(String leaderboardId, String gameId, long startTime, long endTime, RankingInterface strategy) {
        this.leaderboardId = leaderboardId;
        this.gameId = gameId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.strategy = strategy;
        rankingSet = new TreeSet<>(strategy::compare);
    }

    public boolean isActive(long currentTime) {
        return currentTime >= startTime && currentTime <= endTime;
    }

    public void submitScore(String userId, int score) {
        lock.lock();
        try {
            ScoreEntry existing = userScoreMap.get(userId);
            ScoreEntry newEntry = new ScoreEntry(userId, score, System.currentTimeMillis());

            if (existing != null) {
                // Decide using strategy
                if (strategy.compare(newEntry, existing) >= 0) {
                    // new is worse or equal → ignore
                    return;
                }
                rankingSet.remove(existing);
            }
            userScoreMap.put(userId, newEntry);
            rankingSet.add(newEntry);
        } finally {
            lock.unlock();
        }
    }

    public List<ScoreEntry> getTopN(int n) {
        List<ScoreEntry> result = new ArrayList<>();
        int count = 0;
        for (ScoreEntry entry : rankingSet) {
            if (count++ >= n) break;
            result.add(entry);
        }
        return result;
    }

    public List<ScoreEntry> getAll() {
        return new ArrayList<>(rankingSet);
    }

    public List<ScoreEntry> getAroundUser(String userId, int n) {
        List<ScoreEntry> list = new ArrayList<>(rankingSet);

        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).userId.equals(userId)) {
                index = i;
                break;
            }
        }

        // userId not present in the leaderboard
        if (index == -1) return Collections.emptyList();

        int start = Math.max(0, index - n);
        int end = Math.min(list.size(), index + n + 1);

        return list.subList(start, end);
    }
}
