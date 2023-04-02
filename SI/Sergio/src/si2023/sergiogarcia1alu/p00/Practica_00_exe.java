package si2023.sergiogarcia1alu.p00;

import java.util.Random;

import tools.Utils;
import tracks.ArcadeMachine;

public class Practica_00_exe {

    public static void main(String[] args) {

		String agente = "si2023.sergiogarcia1alu.p00.Agente";
		
		//Load available games
		String spGamesCollection =  "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);

		//Game settings
		boolean visuals = false;
		int seed = new Random().nextInt();
		
		// Game and level to play
		int gameIdx  = 50;
		int levelIdx = 1; // level names from 0 to 4 (game_lvlN.txt).
		
		String gameName = games[gameIdx][1];
		String game = games[gameIdx][0];
		String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);

		
		// 1. This starts a game, in a level, played by a human.
		//ArcadeMachine.playOneGame(game, level1, null, seed);
		
		
		// 2. This plays a game in a level by the controller.
		ArcadeMachine.runOneGame(game, level1, visuals, agente, null, seed, 0);
		

		System.exit(0);

    }
}