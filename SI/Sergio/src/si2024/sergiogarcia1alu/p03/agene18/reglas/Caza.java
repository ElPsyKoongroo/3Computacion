package si2024.sergiogarcia1alu.p03.agene18.reglas;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Accion;
import si2024.sergiogarcia1alu.shared.Mundo18;
import si2024.sergiogarcia1alu.shared.Mundo18.iType;
import tools.Vector2d;

public class Caza implements Accion {

	public Caza() {}

    private Vector2d right(Vector2d v) {
        return v.copy().add(1.0, 0.0);
    }
    private Vector2d left(Vector2d v) {
        return v.copy().add(-1.0, 0.0);
    }
    private Vector2d up(Vector2d v) {
        return v.copy().add(0.0, -1.0);
    }
    private Vector2d down(Vector2d v) {
        return v.copy().add(0.0, 1.0);
    }



    @Override
    public ACTIONS do_action(Mundo mundo){
        Mundo18 m = (Mundo18) mundo;


        var player_pos = m.get_player_pos_block();


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

        var r = player_pos.copy().add(1.0, 0.0);
        var l = player_pos.copy().add(-1.0, 0.0);  
        var d = player_pos.copy().add(0.0, 1.0);  
        var u = player_pos.copy().add(0.0, -1.0);  

        if(m.get_pos_type(r) == paloma){
            return ACTIONS.ACTION_RIGHT;
        }
        if(m.get_pos_type(l) == paloma){
            return ACTIONS.ACTION_LEFT;
        }
        if(m.get_pos_type(u) == paloma){
            return ACTIONS.ACTION_UP;
        }
        if(m.get_pos_type(d) == paloma){
            return ACTIONS.ACTION_DOWN;
        }

        var ru = player_pos.copy().add(1.0, -1.0);
        var rd = player_pos.copy().add(1.0, 1.0);
        var lu = player_pos.copy().add(-1.0, -1.0);
        var ld = player_pos.copy().add(-1.0, 1.0);

        if (m.get_pos_type(ru) == paloma){

            if (m.movable(this.up(player_pos))) {
                return ACTIONS.ACTION_UP;
            } else {
                return ACTIONS.ACTION_RIGHT;
            }

        }

        if (m.get_pos_type(rd) == paloma){
            if (m.movable(this.down(player_pos))) {
                return ACTIONS.ACTION_DOWN;
            } else {
                return ACTIONS.ACTION_RIGHT;
            }
        }

        if (m.get_pos_type(lu) == paloma){

            if (m.movable(this.up(player_pos))) {
                return ACTIONS.ACTION_UP;
            } else {
                return ACTIONS.ACTION_LEFT;
            }
        }
        if (m.get_pos_type(ld) == paloma){

            if (m.movable(this.down(player_pos))) {
                return ACTIONS.ACTION_DOWN;
            } else {
                return ACTIONS.ACTION_LEFT;
            }
        }

        return ACTIONS.ACTION_NIL;
    }
}
