package si2023.SergioGarciaMacias.p04;

import java.util.ArrayList;
import java.util.Random;

import tools.Utils;
import tracks.ArcadeMachine;

public class GameRunner {
	public static void main(String[] args) {

		int ejecuciones = 100;
		String p0 = "si2023.SergioGarciaMacias.p04.agente89.AgenteSuperInteligente";

		// Load available games
		String spGamesCollection = "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);

		// Game settings
		boolean visuals = true;

		int[] seed_to_play = { 1963797956, 2074061387, -1872155723, 1303888686, -81322281, -847850438, 157357791,
				2025477112, -1153726265, 262677882, -216340112, -1368116601, 1464790604, -1987832276, 1245635922,
				-1408398019, };

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
			int seed = seed_to_play[i];
			System.out.println(seed);
			ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);
		}

		System.out.println("\n\nRandom seeds !!\n\n"
				+ "=========================================================================");

		ArrayList<Integer> seed_perdedoras = new ArrayList<>();

		// 90% de WinRate en la seed 33
		Random rand = new Random(33);

		for (int i = 0; i < 100; i++) {
			// 2. This plays a game in a level by the controller.
			int seed = rand.nextInt();
			double[] cosos = ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);
			if (cosos[0] == 0.0) {
				seed_perdedoras.add(seed);
			}
		}

		System.out.println("\n\n\n");
		for (Integer seed : seed_perdedoras)
			System.out.println(seed + ",");

		System.out.println("WinRate: " + (ejecuciones - seed_perdedoras.size()) * 100 / ejecuciones);

		System.exit(0);

	}
}
