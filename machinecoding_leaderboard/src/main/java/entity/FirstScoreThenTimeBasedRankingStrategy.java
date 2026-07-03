package entity;

public class FirstScoreThenTimeBasedRankingStrategy implements RankingInterface {

    @Override
    public int compare(ScoreEntry a, ScoreEntry b) {
        if (a.score != b.score) {
            return Integer.compare(b.score, a.score);
        }
        return Long.compare(a.timestamp, b.timestamp);
    }
}
