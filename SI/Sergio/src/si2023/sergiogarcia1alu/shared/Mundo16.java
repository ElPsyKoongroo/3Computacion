package si2023.sergiogarcia1alu.shared;

import core.game.StateObservation;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.mente.MundoBasics;
import tools.Vector2d;

public class Mundo16 extends MundoBasics<Mundo16.iType> implements Mundo {
    public static enum iType {
        Down,
        Goal,
        Invalid,
        Left,
        Nothing,
        Player,
        Right,
        Tree,
        Up,
        Water
    }

    public static final int JUGADOR = 9;

    /// Copy constructor mejor que clone.
    /// see:
    /// https://www.doc-developpement-durable.org/file/Projets-informatiques/cours-&-manuels-informatiques/java/Effective%20Java,%202nd%20Edition.pdf
    public Mundo16(Mundo16 other) {
        this.map = other.map;
        if (other.player_position == null || other.grid == null) {
            return;
        }

        this.vacio = iType.Nothing;

        this.player_position = other.player_position.copy();
        this.block_size = other.block_size;
        this.grid = new iType[other.grid.length][other.grid.length];
        for (int i = 0; i < other.grid.length; i++) {
            for (int j = 0; j < other.grid[i].length; ++j) {
                this.grid[i][j] = other.grid[i][j];
            }
        }
    }

    public Mundo16(StateObservation state) {
        this.map = state.getObservationGrid().clone();
        this.vacio = iType.Nothing;
        this.grid = new iType[state.getObservationGrid().length][state.getObservationGrid()[0].length];
        this.actualizar(state);
    }

    @Override
    public void actualizar(StateObservation mundo) {
        this.set_grid(mundo);
        this.block_size = mundo.getBlockSize();
        this.player_position = mundo.getAvatarPosition();
    }

    public boolean is_andable(iType type) {
        // Mirar esquema de arriba
        switch (type) {
            case Tree:
            case Player:
            case Water:
            case Invalid:
                return false;
            case Right:
            case Left:
            case Down:
            case Up:
            case Goal:
            case Nothing:
                return true;
        }
        return false;
    }

    public boolean is_goal(Vector2d pos) {
        return this.get_pos_type(pos) == iType.Goal;
    }

    public boolean is_hostiable(iType t) {
        if ((t == iType.Tree) || (t == iType.Invalid)) {
            return true;
        }
        return false;
    }

    public boolean is_movimiento(iType type) {
        switch (type) {
            case Right:
            case Up:
            case Left:
            case Down:
                return true;
            default:
                return false;
        }
    }


    // Triquiñuela, pero no voy a poner la ñ por motivos logicos
    public Mundo16 triquinuela(Vector2d pos, iType t) {
        Mundo16 other = new Mundo16(this);
        other.grid[(int) pos.x][(int) pos.y] = t;
        return other;
    }

    @Override
    protected iType get_type(int type) {
        switch (type) {
            case 0:
                return iType.Tree;
            case 9:
                return iType.Player;
            case 7:
                return iType.Right;
            case 5:
                return iType.Down;
            case 15:
                return iType.Goal;
            case 3:
                return iType.Water;
            case 8:
                return iType.Left;
            case 6:
                return iType.Up;
            default:
                return iType.Nothing;
        }
    }

}
