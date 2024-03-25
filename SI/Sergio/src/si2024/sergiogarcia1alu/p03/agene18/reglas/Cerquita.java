package si2024.sergiogarcia1alu.p03.agene18.reglas;

import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Condicion;
import si2024.sergiogarcia1alu.shared.Mundo18;
import si2024.sergiogarcia1alu.shared.Mundo18.iType;

public class Cerquita implements Condicion {

	@Override
	public boolean se_cumple(Mundo m) {
		Mundo18 mundo = (Mundo18) m;
        var player_pos = mundo.get_player_pos_block();
        Double posiciones[][] = {
            {1.0, -1.0},
            {1.0, 1.0},
            {-1.0, -1.0},
            {-1.0, 1.0},
            {1.0, 0.0},
            {-1.0, 0.0},
            {0.0, 1.0},
            {0.0, -1.0},
        };

        var paloma = iType.Paloma;
        for(var p : posiciones) {
            var new_pos = player_pos.copy().add(p[0], p[1]);
            if(mundo.get_pos_type(new_pos) == paloma) {
                return true;
            }
        }
        return false;
	}

}

