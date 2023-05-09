package si2023.sergiogarcia1alu.p04v2;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.p04v2.agente.mente.CEstrella;
import si2023.sergiogarcia1alu.p04v2.agente.mente.CEstrella.Recorrido;
import si2023.sergiogarcia1alu.p04v2.agente.nodo.Estado;
import si2023.sergiogarcia1alu.shared.Mundo16;
import si2023.sergiogarcia1alu.shared.Mundo16.iType;
import si2023.sergiogarcia1alu.shared.utils.Stack;
import tools.ElapsedCpuTimer;
import tools.Vector2d;

// Si no funciona bien es por que le falta oligoelementos <3
public class AgenteSuperInteligente extends AbstractPlayer {
	String[][] map;
	Stack<ACTIONS> acciones;
	private static final int JUGADOR_NORMAL = 9;
	public AgenteSuperInteligente(StateObservation state, ElapsedCpuTimer timer) {
		ArrayList<Observation>[][] estados = state.getObservationGrid();

		int x_size = estados.length;
		int y_size = estados[0].length;
		map = new String[x_size][y_size];

		full_update(state);
		Mundo16 mundo = new Mundo16(state);
		CEstrella estrellita = new CEstrella(mundo, Recorrido.Anchura);
		
		Vector2d meta = mundo.get_pos_iType(iType.Goal).get(0);
        Vector2d player_pos = mundo.get_player_pos_block();
         
        Estado inicial = new Estado(mundo, player_pos, null, null, 0);
        
        this.acciones = estrellita.busqueda(inicial, meta);

		// print_map();
	}

	public void full_update(StateObservation state) {
		ArrayList<Observation>[][] estados = state.getObservationGrid();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (estados[i][j].size() == 0) {
					map[i][j] = " ";
				} else {
					int type = estados[i][j].get(0).itype;
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

		// Descomentar esto para que se muestre el mapa por consola.
		// print_map();
		while (!this.acciones.isEmpty()) {
		    int a = stateObs.getAvatarType();
		    if (a == JUGADOR_NORMAL) {
		        ACTIONS ac = this.acciones.poll();
		        return ac;
		    }
		    return ACTIONS.ACTION_NIL; 
		}
		return ACTIONS.ACTION_NIL;
	}
}
