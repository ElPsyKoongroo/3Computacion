package si2023.SergioGarciaMacias.p03.agente89.mente;

import java.util.ArrayList;
import java.util.HashMap;

import core.game.Observation;
import core.game.StateObservation;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import tools.Vector2d;

public class Mundo89 implements Mundo {
	public static enum iType {
		Bala,
		Carcel,
		Civil,
		Civil_cayendo,
		Enemigo_Derecho,
		Enemigo_Izquierdo,
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

	private int block_size;
	private boolean bolsa_llena = false;
	private ArrayList<Observation> enemigos;
	private iType[][] grid;
	private Vector2d jail_pos = new Vector2d(17.0, 12.0); // Oink Oink, Magic !
	private boolean mundo_ok;
	private Vector2d player_position;
	private HashMap<Integer, Integer> player_resources;

	/// Copy constructor
	/// see:
	/// https://www.doc-developpement-durable.org/file/Projets-informatiques/cours-&-manuels-informatiques/java/Effective%20Java,%202nd%20Edition.pdf
	@SuppressWarnings("unchecked")
	public Mundo89(Mundo89 other) {
		this.jail_pos = other.jail_pos.copy();

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
		this.enemigos = (ArrayList<Observation>) other.enemigos.clone();
	}

	public Mundo89(StateObservation state) {
		this.enemigos = new ArrayList<>();
		localiza_carcel(state);
		this.grid = new iType[state.getObservationGrid().length][state.getObservationGrid()[0].length];
		this.actualizar(state);
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

	private void set_grid(StateObservation m) {
		ArrayList<Observation>[][] estados = m.getObservationGrid();
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if (estados[i][j].size() == 0) {
					grid[i][j] = iType.Nothing;
				} else {
					int type = estados[i][j].get(0).itype;
					this.set_type(i, j, type);
				}
			}
		}
	}

	private void set_type(int x, int y, int type) {
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
		case 18:
			tipo = iType.Civil_cayendo;
			break;
		case 16:
			tipo = iType.Civil;
			break;
		case 19: 
			tipo = iType.Bala;
			break;
		default:
			tipo = iType.Nothing;
			break;
		}
		this.grid[x][y] = tipo;
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

	public ArrayList<Observation> get_enemigos() {
		return this.enemigos;
	}

	public iType[][] get_grid() {
		return this.grid;
	}

	public Vector2d get_jail_position() {
		return this.jail_pos;
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

	public int n_aliados() {
		int n_aliados = 0;
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if(grid[i][j] == iType.Civil) n_aliados++; 
			}
		}
		return n_aliados;
	}

	public boolean ok() {
		return this.mundo_ok;
	}

}
