package si2023.SergioGarciaMacias.p03.agente89.reglas.acciones;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Accion;
import si2023.SergioGarciaMacias.p03.agente89.mente.Mundo89;
import tools.Vector2d;

public class IrCentro implements Accion {

	public IrCentro() {
		// TODO Auto-generated constructor stub
	}
	
	private static final int DEFAULT_X = 11;
	private static final int DEFAULT_Y = 9;

	@Override
	public ACTIONS do_action(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		Vector2d centro = new Vector2d(DEFAULT_X, DEFAULT_Y);
		Vector2d player_pos = mundo.get_player_pos_block();

		if (centro.y > player_pos.y)
			return ACTIONS.ACTION_DOWN;
		else if (centro.y < player_pos.y)
			return ACTIONS.ACTION_UP;

		if (centro.x > player_pos.x)
			return ACTIONS.ACTION_RIGHT;
		else if (centro.x < player_pos.x)
			return ACTIONS.ACTION_LEFT;

		return ACTIONS.ACTION_NIL;
	}

}
