package si2023.sergiogarcia1alu.p02.agente89.estados;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Accion;
import si2023.sergiogarcia1alu.ia.reglas.Estado;
import si2023.sergiogarcia1alu.ia.reglas.Transicion;
import si2023.sergiogarcia1alu.shared.acciones.ProtegeParguela;
import si2023.sergiogarcia1alu.shared.condiciones.Always;
import si2023.sergiogarcia1alu.shared.condiciones.BolsaLLena;
import si2023.sergiogarcia1alu.shared.condiciones.HayEnemigo;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaCayendo;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaEnPeligro;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaMedioMuerto;

public class EPrevieneParguela extends Estado {

	private static Accion AC = new ProtegeParguela();
	public static final String ID = "EPrevieneParguela";

	public EPrevieneParguela() {
		this.transiciones = new ArrayList<>();

		this.transiciones.add(new Transicion(EParguelaPeligroso.ID, new ParguelaMedioMuerto()));
		this.transiciones.add(new Transicion(ESalvarParguela.ID, new ParguelaCayendo()));
//		this.transiciones.add(new Transicion(EPrevieneParguela.ID, new ParguelaEnPeligro()));
		this.transiciones.add(new Transicion(EVaciarBolsa.ID, new BolsaLLena()));
		this.transiciones.add(new Transicion(ECapturarEnemigo.ID, new HayEnemigo()));
		this.transiciones.add(new Transicion(EDefault.ID, new Always()));

	}

	@Override
	public Accion get_accion() {
		return EPrevieneParguela.AC;
	}

}