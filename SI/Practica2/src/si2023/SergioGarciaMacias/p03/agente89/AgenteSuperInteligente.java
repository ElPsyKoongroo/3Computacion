package si2023.SergioGarciaMacias.p03.agente89;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;
import tools.Vector2d;

public class AgenteSuperInteligente extends AbstractPlayer {

	static final int JUGADOR = 1;
	static final int NUBES = 4;
	static final int MALOS = 3;
	static final int NPC = 6;

	String[][] map;

	Cerebro89 serebro;

	public AgenteSuperInteligente(StateObservation state, ElapsedCpuTimer timer) {
		ArrayList<Observation>[][] estados = state.getObservationGrid();

		serebro = new Cerebro89(state);
		int x_size = estados.length;
		int y_size = estados[0].length;
		map = new String[x_size][y_size];

		System.out.println(MessageFormat.format("Columnas: {0}", y_size));
		System.out.println(MessageFormat.format("Filas: {0}", x_size));

		full_update(state);
		print_map();
	}

	public void full_update(StateObservation state) {
		ArrayList<Observation>[][] estados = state.getObservationGrid();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (estados[i][j].size() == 0) {
					map[i][j] = " ";
				} else {
					int type = estados[i][j].get(0).category;
					set_char(i, j, type);
				}
			}
		}
	}

	public void print_map() {
		int y_size = map.length;
		int x_size = map[0].length;
		for (int i = 0; i < x_size; i++) {
			for (int j = 0; j < y_size; j++) {
				System.out.print(map[j][i]);
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}

	public void set_char(int x, int y, int type) {
		map[x][y] = String.valueOf(type);

		/*
		 * switch (type) { case AGUILA: map[x][y] = 'A'; break; case SERPIENTE:
		 * map[x][y] = 'S'; break; case META: map[x][y] = 'G'; break; case ARBOL:
		 * map[x][y] = '#'; break; default: map[x][y] = ' '; break; }
		 */

	}

	public void update_map(StateObservation stateObs) {
		Vector2d avatar_pos = stateObs.getAvatarPosition();
		int block_size = stateObs.getBlockSize();
		int avatar_x = (int) (avatar_pos.x / block_size);
		int avatar_y = (int) (avatar_pos.y / block_size);
		full_update(stateObs);
		set_char(avatar_x, avatar_y, JUGADOR);
	}

	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {

		update_map(stateObs);
		// print_map();
		return this.serebro.Pensar(stateObs);
	}
}
