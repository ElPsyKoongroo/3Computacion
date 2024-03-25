package si2023.sergiogarcia1alu.shared.acciones;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Accion;
import si2023.sergiogarcia1alu.shared.Mundo89;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaMedioMuerto;
import tools.Vector2d;

/*
 * Posible mejora:
 * 
 * Ahora mismo el algoritmo selecciona el parguela que esta mas cerca al suelo como el "prioriatrio".
 * Creo que lo mejor seria hacer una especie de Dijkstra.
 * 
 * La implementacion deberia tener una variable "tiempo_restante" que indica el tiempo que queda hasta 
 * que el parguela mas cercano al suelo se "estampe". Cada accion de Superman restaria un valor a tiempo_restante. 
 * Si "tiempo_restante" llega a 0 entonces el camino encontrado por el algoritmo no es valido y deberia 
 * seguir buscando.
 * 
 * En caso de que encuentre un camino deberia seguir ese camino hasta que rescate a todos o hasta que un
 * nuevo parguela entre a la zona de peligro. En este caso habria que recalcular la ruta ideal para salvarlos
 * a todos a tiempo.
 * 
 * En caso de que el algoritmo no encontrara ningun camino GAME OVER.
 * 
 * Hay que tener en cuenta que Superman se mueve mas rapido que los Parguelas, entonces en lo que un
 * parguela cae 1 bloque superman se puede mover alomejor 2 bloques. 
 * 
 * */
public class SalvaParguelaPeligro implements Accion {

	public SalvaParguelaPeligro() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ACTIONS do_action(Mundo m) {
		ArrayList<Vector2d> parguelas = ParguelaMedioMuerto.parguelas_medio_muertos(m);
		Vector2d p = parguelas.get(0);
		for (Vector2d parguela : parguelas) {
			if (parguela.y < p.y)
				p = parguela;
		}

		return SalvaParguela.selecciona_accion(parguelas.get(0), ((Mundo89) m).get_player_pos_block());
	}

}
