package si2023.SergioGarciaMacias.p03.agente89.mente;

import java.util.ArrayList;

import core.game.StateObservation;
import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Motor;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Regla;
import si2023.SergioGarciaMacias.p03.agente89.reglas.CapturarEnemigo;
import si2023.SergioGarciaMacias.p03.agente89.reglas.Default;
import si2023.SergioGarciaMacias.p03.agente89.reglas.ParguelaPeligroso;
import si2023.SergioGarciaMacias.p03.agente89.reglas.PrevieneParguela;
import si2023.SergioGarciaMacias.p03.agente89.reglas.SalvarParguela;
import si2023.SergioGarciaMacias.p03.agente89.reglas.VaciarBolsa;

public class Motor89 implements Motor {

	private Mundo89 mundo;
	private ArrayList<Regla> reglas;

	public Motor89(StateObservation mundo) {
		this.mundo = new Mundo89(mundo);
		this.reglas = new ArrayList<>();
		this.reglas.add(new ParguelaPeligroso());
		this.reglas.add(new SalvarParguela());
//		this.reglas.add(new PrevieneParguela());  // No parece afectar en mucho al WinRate 
		this.reglas.add(new VaciarBolsa());
		this.reglas.add(new CapturarEnemigo());
		this.reglas.add(new Default());
	}

	@Override
	public ACTIONS AnalizarMapa(Mundo m) {
		for(Regla r: this.reglas) { 
			if (r.se_cumple(m)) {
				return r.act().do_action(m);
			}
		}
		System.out.println("No se cumple nada");
		return ACTIONS.ACTION_NIL;
	}

	@Override
	public ACTIONS Pensar(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		this.mundo = new Mundo89(mundo);

		// En modo grafico el juego tarda en iniciarse
		// por lo que es necesaria esta comprobacion para
		// que no pete.
		if (this.mundo.ok())
			return this.AnalizarMapa(m);

		System.out.println("Mapa no ok");
		return ACTIONS.ACTION_NIL;

	}
	
}
