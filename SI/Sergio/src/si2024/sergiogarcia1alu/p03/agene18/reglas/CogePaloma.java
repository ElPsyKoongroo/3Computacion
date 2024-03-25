package si2024.sergiogarcia1alu.p03.agene18.reglas;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.reglas.Accion;
import si2024.sergiogarcia1alu.shared.Mundo18;
import si2024.sergiogarcia1alu.shared.Mundo18.iType;
import tools.Vector2d;

public class CogePaloma implements Accion {

	public CogePaloma() {
	}

	private Vector2d busca_paloma(Mundo18 m) {
		var player_pos = m.get_player_pos_block();
		var palomas = m.get_pos_iType(iType.Paloma);

		var closest = player_pos.dist(palomas.get(0));
		var closest_pal = palomas.get(0);

		for (Vector2d paloma : palomas) {
			var dist = player_pos.dist(paloma);
            var objs = m.get_pos_types(paloma);

            long n_palomas = objs.stream().filter(e -> e == iType.Paloma).count();

    
			if (dist < closest && n_palomas < 2) {
				closest = dist;
				closest_pal = paloma;
			}
		}
		return closest_pal;
	}

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
    
    
    private int how(Mundo18 m, Vector2d target) {
    	
    	Vector2d hunt_pos[][] = {
    			{new Vector2d(-1.0, 0.0), new Vector2d(-1.0, 1.0)},
    			{new Vector2d(-1.0, 0.0), new Vector2d(-1.0, -1.0)},
    			{new Vector2d(1.0, 0.0), new Vector2d(1.0, -1.0)},
    			{new Vector2d(1.0, 0.0), new Vector2d(1.0, 1.0)},
    	};
    	
    	
    	var good = true;
    	
    	for(var positions : hunt_pos) {
    		good = true;
    		for(var pos : positions) {
    			if (!m.movable(target.copy().add(pos))) {
    				good = false;
    				break ;
    			}
    		}
    		
    		if (good) {
    			System.out.println("a");
    		}
    	}
    	
    	return 0;
    }


    /*
     * Hay que moverse en diagonal.
     *
     * */
	private ACTIONS next_move(Mundo18 m, Vector2d target) {
		var player_pos = m.get_player_pos_block();

        var left_down = target.copy().add(new Vector2d(-1.0,-1.0));
        var right_down = target.copy().add(new Vector2d(1.0, -1.0));

        var left_up = target.copy().add(new Vector2d(-1.0, 1.0));
        var right_up = target.copy().add(new Vector2d(1.0, 1.0));


        Vector2d go_pos = null;
        
        var x = this.how(m, target);

        if (m.movable(left_down)) {
            go_pos = left_down;
        } else if (m.movable(right_down)) {
            go_pos = right_down;
        } else if (m.movable(left_up)) {
            go_pos = left_up;
        } else {
            go_pos = right_up;
        }

        var pos = player_pos;
        if (go_pos.x > pos.x && m.movable(this.right(pos))) {
            return ACTIONS.ACTION_RIGHT;
        }
        if (go_pos.x < pos.x && m.movable(this.left(pos))) {
            return ACTIONS.ACTION_LEFT;
        }

        if (go_pos.y < pos.y && m.movable(this.up(pos))) {
            return ACTIONS.ACTION_UP;
        }
        if (go_pos.y > pos.y && m.movable(this.down(pos))) {
            return ACTIONS.ACTION_DOWN;
        }


        var dimensions = m.get_dimensions();
        var cx = dimensions[0] / 2;
        var cy = dimensions[1] / 2;

        System.out.println("Ir al centro");
        if (cx > pos.x) {
            return ACTIONS.ACTION_RIGHT;
        }
        if (cx < pos.x) {
            return ACTIONS.ACTION_LEFT;
        }

        if (cy > pos.x) {
            return ACTIONS.ACTION_UP;
        }
        if (cy < pos.x) {
            return ACTIONS.ACTION_DOWN;
        }
       
        return ACTIONS.ACTION_NIL;

        /*
        class State {
            public Vector2d pos;
            public ArrayDeque<ACTIONS> movs;

            public State(Vector2d p){ this.pos = p; this.movs = new ArrayDeque<>();}
        };

        ArrayDeque<State> positions = new ArrayDeque();
        positions.add(new State(player_pos));


        while (!positions.isEmpty()) {
            var next = positions.pop();
            if(next.pos.equals(go_pos)) {
                System.out.println("Done");
                return ACTIONS.ACTION_NIL;
            }

            var pos = next.pos;
            if (m.movable(this.right(pos))) {
                positions.add(new State(this.right(pos)));
            }

            if (m.movable(this.left(pos))) {
                positions.add(new State(this.left(pos)));
            }

            if (m.movable(this.up(pos))) {
                positions.add(new State(this.up(pos)));
            }

            if (m.movable(this.down(pos))) {
                positions.add(new State(this.down(pos)));
            }
        }
        return ACTIONS.ACTION_NIL;
        */

	}

	@Override
	public ACTIONS do_action(Mundo m) {
		var p = busca_paloma((Mundo18) m);
		var nm = next_move((Mundo18) m, p);
		return nm;
	}

}
