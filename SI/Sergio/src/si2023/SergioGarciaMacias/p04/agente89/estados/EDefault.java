package si2023.SergioGarciaMacias.p04.agente89.estados;

import java.util.ArrayList;

import si2023.SergioGarciaMacias.ia.reglas.Accion;
import si2023.SergioGarciaMacias.ia.reglas.Estado;
import si2023.SergioGarciaMacias.ia.reglas.Transicion;
import si2023.SergioGarciaMacias.shared.acciones.IrCentro;
import si2023.SergioGarciaMacias.shared.condiciones.Always;
import si2023.SergioGarciaMacias.shared.condiciones.BolsaLLena;
import si2023.SergioGarciaMacias.shared.condiciones.HayEnemigo;
import si2023.SergioGarciaMacias.shared.condiciones.ParguelaCayendo;

public class EDefault extends Estado {
	
	public static Accion AC = new IrCentro();
	public static final String ID = "EDefault";

	public EDefault() {
		this.transiciones = new ArrayList<>();
		
		this.transiciones.add(new Transicion(ESalvarParguela.ID, new ParguelaCayendo()));
		this.transiciones.add(new Transicion(EVaciarBolsa.ID, new BolsaLLena()));
		this.transiciones.add(new Transicion(ECapturarEnemigo.ID, new HayEnemigo()));
		this.transiciones.add(new Transicion(EDefault.ID, new Always()));
	}

	@Override
	public Accion get_accion() {
		return EDefault.AC;
	}


}
