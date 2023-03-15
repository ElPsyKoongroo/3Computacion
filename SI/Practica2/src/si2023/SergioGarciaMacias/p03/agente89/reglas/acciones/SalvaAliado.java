package si2023.SergioGarciaMacias.p03.agente89.reglas.acciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Accion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89.iType;
import si2023.SergioGarciaMacias.p03.agente89.reglas.condiciones.AliadoCayendo;
import tools.Vector2d;

// Singleton ? 
public class SalvaAliado implements Accion {

	private static final double NIVEL_DEL_SUELO = 12.0;

	public SalvaAliado() {
		// TODO Auto-generated constructor stub
	}

	private ACTIONS selecciona_accion(Vector2d closest_parguela, Vector2d player_pos) {
		if (closest_parguela.y > player_pos.y)
			return ACTIONS.ACTION_DOWN;
		else if (closest_parguela.y < player_pos.y)
			return ACTIONS.ACTION_UP;

		if (closest_parguela.x > player_pos.x)
			return ACTIONS.ACTION_RIGHT;
		else if (closest_parguela.x < player_pos.x)
			return ACTIONS.ACTION_LEFT;

		return ACTIONS.ACTION_NIL;
	}

	// Java se pone nervioso si le paso in Integer al metodo conteins de un
	// HashMap<Integer, _>
	// porque dice que le paso un Integer en vez de un Object. Makes 0 sense
	@SuppressWarnings("unlikely-arg-type")
	public Vector2d selecciona_parguela(ArrayList<Vector2d> parguelas, Vector2d player_pos) {
		HashSet<Integer> distancias = new HashSet<>();
		HashMap<Integer, ArrayList<Vector2d>> relaciones = new HashMap<>();

		for (Vector2d parguela : parguelas) {
			Integer distacia_al_suelo = (int) parguela.dist(new Vector2d(parguela.x, NIVEL_DEL_SUELO));
			// distacia_al_suelo += (int) parguela.dist(player_pos);
			distancias.add(distacia_al_suelo);
			if (relaciones.containsValue(distacia_al_suelo)) {
				relaciones.get(distacia_al_suelo).add(parguela);
			} else {
				distancias.add(distacia_al_suelo);
				relaciones.put(distacia_al_suelo, new ArrayList<>());
				relaciones.get(distacia_al_suelo).add(parguela);
			}
		}

		ArrayList<Integer> ordenados = new ArrayList<>(distancias);
		Collections.sort(ordenados);

		return relaciones.get(ordenados.get(0)).get(0);
	}

	public Vector2d selecciona_parguela_v2(iType[][] grid) {

		ArrayList<Vector2d> balas = new ArrayList<>();
		
		for (int y = (int) NIVEL_DEL_SUELO; y > 0; y--) {
			for (int x = 0; x < grid.length; x++) {	
				if (grid[x][y] == iType.Bala) balas.add(new Vector2d(x, y));
			}
		}

		for(Vector2d bala : balas) {
			
		}
		
		return new Vector2d(0, 0);
	}

	@Override
	public ACTIONS do_action(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		ArrayList<Vector2d> parguelas = AliadoCayendo.dectecta_parguelas(m);
		Vector2d player_pos = new Vector2d(mundo.get_player_position().x / mundo.get_block_size(),
				mundo.get_player_position().y / mundo.get_block_size());
		Vector2d closest_parguela = selecciona_parguela(parguelas, player_pos);
		return selecciona_accion(closest_parguela, player_pos);
	}

}
