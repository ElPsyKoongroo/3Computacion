package si2023.sergiogarcia1alu.p04.agente.nodo;

import java.util.ArrayList;
import java.util.Arrays;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.shared.Mundo50;
import si2023.sergiogarcia1alu.shared.Mundo50.iType;
import si2023.sergiogarcia1alu.shared.utils.Queue;
import tools.Vector2d;

public class Estado {
    public final static Vector2d DOWN = new Vector2d(0, 1);
    public final static Vector2d LEFT = new Vector2d(-1, 0);
    public final static Vector2d NIL = new Vector2d(0,0);
    public final static Vector2d RIGHT = new Vector2d(1, 0);
    public final static Vector2d UP = new Vector2d(0, -1);
    private Vector2d accion;
    private Queue<Vector2d> movs;

    private Mundo50 mundo;
    private final ArrayList<Vector2d> operadores = new ArrayList<Vector2d>(Arrays.asList(
            DOWN, LEFT, RIGHT, UP));
    private Vector2d player_pos;
    private final int MOVIMIENTOS_POR_VIDA = 16;
    public int movimientos;
    public Estado parent;

    public int vida;

    public Estado(Mundo50 m, Vector2d pp, Estado parent, Vector2d acc, int mov, int vida) {
        this.movs = new Queue<>();
        this.parent = parent;
        this.mundo = m;
        this.player_pos = pp;
        this.accion = acc;
        if (acc != null)
            this.movs.add(acc);
        this.movimientos = mov;
        this.vida = vida;
        if (mov%MOVIMIENTOS_POR_VIDA == 0)
            this.vida--;
    }

    private void resuelve_conflictos(Vector2d current_pos) {
        this.movs.add(NIL);
        this.vida = 5;
        this.mundo = mundo.triquinuela(current_pos, iType.Nothing);
    }
    
    private ArrayList<Estado> sigue_sucesores(ArrayList<Estado> sucesores) {
        ArrayList<Estado> estados_finales = new ArrayList<>();
//        Mundo50 cp = new Mundo50(this.mundo);
        for (Estado sucesor : sucesores) {
            Vector2d current_pos = sucesor.player_pos;
            
            if (mundo.hay_conflicto(mundo.get_pos_type(current_pos))) {
                this.resuelve_conflictos(current_pos);
            }
            
            if (mundo.is_andable(mundo.get_pos_type(current_pos))) {
                estados_finales.add(new Estado(mundo, current_pos.copy(), this, sucesor.accion, this.movimientos+1, this.vida));
            }
//            this.mundo = new Mundo50(cp);
        }
        return estados_finales;
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
                sucesores.add(new Estado(this.mundo, next_posicion, this, movimiento, this.movimientos+1, this.vida));
            }
        }
        return sigue_sucesores(sucesores);
    }

    public Vector2d get_player_pos() {
        return this.player_pos;
    }
    
    
    public double get_vida() {
        return (double) this.vida - (double)(this.movimientos%MOVIMIENTOS_POR_VIDA)/10;
    }
    
    public Vector2d movimiento() {
        return this.movs.poll();
    }
    

}
