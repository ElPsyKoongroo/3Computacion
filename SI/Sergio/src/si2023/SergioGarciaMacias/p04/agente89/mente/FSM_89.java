package si2023.SergioGarciaMacias.p04.agente89.mente;

import java.util.HashMap;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.MaquinaFSM;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.p04.agente89.estados.ECapturarEnemigo;
import si2023.SergioGarciaMacias.p04.agente89.estados.EDefault;
import si2023.SergioGarciaMacias.p04.agente89.estados.EParguelaPeligroso;
import si2023.SergioGarciaMacias.p04.agente89.estados.EPrevieneParguela;
import si2023.SergioGarciaMacias.p04.agente89.estados.ESalirCarcel;
import si2023.SergioGarciaMacias.p04.agente89.estados.ESalvarParguela;
import si2023.SergioGarciaMacias.p04.agente89.estados.EVaciarBolsa;
import si2023.SergioGarciaMacias.shared.Mundo89;

/*
 * MSF stands for MotherSuperFucker89 or Maquina de heStados Finita.
 */
public class FSM_89 extends MaquinaFSM {

	Mundo89 mundo;

	public FSM_89(Mundo m) {
		this.mundo = (Mundo89) m;

		this.conversion_estado = new HashMap<>();
		this.conversion_estado.put(ESalvarParguela.ID, new ESalvarParguela());
		this.conversion_estado.put(ECapturarEnemigo.ID, new ECapturarEnemigo());
		this.conversion_estado.put(EDefault.ID, new EDefault());
		this.conversion_estado.put(EVaciarBolsa.ID, new EVaciarBolsa());
		this.conversion_estado.put(EPrevieneParguela.ID, new EPrevieneParguela());
		this.conversion_estado.put(EParguelaPeligroso.ID, new EParguelaPeligroso());
		this.conversion_estado.put(ESalirCarcel.ID, new ESalirCarcel());

		this.estado_inicial = this.conversion_estado.get(EDefault.ID);
		this.estado_actual = this.estado_inicial;
	}

	public ACTIONS pensar(Mundo m) {
		return this.decide(m);
	};
}
