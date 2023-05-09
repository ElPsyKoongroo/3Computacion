package si2023.sergiogarcia1alu.p05;

import tools.Utils;
import tracks.ArcadeMachine;

public class GameRunner {
    public static void main(String[] args) {

//        int ejecuciones = 100;
        String p0 = "si2023.sergiogarcia1alu.p05.AgenteSuperInteligente";

        // Load available games
        String spGamesCollection = "examples/all_games_sp.csv";
        String[][] games = Utils.readGames(spGamesCollection);

        int gameIdx = 4;
        int levelIdx = 0;

        String gameName = games[gameIdx][1];
        String game = games[gameIdx][0];
        String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);
        // Game settings
        boolean visuals = true;

        //ArcadeMachine.playOneGame(game, level1, null, 33);
        ArcadeMachine.runOneGame(game, level1, visuals, p0, null, 2, 0);
        // Game and level to play

//        int levelIdx = 4; // level names from 0 to 4 (game_lvlN.txt).

//        for (int i = 4; i < 5; i++) {
//            i = 0;
//
//            String level = game.replace(gameName, gameName + "_lvl" + i);
//            // 1. This starts a game, in a level, played by a human.
//            //ArcadeMachine.playOneGame(game, level1, null, 33);
//            ArcadeMachine.runOneGame(game, level1, visuals, p0, null, 2, 0);
//        }

//		for (int i = 0; i < seed_to_play.length; i++) {
//			// 2. This plays a game in a level by the controller.
//			int seed = seed_to_play[i];
//			System.out.println(seed);
//			ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);
//		}
//
//		System.out.println("\n\nRandom seeds !!\n\n"
//				+ "=========================================================================");
//
//		ArrayList<Integer> seed_perdedoras = new ArrayList<>();
//
//		// 90% de WinRate en la seed 33
//		Random rand = new Random();
//
//		for (int i = 0; i < 100; i++) {
//			// 2. This plays a game in a level by the controller.
//			int seed = rand.nextInt();
//			double[] cosos = ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);
//			if (cosos[0] == 0.0) {
//				seed_perdedoras.add(seed);
//			}
//		}
//
//		System.out.println("\n\n\n");
//		for (Integer seed : seed_perdedoras)
//			System.out.println(seed + ",");
//
//		System.out.println("WinRate: " + (ejecuciones - seed_perdedoras.size()) * 100 / ejecuciones);

        System.exit(0);

    }
}
