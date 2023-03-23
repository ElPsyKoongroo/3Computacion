package si2023.SergioGarciaMacias.p04.agente89;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;
import si2023.SergioGarciaMacias.p04.agente89.mente.FSM_89;
import tools.ElapsedCpuTimer;

/* 
 * Cortesia de Adrian Rodriguez Rodriguez:
 * 
 * iType
 * 0     -> Rocas (end of map)
 * 3     -> Nube
 * 4     -> Piedras
 * 5     -> Nube destruida
 * 6     -> Carcel
 * 11    -> Enemigo Derecho
 * 12    -> Enemigo Izquierdo
 * 16    -> Civil
 * 18    -> Civil cayendo
 * 19    -> Bala
 * 20    -> Player
 * 
 */

// Si no funciona bien es por que le falta oligoelementos <3
public class AgenteSuperInteligente extends AbstractPlayer {
	String[][] map;
	Mundo89 mundo;
	FSM_89 serebro;

	public AgenteSuperInteligente(StateObservation state, ElapsedCpuTimer timer) {
		ArrayList<Observation>[][] estados = state.getObservationGrid();

		serebro = new FSM_89(mundo);
		mundo = new Mundo89(state);
		int x_size = estados.length;
		int y_size = estados[0].length;
		map = new String[x_size][y_size];

		//System.out.println(MessageFormat.format("Columnas: {0}", y_size));
		//System.out.println(MessageFormat.format("Filas: {0}", x_size));

		full_update(state);
		// print_map();
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
				String block = String.format("%3s", map[j][i]);
				System.out.print(block);
			}
			System.out.println();
		}
		System.out.println("\n\n");
		System.out.println("===========================================================================");
		System.out.println();
	}

	public void set_char(int x, int y, int type) {
		map[x][y] = String.valueOf(type);
	}

	public void update_map(StateObservation stateObs) {
		full_update(stateObs);
	}

	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {

		update_map(stateObs);
		this.mundo.actualizar(stateObs);

		// Descomentar esto para que se muestre el mapa por consola.
		// print_map();
		return this.serebro.pensar(this.mundo);
	}
}
