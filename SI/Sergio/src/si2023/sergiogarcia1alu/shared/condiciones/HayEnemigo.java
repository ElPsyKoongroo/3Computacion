package si2023.sergiogarcia1alu.shared.condiciones;

import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Condicion;
import si2023.sergiogarcia1alu.shared.Mundo89;

public class HayEnemigo implements Condicion {

	/*
	 * Si hay mas de X de enemigos en pantalla está permitido ir a
	 * capturarlos. 
	 * 
	 * Si queremos que pueda ir a capturar enemigos mientras no hay parguelas 
	 * cayendo cambiar este valor por 0.
	 * 
	 * Valores mas bajos aumentan el WinRate a cambio del tiempo y score.
	 * 
	 */

	private static final double VALOR_COMPLETAMENTE_ARBITRARIO = 40; //40 / Math.E;

	public HayEnemigo() {
	}

	@Override
	public boolean se_cumple(Mundo m) {
		Mundo89 mundo = (Mundo89) m;
		return mundo.n_aliados() == 0 || mundo.get_enemigos().size() > VALOR_COMPLETAMENTE_ARBITRARIO;
	}

}