package si2023.SergioGarciaMacias.shared.acciones;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Accion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;
import si2023.SergioGarciaMacias.shared.condiciones.ParguelaEnPeligro;
import tools.Vector2d;

public class ProtegeParguela implements Accion {

	public ProtegeParguela() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ACTIONS do_action(Mundo m) {
		Vector2d prevencion = ParguelaEnPeligro.previene(m);
		return CapturaEnemigo.selecciona_accion(prevencion, ((Mundo89)m).get_player_pos_block());
	}

}
