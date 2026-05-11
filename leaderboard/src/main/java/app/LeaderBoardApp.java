package app;

import entity.LowScoreRankingStrategy;
import entity.RankingInterface;
import service.LeaderBoardService;

public class LeaderBoardApp {

    public static void main(String[] args) throws InterruptedException {

        LeaderBoardService service = new LeaderBoardService();

        String gameId = "game1";
        service.registerGame(gameId);

        long now = System.currentTimeMillis() / 1000;

        RankingInterface strategy = new LowScoreRankingStrategy();
        String lbId = service.createLeaderBoard(gameId, now - 10, now + 1);
        String lbId2 = service.createLeaderBoard(gameId, now - 10, now + 3, strategy);

        service.submitScore(gameId, "user1", 100);
        service.submitScore(gameId, "user2", 200);
        service.submitScore(gameId, "user3", 150);
        service.submitScore(gameId, "user1", 250); // update

        //Thread.sleep(1800);

        service.submitScore(gameId, "user3", 300);

        System.out.println("=== Full Leaderboard ===");
        System.out.println(service.getLeaderBoard(lbId));
        System.out.println(service.getLeaderBoard(lbId2));

        System.out.println("=== Top 2 ===");
        System.out.println(service.getTopPlayers(lbId, 2));
        System.out.println(service.getTopPlayers(lbId2, 2));

        System.out.println("=== Around user3 ===");
        System.out.println(service.listPlayersAround(lbId, "user1", 1));
    }
}
