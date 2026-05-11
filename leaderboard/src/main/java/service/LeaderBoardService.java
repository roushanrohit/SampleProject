package service;

import entity.HighScoreRankingStrategy;
import entity.RankingInterface;
import entity.ScoreEntry;
import entity.LeaderBoard;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LeaderBoardService {

    // lookup by gameId -- final prevents accidental reassignment
    private final Map<String, List<LeaderBoard>> gameLeaderBoards = new ConcurrentHashMap<>();
    // lookup by leaderboardId -- final prevents accidental reassignment
    private final Map<String, LeaderBoard> LeaderBoardMap = new ConcurrentHashMap<>();

    public void registerGame(String gameId) {
        gameLeaderBoards.putIfAbsent(gameId, new CopyOnWriteArrayList<>());
    }

    public Set<String> getSupportedGames() {
        return gameLeaderBoards.keySet();
    }

    public String createLeaderBoard(String gameId, long start, long end, RankingInterface strategy) {
        if (!gameLeaderBoards.containsKey(gameId)) {
            throw new IllegalArgumentException("Game not found");
        }

        // probability of collision is extremely low because of 122 bits of randomness
        String LeaderBoardId = UUID.randomUUID().toString();
        LeaderBoard lb = new LeaderBoard(LeaderBoardId, gameId, start, end, strategy);
        LeaderBoardMap.put(LeaderBoardId, lb);
        gameLeaderBoards.get(gameId).add(lb);
        return LeaderBoardId;
    }

    // overloaded for default ranking strategy
    public String createLeaderBoard(String gameId, long start, long end) {
        if (!gameLeaderBoards.containsKey(gameId)) {
            throw new IllegalArgumentException("Game not found");
        }

        // probability of collision is extremely low because of 122 bits of randomness
        String LeaderBoardId = UUID.randomUUID().toString();
        LeaderBoard lb = new LeaderBoard(LeaderBoardId, gameId, start, end, new HighScoreRankingStrategy());
        LeaderBoardMap.put(LeaderBoardId, lb);
        gameLeaderBoards.get(gameId).add(lb);
        return LeaderBoardId;
    }

    public void submitScore(String gameId, String userId, int score) {
        if (!gameLeaderBoards.containsKey(gameId)) {
            throw new IllegalArgumentException("Game not found");
        }
        long now = System.currentTimeMillis() / 1000;
        for (LeaderBoard lb : gameLeaderBoards.get(gameId)) {
            if (lb.isActive(now)) {
                lb.submitScore(userId, score);
            }
        }
    }

    public List<ScoreEntry> getLeaderBoard(String LeaderBoardId) {
        LeaderBoard lb = LeaderBoardMap.get(LeaderBoardId);
        if (lb == null) throw new IllegalArgumentException("LeaderBoard not found");
        return lb.getAll();
    }

    public List<ScoreEntry> getTopPlayers(String LeaderBoardId, int n) {
        LeaderBoard lb = LeaderBoardMap.get(LeaderBoardId);
        if (lb == null) throw new IllegalArgumentException("LeaderBoard not found");
        return lb.getTopN(n);
    }

    public List<ScoreEntry> listPlayersAround(String LeaderBoardId, String userId, int n) {
        LeaderBoard lb = LeaderBoardMap.get(LeaderBoardId);
        if (lb == null) throw new IllegalArgumentException("LeaderBoard not found");
        return lb.getAroundUser(userId, n);
    }
}