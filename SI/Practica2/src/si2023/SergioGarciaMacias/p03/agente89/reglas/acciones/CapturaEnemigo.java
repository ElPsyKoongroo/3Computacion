package si2023.SergioGarciaMacias.p03.agente89.reglas.acciones;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Accion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89.iType;
import tools.Vector2d;

public class CapturaEnemigo implements Accion {

	public CapturaEnemigo() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 
	 * CapturaEnemigo tiene su propio selecciona accion ya que primero debe moverse
	 * en el eje de las Y y despues en el de las X para que no se quede bloqueado en
	 * los bloques que no pueden ser atravesados.
	 * 
	 */
	public static ACTIONS selecciona_accion(Vector2d target, Vector2d player_pos) {
		int y_diff = (int) (target.y - player_pos.y);
		int x_diff = (int) (player_pos.x - target.x);

		if (y_diff > 0)
			return ACTIONS.ACTION_DOWN;
		else if (y_diff < 0)
			return ACTIONS.ACTION_UP;

		if (x_diff < 0) {
			return ACTIONS.ACTION_RIGHT;
		} else if (x_diff > 0) {
			return ACTIONS.ACTION_LEFT;
		}

		return ACTIONS.ACTION_NIL;
	}

	@Override
	public ACTIONS do_action(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		ArrayList<Vector2d> enemigos = mundo.get_pos_iType(iType.Enemigo);

		if (enemigos.size() == 0)
			return ACTIONS.ACTION_NIL;

		Vector2d player_pos = mundo.get_player_pos_block();
		Vector2d closest_enemy = enemigos.get(0);

		for (Vector2d enemy_pos : enemigos) {
			if (enemy_pos.dist(player_pos) < closest_enemy.dist(player_pos)) {
				closest_enemy = enemy_pos;
			}
		}

		return selecciona_accion(closest_enemy, player_pos);
	}

}
