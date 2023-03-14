package si2023.SergioGarciaMacias.p03;

import java.util.ArrayList;
import java.util.Random;

import tools.Utils;
import tracks.ArcadeMachine;

public class GameRunner {
	public static void main(String[] args) {

		int ejecuciones = 50;
		String p0 = "si2023.SergioGarciaMacias.p03.agente89.AgenteSuperInteligente";

		// Load available games
		String spGamesCollection = "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);

		// Game settings
		boolean visuals = false;

		int[] seed_to_play = { 1130732993, -1624850195, 1662558412, -1128433611, 1436601074, 667203964,
				-1088813805, -738128400, -1232769431 };

		// Game and level to play
		int gameIdx = 89;
		int levelIdx = 4; // level names from 0 to 4 (game_lvlN.txt).

		String gameName = games[gameIdx][1];
		String game = games[gameIdx][0];
		String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);

		// 1. This starts a game, in a level, played by a human.
		// ArcadeMachine.playOneGame(game, level1, null, seed);

		for (int i = 0; i < seed_to_play.length; i++) {
			// 2. This plays a game in a level by the controller.
			int seed = seed_to_play[i]; // new Random().nextInt();
			System.out.println(seed);
			// ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);
		}

		
		System.out.println("\n\nRandom seeds !!\n\n"
				+ "=========================================================================");

		ArrayList<Integer> seed_perdedoras = new ArrayList<>();

		for (int i = 0; i < ejecuciones; i++) {
			// 2. This plays a game in a level by the controller.
			int seed = new Random().nextInt();
			double[] cosos = ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);
			if (cosos[0] == 0.0) {
				seed_perdedoras.add(seed);
			}
		}

		System.out.println("\n\n\n");
		for (Integer seed : seed_perdedoras)
			System.out.println(seed + ",");

		System.exit(0);

	}
}
