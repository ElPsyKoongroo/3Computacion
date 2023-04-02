package si2023.sergiogarcia1alu.p02.agente89.estados;

import java.util.ArrayList;

import si2023.sergiogarcia1alu.ia.reglas.Accion;
import si2023.sergiogarcia1alu.ia.reglas.Estado;
import si2023.sergiogarcia1alu.ia.reglas.Transicion;
import si2023.sergiogarcia1alu.shared.acciones.SalirDeCarcel;
import si2023.sergiogarcia1alu.shared.condiciones.Always;
import si2023.sergiogarcia1alu.shared.condiciones.EstoyBurlao;
import si2023.sergiogarcia1alu.shared.condiciones.HayEnemigo;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaCayendo;

public class ESalirCarcel extends Estado {

	private static Accion AC = new SalirDeCarcel();
	public static final String ID = "ESalirCarcel";

	public ESalirCarcel() {
		this.transiciones = new ArrayList<>();
		
		this.transiciones.add(new Transicion(ESalvarParguela.ID, new ParguelaCayendo()));
		this.transiciones.add(new Transicion(ESalirCarcel.ID, new EstoyBurlao()));
		this.transiciones.add(new Transicion(ECapturarEnemigo.ID, new HayEnemigo()));
		this.transiciones.add(new Transicion(EDefault.ID, new Always()));

	}

	@Override
	public Accion get_accion() {
		return ESalirCarcel.AC;
	}

}
