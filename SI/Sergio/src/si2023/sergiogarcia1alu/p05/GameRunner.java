package si2023.sergiogarcia1alu.p05;

import tools.Utils;
import tracks.ArcadeMachine;

import java.util.ArrayList;

/*
* Nivel 4:  43 movimientos maximo
*
* Nivel 1: 38 movientos maximo
* */

public class GameRunner {
    public static ArrayList<Long> times = new ArrayList<>();
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
        // Game settings
        boolean visuals = false;
//        String level1 = game.replace(gameName, gameName + "_lvl" + 4);
//        ArcadeMachine.playOneGame(game, level1, null, 33);
        for(int i = 0; i<2; i++) {
            String level1 = game.replace(gameName, gameName + "_lvl" + i);
            ArcadeMachine.runOneGame(game, level1, visuals, p0, null, 2, 0);
        }

        String level1 = game.replace(gameName, gameName + "_lvl" + 4);
        ArcadeMachine.runOneGame(game, level1, visuals, p0, null, 2, 0);

//        final int totalTimes = 25;
//        for(int i = 0; i<totalTimes; i++) {
//            ArcadeMachine.runOneGame(game, level1, visuals, p0, null, 2, 0);
//        }
//        long media = 0;
//        for(int i = 0; i<totalTimes; i++) {
//            media += times.get(i);
//        }
//        media /= totalTimes;
//        System.out.println("Tiempo medio: " + media);

        //ArcadeMachine.playOneGame(game, level1, null, 33);
//        for(int i = 0; i<5; i++) {
//            int levelIdx = i;
//            String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);
//            ArcadeMachine.playOneGame(game, level1, null, 33);
//            ArcadeMachine.runOneGame(game, level1, visuals, p0, null, 2, 0);
//        }
        System.exit(0);

    }
}
