package si2023.SergioGarciaMacias.shared;

import java.util.ArrayList;
import java.util.HashMap;

import core.game.Observation;
import core.game.StateObservation;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import tools.Vector2d;

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

public class Mundo89 implements Mundo {
	public static enum iType {
		Bala,
		Carcel,
		Parguela,
		Parguela_cayendo,
		Enemigo,
		Nothing,
		Nube,
		Nube_destruida,
		Piedras,
		Player,
		Rocas
	}

	public static final int BOLSA = 13;
	public static final int JUGADOR = 1;
	public static final int MALOS = 3;
	public static final int NPC = 6;
	public static final int NUBES = 4;
	public static final int NIVEL_DEL_SUELO = 12;

	private int block_size;
	private boolean bolsa_llena = false;
	private ArrayList<Observation> enemigos;
	private iType[][] grid;
	private Vector2d jail_pos;
	private final ArrayList<Observation>[][] map;
	private boolean mundo_ok;
	private ArrayList<Observation> parguelas;
	private Vector2d player_position;
	private HashMap<Integer, Integer> player_resources;

	/// Copy constructor mejor que clone.
	/// see:
	/// https://www.doc-developpement-durable.org/file/Projets-informatiques/cours-&-manuels-informatiques/java/Effective%20Java,%202nd%20Edition.pdf
	@SuppressWarnings("unchecked")
	public Mundo89(Mundo89 other) {
		this.jail_pos = other.jail_pos.copy();
		this.map = other.map;
		if (other.player_position == null) {
			this.mundo_ok = false;
			return;
		}

		this.mundo_ok = true;
		this.player_position = other.player_position.copy();
		this.block_size = other.block_size;

		// Me parece desagradable que si clono un tipo T me devuelva
		// un Object en vez de T.
		this.player_resources = (HashMap<Integer, Integer>) other.player_resources.clone();
		
		
		// Esto esta regular ya que hace una shallow copy y no un deep cope. 
		// Observation no implementa cloneable ni tiene un copy method.
		this.enemigos = (ArrayList<Observation>) other.enemigos.clone();
		this.parguelas = (ArrayList<Observation>) other.parguelas.clone();
	}

	public Mundo89(StateObservation state) {
		this.enemigos = new ArrayList<>();
		this.parguelas = new ArrayList<>();
		this.map = state.getObservationGrid().clone();
		localiza_carcel(state);
		this.grid = new iType[state.getObservationGrid().length][state.getObservationGrid()[0].length];
		this.actualizar(state);
	}

	private iType get_type(int type) {
		// Mirar esquema de arriba
		iType tipo;
		switch (type) {
		case 0:
			tipo = iType.Rocas;
			break;
		case 3:
			tipo = iType.Nube;
			break;
		case 5:
			tipo = iType.Nube_destruida;
			break;
		case 11: // Drooooooooooooooooooooooooooooopeando
		case 12:
			tipo = iType.Enemigo;
			break;
		case 18:
			tipo = iType.Parguela_cayendo;
			break;
		case 16:
			tipo = iType.Parguela;
			break;
		case 19:
			tipo = iType.Bala;
			break;
		default:
			tipo = iType.Nothing;
			break;
		}
		return tipo;
	}

	private void localiza_carcel(StateObservation mundo) {
		ArrayList<Observation>[][] mapa = mundo.getObservationGrid();
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[i].length; j++) {
				for (Observation element : mapa[i][j]) {
					if (element.category == 4 && element.itype == 6) {
						this.jail_pos = new Vector2d(i, j);
					}
				}
			}
		}
	}

	// Esto tiene un Bug
	private void set_grid(StateObservation m) {
		ArrayList<Observation>[][] estados = m.getObservationGrid();
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if (estados[i][j].size() == 0) {
					grid[i][j] = iType.Nothing;
				} else {
					for (Observation o : estados[i][j]) {
						this.set_type(i, j, o.itype);
					}
				}
			}
		}
	}

	private void set_type(int x, int y, int type) {
		this.grid[x][y] = get_type(type);
	}

	@Override
	public void actualizar(StateObservation mundo) {
		this.enemigos.clear();
		this.set_grid(mundo);

		ArrayList<Observation>[] cosas_movibles = mundo.getNPCPositions();

		this.player_resources = mundo.getAvatarResources();

		if (this.player_resources.get(Mundo89.BOLSA) != null) {
			this.bolsa_llena = this.player_resources.get(Mundo89.BOLSA).intValue() == 8;
		} else {
			this.bolsa_llena = false;
		}

		this.block_size = mundo.getBlockSize();
		this.player_position = mundo.getAvatarPosition();

		for (int x = 0; x < mundo.getObservationGrid().length; x++) {
			for (int y = 0; y < mundo.getObservationGrid()[x].length; y++) {
				for (Observation o : mundo.getObservationGrid()[x][y]) {
					if (o.itype == 16 || o.itype == 18) {

					}
				}
			}
		}

		// Si no hay objetos moviles no añadimos nada a enemigos
		if (cosas_movibles == null)
			return;

		for (ArrayList<Observation> cosas_movible : cosas_movibles) {
			for (int j = 0; j < cosas_movible.size(); j++) {
				if (cosas_movible.get(j).category == MALOS) {
					enemigos.add(cosas_movible.get(j));
				}
			}
		}

	}

	public int get_block_size() {
		return this.block_size;
	}

	public boolean get_bolsa_llena() {
		return bolsa_llena;
	}

	public int[] get_dimensions() {
		return new int[] { this.grid.length, this.grid[0].length };
	}

	public ArrayList<Observation> get_enemigos() {
		return this.enemigos;
	}

	public iType[][] get_grid() {
		return this.grid;
	}

	public Vector2d get_jail_position() {
		return this.jail_pos;
	}

	public ArrayList<Observation> get_obs_iType(iType tipo) {
		ArrayList<Observation> pos = new ArrayList<>();
		for (int x = this.grid.length - 1; x > 0; x--) {
			for (int y = this.grid[x].length - 1; y > 0; y--) {
				for (Observation o : this.map[x][y]) {
					if (this.get_type(o.itype) == tipo) {
						pos.add(o);
					}
				}
			}
		}
		return pos;
	}

	public ArrayList<Observation> get_parguelas() {
		return this.parguelas;
	}

	public Vector2d get_player_position() {
		return this.player_position;
	}

	public HashMap<Integer, Integer> get_player_resources() {
		return this.player_resources;
	}

	public ArrayList<Vector2d> get_pos_enemigos() {
		ArrayList<Vector2d> pos = new ArrayList<>();
		for (Observation o : this.enemigos) {
			pos.add(o.position);
		}
		return pos;
	}

	public ArrayList<Vector2d> get_pos_iType(iType tipo) {
		ArrayList<Vector2d> pos = new ArrayList<>();
		for (int x = this.grid.length - 1; x >= 0; x--) {
			for (int y = this.grid[x].length - 1; y >= 0; y--) {
				if (this.grid[x][y] == tipo) {
					pos.add(new Vector2d(x, y));
				}
			}
		}
		return pos;
	}

	public ArrayList<Vector2d> get_pos_parguelas() {
		ArrayList<Vector2d> pos = new ArrayList<>();
		for (int x = this.grid.length - 1; x > 0; x--) {
			for (int y = this.grid[x].length - 1; y > 0; y--) {
				if (this.grid[x][y] == iType.Parguela) {
					pos.add(new Vector2d(x, y));
				}
			}
		}
		return pos;
	}

	public iType get_pos_type(int x, int y) {
		return this.grid[x][y];
	}

	public ArrayList<Observation> map_pos(int x, int y) {
		return this.map[x][y];
	}

	public Vector2d get_player_pos_block() {
		return new Vector2d(this.player_position.x / this.get_block_size(), this.player_position.y / this.get_block_size());
	}

	public int n_aliados() {
		int n_aliados = 0;
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if (grid[i][j] == iType.Parguela || grid[i][j] == iType.Parguela_cayendo)
					n_aliados++;
			}
		}
		return n_aliados;
	}

	public boolean ok() {
		return this.mundo_ok;
	}

}
