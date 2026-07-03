package entity;

public class TimeBasedRankingStrategy implements RankingInterface {

    @Override
    public int compare(ScoreEntry a, ScoreEntry b) {
        return Long.compare(a.timestamp, b.timestamp);
    }
}
