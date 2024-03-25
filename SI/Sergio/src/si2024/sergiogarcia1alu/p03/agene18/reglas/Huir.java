package si2024.sergiogarcia1alu.p03.agene18.reglas;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Accion;
import si2024.sergiogarcia1alu.shared.Mundo18;
import si2024.sergiogarcia1alu.shared.Mundo18.iType;
import tools.Vector2d;

public class Huir implements Accion {

    static final Vector2d adyacents[] = {
        new Vector2d(1.0, 0.0),
        new Vector2d(0.0, 1.0),
        new Vector2d(0.0, -1.0),
        new Vector2d(-1.0, 0.0),
    };

	public Huir() {
	}


    public Boolean good_pos(Mundo18 m, Vector2d pos) {

        if (!m.movable(pos)) return false;

        int c = 0;

        for(var ady : adyacents) {
            var x = pos.copy().add(ady);

            if (m.movable(x)) {
                c++;
            }
        }


        return c >= 2;
    }

    public ACTIONS do_action(Mundo m) {
        Mundo18 mundo = (Mundo18) m;

        Double posiciones[][] = {
            {1.0, 0.0},
            {-1.0, 0.0},
            {0.0, 1.0},
            {0.0, -1.0},
        };

        var paloma = iType.Paloma;

        var negros = mundo.get_pos_iType(iType.Cuervo);
        var player_pos = mundo.get_player_pos_block();


        Vector2d sum = player_pos.copy();

        for(var negro : negros) {
            sum = sum.subtract(negro);
        }

        iType smith = iType.Cuervo;
        var r = player_pos.copy().add(1.0, 0.0);
        var l = player_pos.copy().add(-1.0, 0.0);
        var u = player_pos.copy().add(0.0, -1.0);
        var d = player_pos.copy().add(0.0, 1.0);


        // Correr en sentido contrario al agente smith
        if(sum.x > 0 && mundo.movable(r) && mundo.get_pos_type(r) != smith) {
            return ACTIONS.ACTION_RIGHT;
        }

        if(sum.x < 0  && mundo.movable(l) && mundo.get_pos_type(l) != smith) {
            return ACTIONS.ACTION_LEFT;
        }

        if(sum.y < 0  && mundo.movable(u) && mundo.get_pos_type(u) != smith) {
            return ACTIONS.ACTION_UP;
        }

        if(sum.y > 0 && this.good_pos(mundo, d) && mundo.get_pos_type(d) != smith) {
            return ACTIONS.ACTION_DOWN;
        }


        // Correr por nuestra vida a la desesperada
        if(mundo.movable(l) && mundo.get_pos_type(l) != smith) {
            return ACTIONS.ACTION_LEFT;
        }
        if(mundo.movable(r) && mundo.get_pos_type(r) != smith) {
            return ACTIONS.ACTION_RIGHT;
        }

        if(mundo.movable(u) && mundo.get_pos_type(u) != smith) {
            return ACTIONS.ACTION_UP;
        }

        if(mundo.movable(d) && mundo.get_pos_type(d) != smith) {
            return ACTIONS.ACTION_DOWN;
        }


        return ACTIONS.ACTION_NIL;
    }
}
