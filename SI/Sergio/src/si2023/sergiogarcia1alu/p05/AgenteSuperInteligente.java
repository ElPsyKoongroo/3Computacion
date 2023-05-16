package si2023.sergiogarcia1alu.p05;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.p05.operadores.*;
import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.shared.Mundo04;
import si2023.sergiogarcia1alu.shared.utils.Stack;
import si2023.sergiogarcia1alu.strips.*;
import tools.ElapsedCpuTimer;
import tools.Vector2d;

import java.util.ArrayList;

/**
 * 0 -> Pared
 * 8 -> Puerta
 * 4 -> Player
 * 9 -> Bloque movible
 * 7 -> Llave
 * 6 -> Seta
 * 3 -> Vacio
 *
 */

// Si no funciona bien es por que le falta oligoelementos <3
public class AgenteSuperInteligente extends AbstractPlayer {
	private final ArrayList<ACTIONS> solucion;
	String[][] map;
	Stack<ACTIONS> acciones;
	private static final int JUGADOR_NORMAL = 9;

	public static int x_size = 0;
	public static int y_size = 0;
	public AgenteSuperInteligente(StateObservation state, ElapsedCpuTimer timer) {

		ArrayList<Observation>[][] estados = state.getObservationGrid();
		this.solucion = new ArrayList<>();
		AgenteSuperInteligente.x_size = estados.length;
		AgenteSuperInteligente.y_size = estados[0].length;
		map = new String[x_size][y_size];

		full_update(state);
		Mundo04 mundo = new Mundo04(state);

		int[] dimensiones = mundo.get_dimensions();
		int x = dimensiones[0];
		int y = dimensiones[1];

//		if (!((x == 13 && y == 9) || (x == 5 && y == 6))) {
//			return ;
//		}

//		System.out.println(x);
//		System.out.println(y);

		ArrayList<Meta> recursos_iniciales = new ArrayList<>();
		StripsState.pos_paredes.clear();
		for(int i = 0; i<x; i++) {
			for(int j = 0; j<y;j++) {
				switch (mundo.get_pos_type(i,j)) {
					case Pared:
						StripsState.pos_paredes.add(new Vector2d(i,j));
						recursos_iniciales.add(new Pared(new Vector2d(i,j)));;
						break;
					case Puerta:
						recursos_iniciales.add(new Puerta(new Vector2d(i,j)));
						break;
					case Player:
						recursos_iniciales.add(new Jugador(new Vector2d(i,j)));
						recursos_iniciales.add(new BloqueLibre(new Vector2d(i,j)));
						break;
					case BloqueMovible:
						recursos_iniciales.add(new BloquePiedra(new Vector2d(i,j)));
						break;
					case Llave:
						recursos_iniciales.add(new Llave(new Vector2d(i,j)));
						break;
					case Seta:
						recursos_iniciales.add(new Seta(new Vector2d(i,j)));
						//recursos_iniciales.add(new BloqueLibre(new Vector2d(i,j)));
						break;
					case Vacio:
						recursos_iniciales.add(new Gujero(new Vector2d(i,j)));
						break;
					case Nothing:
						recursos_iniciales.add(new BloqueLibre(new Vector2d(i,j)));
						break;
				}
			}
		}

		ArrayList<IStackeable> objetivos = new ArrayList<>();
		objetivos.add(new HeSalido());
		objetivos.add(new TengoLlave());

		ArrayList<Operador> acciones = new ArrayList<>();

		acciones.add(new Moverme());
		acciones.add(new CogerSeta());
		acciones.add(new TaparAbujero());
		acciones.add(new CogerLlave());
		acciones.add(new Salir());
		acciones.add(new MoverPiedra());


		StripsState estado_inicial = new StripsState(recursos_iniciales, objetivos);

		ArrayList<Meta> objetivos_finales = new ArrayList<>();
		objetivos_finales.add(new HeSalido());
		objetivos_finales.add(new TengoLlave());
		Strips super_solver = new Strips(estado_inicial, acciones, objetivos_finales, Strips.TipoRecorrido.Anchura);
		super_solver.resolver();


		// this.solucion = super_solver.solucion; // new ArrayList<>()

		for(Operador op : super_solver.solucion) {
			ACTIONS ac = op.GetAction();
			//System.out.println(ac.name());
			solucion.add(ac);
		}
		//System.out.println("Resuelto");

		//mundo.print_map();
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
			for (String[] strings : map) {
				String block = String.format("%3s", strings[i]);
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
		try {

			return solucion.remove(0);
		} catch (Exception e) {
			return ACTIONS.ACTION_NIL;
		}
	}
}
