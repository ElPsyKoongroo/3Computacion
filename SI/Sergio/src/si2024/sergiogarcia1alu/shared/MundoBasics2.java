package si2024.sergiogarcia1alu.shared;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import tools.Vector2d;

public abstract class MundoBasics2<T> {

    protected int block_size;
    protected ArrayList<T>[][] grid;
    protected ArrayList<Observation>[][] map;
    protected Vector2d player_position;
    protected T vacio;

    public MundoBasics2() {}

    public void set_type(int x, int y, int type) {
        this.grid[x][y].add(get_type(type));
    }

    public int get_block_size() {
        return this.block_size;
    }

    public Vector2d get_player_pos_block() {
        return new Vector2d(this.player_position.x / this.get_block_size(),
                this.player_position.y / this.get_block_size());
    }

    public ArrayList<Vector2d> get_pos_iType(T tipo) {
        ArrayList<Vector2d> pos = new ArrayList<>();
        for (int x = this.grid.length - 1; x >= 0; x--) {
            for (int y = this.grid[x].length - 1; y >= 0; y--) {
                for(T v: this.grid[x][y]) {
                    if (v == tipo) {
                        pos.add(new Vector2d(x, y));
                    }
                }
            }
        }
        return pos;
    }

    public int[] get_dimensions() {
        return new int[] { this.grid.length, this.grid[0].length };
    }

    public ArrayList<T>[][] get_grid() {
        return this.grid;
    }
    
    public ArrayList<T> get_pos_type(int x, int y) {
        return this.grid[x][y];
    }

    public boolean is_in_range(Vector2d pos) {
        return !((pos.x < 0 || pos.x > this.grid.length)
                || (pos.y < 0 || pos.y > this.grid[0].length));
    }

    public void print_map() {
        int y_size = map.length;
        int x_size = map[0].length;
        for (int i = 0; i < x_size; i++) {
            for (int j = 0; j < y_size; j++) {
                String c = map[j][i].size() > 0 ? Integer.toString(map[j][i].get(0).itype) : " ";
                String block = String.format("%3s", c);
                System.out.print(block);
            }
            System.out.println();
        }
        System.out.println("\n\n");
        System.out.println(
                "===========================================================================");
        System.out.println();
    }

    public void set_grid(StateObservation m) {
        ArrayList<Observation>[][] estados = m.getObservationGrid();
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.map[i][j] = new ArrayList(); //estados[i][j];
                this.grid[i][j] = new ArrayList(); //estados[i][j];
                if (estados[i][j].size() == 0) {
                    grid[i][j].add(this.vacio);
                } else {
                    // this.set_type(i,j, estados[i][j].get(0).itype);
                    for (Observation o : estados[i][j]) {
                        this.set_type(i, j, o.itype);
                    }
                }
            }
        }
    }

    public ArrayList<T> get_pos_types(Vector2d pos) {
        return this.grid[(int) pos.x][(int) pos.y];
    }

    public T get_pos_type(Vector2d pos) {
        return this.grid[(int) pos.x][(int) pos.y].get(0);
    }

    protected abstract T get_type(int type);

}
