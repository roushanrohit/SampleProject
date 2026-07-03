package entity;

public class LowScoreRankingStrategy implements RankingInterface {

    @Override
    public int compare(ScoreEntry a, ScoreEntry b) {
        if (a.score != b.score) {
            return Integer.compare(a.score, b.score); // ASC
        }
        return a.userId.compareTo(b.userId);
    }
}
