package si2023.sergiogarcia1alu.shared;

import core.game.StateObservation;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.mente.MundoBasics;
import tools.Vector2d;

public class Mundo50 extends MundoBasics<Mundo50.iType> implements Mundo {

    public enum iType {
        Goal,
        Nothing,
        Oligo,
        Player,
        Tree
    }

    public static final int JUGADOR = 9;

    /// Copy constructor mejor que clone.
    /// see:
    /// https://www.doc-developpement-durable.org/file/Projets-informatiques/cours-&-manuels-informatiques/java/Effective%20Java,%202nd%20Edition.pdf
    public Mundo50(Mundo50 other) {
        this.map = other.map;
        this.vacio = other.vacio;
        if (other.player_position == null || other.grid == null) {
            //this.mundo_ok = false;
            return;
        }

        // this.mundo_ok = true;
        this.player_position = other.player_position.copy();
        this.block_size = other.block_size;
        this.grid = new iType[other.grid.length][other.grid.length];
        for (int i = 0; i < other.grid.length; i++) {
            for (int j = 0; j < other.grid[i].length; ++j) {
                this.grid[i][j] = other.grid[i][j];
            }
        }
    }

    public Mundo50(StateObservation state) {
        this.vacio = iType.Nothing;
        this.map = state.getObservationGrid().clone();
        this.grid = new iType[state.getObservationGrid().length][state.getObservationGrid()[0].length];
        this.actualizar(state);
    }

    @Override
    public void actualizar(StateObservation mundo) {
        this.set_grid(mundo);
        this.block_size = mundo.getBlockSize();
        this.player_position = mundo.getAvatarPosition();
    }

    public boolean is_andable(iType t) {
        switch (t) {
            case Nothing:
                return true;
            case Goal:
                return true;
            case Oligo:
                return true;
            default:
                return false;
        }
    }

    public boolean is_goal(Vector2d pos) {
        return this.get_pos_type(pos) == iType.Goal;
    }
    
    public boolean hay_conflicto(iType t) {
        return t == iType.Oligo;
    }

    protected iType get_type(int type) {
        // Mirar esquema de arriba
        switch (type) {
            case 0:
                return iType.Tree;
            case 3:
                return iType.Oligo;
            case 1:
                return iType.Player;
            case 4:
                return iType.Goal;
            default:
                return iType.Nothing;
        }
    }
    
    public Mundo50 triquinuela(Vector2d pos, iType t) {
        Mundo50 other = new Mundo50(this);
        other.grid[(int) pos.x][(int) pos.y] = t;
        return other;
    }
}
