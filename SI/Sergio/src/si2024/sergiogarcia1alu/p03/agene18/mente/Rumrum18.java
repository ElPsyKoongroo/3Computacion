package si2024.sergiogarcia1alu.p03.agene18.mente;

import java.util.ArrayList;

import core.game.StateObservation;
import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.ia.mente.Motor;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Regla;
import si2024.sergiogarcia1alu.p03.agene18.reglas.Acercar;
import si2024.sergiogarcia1alu.p03.agene18.reglas.Cazar;
import si2024.sergiogarcia1alu.p03.agene18.reglas.CorreNeo;
import si2024.sergiogarcia1alu.shared.Mundo18;

public class Rumrum18 implements Motor {

	private Mundo18 mundo;
	private ArrayList<Regla> reglas;

	public Rumrum18(StateObservation state) {
		
		this.reglas = new ArrayList();
		this.reglas.add(new CorreNeo());
        this.reglas.add(new Cazar());
		this.reglas.add(new Acercar());
	}
	

	@Override
	public ACTIONS decide(Mundo m) {
		for(Regla r: this.reglas) { 
			if (r.se_cumple(m)) {
                System.out.println(r.getClass());
				return r.act().do_action(m);
			}
		}
		System.out.println("No se cumple nada");
		return ACTIONS.ACTION_NIL;
	}

	@Override
	public ACTIONS pensar(Mundo m) {
		Mundo18 mundo = (Mundo18) m;
		this.mundo = new Mundo18(mundo);

		// En modo grafico el juego tarda en iniciarse
		// por lo que es necesaria esta comprobacion para
		// que no pete.
		if (this.mundo.ok())
			return this.decide(m);

		System.out.println("Mapa no ok");
		return ACTIONS.ACTION_NIL;
	}

}
