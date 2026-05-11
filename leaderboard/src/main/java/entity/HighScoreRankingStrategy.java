package entity;

public class HighScoreRankingStrategy implements RankingInterface {

    @Override
    public int compare(ScoreEntry a, ScoreEntry b) {
        if (a.score != b.score) {
            return Integer.compare(b.score, a.score); // DESC
        }
        return a.userId.compareTo(b.userId);
    }
}
