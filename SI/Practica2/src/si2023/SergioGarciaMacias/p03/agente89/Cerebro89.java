package si2023.SergioGarciaMacias.p03.agente89;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;
import tools.Vector2d;
import java.lang.Math;
import java.lang.reflect.Array;

public class Cerebro89 implements Cerebro {

	static final int JUGADOR = 1;
	static final int NUBES = 4;
	static final int MALOS = 3;
	static final int NPC = 6;

	private ArrayList<Observation> enemigos;
	private int block_size;
	private Vector2d player_position;
	private int actual_target_id = -1;
	private Vector2d jail_pos = new Vector2d(17.0, 12.0);
	private HashMap<Integer, Integer> player_resources;
	private Stack<ACTIONS> jail_path = new Stack<ACTIONS>();
	private boolean columna_izq = true;
	
	
	// Soy mas de self (Rust 4ever <3)
	private Cerebro89 self;

	public Cerebro89(StateObservation mundo) {
		this.self = this;
		self.block_size = mundo.getBlockSize();
		self.localiza_carcel(mundo);
	}
	
	private void localiza_carcel(StateObservation mundo) {
		ArrayList<Observation>[][] mapa = mundo.getObservationGrid();
		
		for(int i = 0; i<mapa.length; i++) {
			for(int j = 0; j<mapa[i].length; j++) {
				for(Observation element: mapa[i][j]) {
					if (element.category == 4 && element.itype == 6) {
						self.jail_pos = new Vector2d(element.position.x/self.block_size, element.position.y/self.block_size);
					}
				}
			}
		}
		
	}
	
	private void selecciona_enemigo() {	
		for (Observation enemy : self.enemigos) {
			if (columna_izq) {
				if (enemy.position.x == 756.0) {
					self.actual_target_id = enemy.obsID;
					return;
				}
			} else {
				if (enemy.position.x == 0.0) {
					self.actual_target_id = enemy.obsID;
					return;
				}
			}

		}
		self.actual_target_id = -1;
	}

	/// Calcula el recorrido que ha de seguir el avatar
	/// para llegar a la carcel
	private void calcula_jail_path() {
		double x_diff = jail_pos.x - player_position.x / self.block_size;
		double y_diff = jail_pos.y - player_position.y / self.block_size;

		while (Math.abs(Math.floor(x_diff) - Math.floor(y_diff)) > 0) {
			if (y_diff < 0) {
				jail_path.add(ACTIONS.ACTION_UP);
				y_diff += 1;
			} else if (y_diff > 0) {
				jail_path.add(ACTIONS.ACTION_DOWN);
				y_diff -= 1;
			}

			if (x_diff > 0) {
				self.jail_path.add(ACTIONS.ACTION_RIGHT);
				x_diff -= 1;
			} else if (x_diff < 0) {
				self.jail_path.add(ACTIONS.ACTION_LEFT);
				x_diff += 1;
			}
		}
	}

	@Override
	public ACTIONS AnalizarMapa() {

		// Tenemos la bolsa llena
		Integer cantidad = self.player_resources.get(13);
		if (cantidad != null && cantidad == 8) {
			if (self.jail_path.size() == 0) {
				self.calcula_jail_path();
			}
			return self.jail_path.pop();
		}

		// Cuando actual_target_id == 1 no tenemos ningun enemigo
		// como objetivo
		if (self.actual_target_id == -1) {
			self.selecciona_enemigo();
		}


		Vector2d actual_target = new Vector2d();
		for (Observation enemy : enemigos) {
			if (enemy.obsID == actual_target_id) {
				actual_target = enemy.position;
			}
		}

		// Se me ha quedao buggeao ticher
		if (actual_target.x == 0 && actual_target.y == 0)
			selecciona_enemigo();

		if (self.actual_target_id == -1)
			return ACTIONS.ACTION_DOWN;

		if (actual_target.x == 756.0)
			self.columna_izq = false;
		else
			self.columna_izq = true;

		double x_diff = actual_target.x - player_position.x;
		double y_diff = actual_target.y - player_position.y;

		// int p_x_block = (int) (player_position.x / block_size);
		int p_y_block = (int) (self.player_position.y / self.block_size);

		// int e_x_block = (int) (actual_target.x / self.block_size);
		int e_y_block = (int) (actual_target.y / self.block_size);
	
		if (p_y_block - e_y_block > 0)
			return ACTIONS.ACTION_UP;
		else if (p_y_block - e_y_block < 0)
			return ACTIONS.ACTION_DOWN;

		if (x_diff > 0) {
			return ACTIONS.ACTION_RIGHT;
		} else if (x_diff < 0) {
			return ACTIONS.ACTION_LEFT;
		}
		self.actual_target_id = -1;
		return ACTIONS.ACTION_DOWN;

	}

	@Override
	public ACTIONS Pensar(StateObservation mundo) {
		// TODO Auto-generated method stub
		self.enemigos = new ArrayList<>();
		ArrayList<Observation>[] cosas_movibles = mundo.getNPCPositions();
		self.player_resources = mundo.getAvatarResources();

		if (cosas_movibles == null)
			return ACTIONS.ACTION_NIL;
		
		self.block_size = mundo.getBlockSize();
		self.player_position = mundo.getAvatarPosition();

		for (int i = 0; i < cosas_movibles.length; i++) {
			for (int j = 0; j < cosas_movibles[i].size(); j++) {
				if (cosas_movibles[i].get(j).category == MALOS) {
					enemigos.add(cosas_movibles[i].get(j));
				}
			}
		}

		return self.AnalizarMapa();

	}

}
