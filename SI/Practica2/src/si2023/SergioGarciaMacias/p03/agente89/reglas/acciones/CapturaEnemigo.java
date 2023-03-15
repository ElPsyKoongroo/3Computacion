package si2023.SergioGarciaMacias.p03.agente89.reglas.acciones;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Accion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;
import tools.Vector2d;

public class CapturaEnemigo implements Accion {

	public CapturaEnemigo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ACTIONS do_action(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		ArrayList<Vector2d> enemigos = mundo.get_pos_enemigos();
		Vector2d player_pos = mundo.get_player_position();

		if (enemigos.size() == 0) 
			return ACTIONS.ACTION_NIL;
		
		Vector2d closest_enemy = enemigos.get(0);

		for (Vector2d enemy_pos : enemigos) {
			if (enemy_pos.dist(player_pos) < closest_enemy.dist(player_pos)) {
				closest_enemy = enemy_pos;
			}
		}

		// Posicion del jugador
		int p_x_block = (int) (player_pos.x / mundo.get_block_size());
		int p_y_block = (int) (player_pos.y / mundo.get_block_size());

		// Posicion del enemigo
		int e_x_block = (int) (closest_enemy.x / mundo.get_block_size());
		int e_y_block = (int) (closest_enemy.y / mundo.get_block_size());

		int y_diff = p_y_block - e_y_block;
		int x_diff = p_x_block - e_x_block;

		

		if (y_diff > 0)
			return ACTIONS.ACTION_UP;
		else if (y_diff < 0)
			return ACTIONS.ACTION_DOWN;

		if (x_diff < 0) {
			return ACTIONS.ACTION_RIGHT;
		} else if (x_diff > 0) {
			return ACTIONS.ACTION_LEFT;
		}
		
		System.out.println("Default captura enemigos");
		return ACTIONS.ACTION_DOWN;
	}

}
