import entity.HighScoreRankingStrategy;
import entity.LowScoreRankingStrategy;
import entity.ScoreEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.LeaderBoardService;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardServiceTest {

    private LeaderBoardService service;
    private String gameId;
    private String leaderboardId;

    @BeforeEach
    void setup() {
        service = new LeaderBoardService();
        gameId = "game1";

        service.registerGame(gameId);

        long now = System.currentTimeMillis() / 1000;
        leaderboardId = service.createLeaderBoard(gameId, now - 10, now + 1000, new HighScoreRankingStrategy());
    }

    @Test
    void testRegisterGame() {
        Set<String> games = service.getSupportedGames();
        assertTrue(games.contains(gameId));
    }

    @Test
    void testCreateLeaderboard() {
        assertNotNull(leaderboardId);
    }

    @Test
    void testSubmitScoreAndRanking() {
        service.submitScore(gameId, "user1", 100);
        service.submitScore(gameId, "user2", 200);
        service.submitScore(gameId, "user3", 150);

        List<ScoreEntry> leaderboard = service.getLeaderBoard(leaderboardId);

        assertEquals("user2", leaderboard.get(0).userId);
        assertEquals("user3", leaderboard.get(1).userId);
        assertEquals("user1", leaderboard.get(2).userId);
    }

    @Test
    void testUpdateScoreOnlyIfHigher() {
        service.submitScore(gameId, "user1", 100);
        service.submitScore(gameId, "user1", 90); // should be ignored

        List<ScoreEntry> leaderboard = service.getLeaderBoard(leaderboardId);

        assertEquals(100, leaderboard.get(0).score);
    }

    @Test
    void testTopPlayers() {
        service.submitScore(gameId, "user1", 100);
        service.submitScore(gameId, "user2", 200);
        service.submitScore(gameId, "user3", 150);

        List<ScoreEntry> top2 = service.getTopPlayers(leaderboardId, 2);

        assertEquals(2, top2.size());
        assertEquals("user2", top2.get(0).userId);
        assertEquals("user3", top2.get(1).userId);
    }

    @Test
    void testPlayersAroundUser() {
        service.submitScore(gameId, "user1", 100);
        service.submitScore(gameId, "user2", 200);
        service.submitScore(gameId, "user3", 150);
        service.submitScore(gameId, "user4", 120);

        List<ScoreEntry> around = service.listPlayersAround(leaderboardId, "user3", 1);

        assertEquals(3, around.size());
        assertEquals("user2", around.get(0).userId);
        assertEquals("user3", around.get(1).userId);
        assertEquals("user4", around.get(2).userId);
    }

    @Test
    void testLeaderboardNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> service.getLeaderBoard("invalid-id"));
    }

    @Test
    void testGameNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> service.submitScore("invalid-game", "user1", 100));
    }
}