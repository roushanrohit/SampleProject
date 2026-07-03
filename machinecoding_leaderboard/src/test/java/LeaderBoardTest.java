import entity.HighScoreRankingStrategy;
import entity.LeaderBoard;
import entity.ScoreEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    private LeaderBoard leaderboard;

    @BeforeEach
    void setup() {
        long now = System.currentTimeMillis() / 1000;
        leaderboard = new LeaderBoard("lb1", "game1", now - 10,
                now + 1000, new HighScoreRankingStrategy());
    }

    @Test
    void testSubmitScore() {
        leaderboard.submitScore("user1", 100);
        leaderboard.submitScore("user2", 200);

        List<ScoreEntry> all = leaderboard.getAll();

        assertEquals("user2", all.get(0).userId);
        assertEquals("user1", all.get(1).userId);
    }

    @Test
    void testUpdateScore() {
        leaderboard.submitScore("user1", 100);
        leaderboard.submitScore("user1", 150);

        List<ScoreEntry> all = leaderboard.getAll();

        assertEquals(150, all.get(0).score);
    }

    @Test
    void testIgnoreLowerScore() {
        leaderboard.submitScore("user1", 100);
        leaderboard.submitScore("user1", 50);

        List<ScoreEntry> all = leaderboard.getAll();

        assertEquals(100, all.get(0).score);
    }

    @Test
    void testGetTopN() {
        leaderboard.submitScore("user1", 100);
        leaderboard.submitScore("user2", 200);
        leaderboard.submitScore("user3", 150);

        List<ScoreEntry> top2 = leaderboard.getTopN(2);

        assertEquals(2, top2.size());
        assertEquals("user2", top2.get(0).userId);
        assertEquals("user3", top2.get(1).userId);
    }

    @Test
    void testGetAroundUser() {
        leaderboard.submitScore("user1", 100);
        leaderboard.submitScore("user2", 200);
        leaderboard.submitScore("user3", 150);

        List<ScoreEntry> around = leaderboard.getAroundUser("user3", 1);

        assertEquals(3, around.size());
    }
}