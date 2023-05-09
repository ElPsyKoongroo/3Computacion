package si2023.sergiogarcia1alu.p04v2.agente.nodo;

import java.util.ArrayList;
import java.util.Arrays;

import si2023.sergiogarcia1alu.shared.Mundo16;
import si2023.sergiogarcia1alu.shared.Mundo16.iType;
import tools.Vector2d;

public class Estado {
    private Vector2d player_pos;
    private Mundo16 mundo;
    public Estado parent;
    public Vector2d accion;
    public int movimientos;

    public final static Vector2d DOWN = new Vector2d(0, 1);
    public final static Vector2d RIGHT = new Vector2d(1, 0);
    public final static Vector2d LEFT = new Vector2d(-1, 0);
    public final static Vector2d UP = new Vector2d(0, -1);

    private final ArrayList<Vector2d> operadores = new ArrayList<Vector2d>(Arrays.asList(
            DOWN, LEFT, RIGHT, UP));

    public Estado(Mundo16 m, Vector2d pp, Estado parent, Vector2d acc, int mov) {
        this.parent = parent;
        this.mundo = m;
        this.player_pos = pp;
        this.accion = acc;
        this.movimientos = mov;
    }

    public Vector2d get_player_pos() {
        return this.player_pos;
    }

    public ArrayList<Estado> genera_sucesores() {
        ArrayList<Estado> sucesores = new ArrayList<>();
        for (Vector2d movimiento : this.operadores) {
            Vector2d next_posicion = this.player_pos.copy().add(movimiento);
            if (!mundo.is_in_range(next_posicion) || mundo.is_goal(this.player_pos))
                continue;
            iType siguiente_casilla = this.mundo.get_pos_type(
                    (int) next_posicion.x, (int) next_posicion.y
            );

            if (this.mundo.is_andable(siguiente_casilla)) {
                sucesores.add(new Estado(this.mundo, next_posicion, this, movimiento, this.movimientos+1));
            }
        }
        return sigue_sucesores(sucesores);
    }

    private Vector2d resuelve_conflictos(Vector2d current_pos) {
        iType t = this.mundo.get_pos_type(current_pos);
        mundo = mundo.triquinuela(current_pos, iType.Nothing);
        Vector2d inercia = new Vector2d(0, 0);
        boolean cambiado = true;
        while (cambiado || this.mundo.get_pos_type(current_pos) != iType.Goal 
                && !this.mundo.is_hostiable(this.mundo.get_pos_type(current_pos.copy().add(inercia)))) {
            cambiado = false;
            switch (t) {
                case Down: {
                    inercia = DOWN;
                    break;
                }
                case Right: {
                    inercia = RIGHT;
                    break;
                }
                case Up: {
                    inercia = UP;
                    break;
                }
                case Left: {
                    inercia = LEFT;
                    break;
                }
                default: break;
            }
            current_pos.add(inercia);
            if (this.mundo.is_movimiento(this.mundo.get_pos_type(current_pos))) {
                t = this.mundo.get_pos_type(current_pos);
                mundo = mundo.triquinuela(current_pos, iType.Nothing);
                cambiado = true;
            }
        }
        return current_pos;
    }

    private boolean hay_conflicto(iType block_type) {
        return mundo.is_movimiento(block_type);
    }
    
    private ArrayList<Estado> sigue_sucesores(ArrayList<Estado> sucesores) {
        ArrayList<Estado> estados_finales = new ArrayList<>();
        Mundo16 cp = new Mundo16(this.mundo);
        for (Estado sucesor : sucesores) {
            iType block_type = mundo.get_pos_type(sucesor.player_pos);

            Vector2d current_pos = sucesor.player_pos;

            if (this.hay_conflicto(block_type)) {
                current_pos = resuelve_conflictos(current_pos);
            }

            if (mundo.is_andable(mundo.get_pos_type(current_pos))) {
                Mundo16 other = mundo.triquinuela(player_pos, iType.Nothing);
                estados_finales.add(new Estado(other, current_pos.copy(), this, sucesor.accion, this.movimientos+1));
            }
            this.mundo = new Mundo16(cp);
        }
        return estados_finales;
    }

}
