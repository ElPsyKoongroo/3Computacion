package si2024.sergiogarcia1alu.shared;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import si2023.sergiogarcia1alu.ia.mente.Mundo;
import si2023.sergiogarcia1alu.ia.mente.MundoBasics;
import tools.Vector2d;

public class Mundo18 extends MundoBasics2<Mundo18.iType> implements Mundo {

	private boolean mundo_ok;
	private ArrayList<Observation> enemigos = new ArrayList();
	public static enum iType {
		Player,
		Palmera,
		Serpiente,
		Paloma,
		Cuervo,
		Nothing
	}
	
	public Mundo18(StateObservation state) {
		this.map = state.getObservationGrid().clone();
        this.vacio = iType.Nothing;
        this.grid = new ArrayList[state.getObservationGrid().length][state.getObservationGrid()[0].length];
        this.actualizar(state);
	}
	
	public Mundo18(Mundo18 other) {
		this.map = other.map;
		if (other.player_position == null) {
			this.mundo_ok = false;
			return;
		}

		this.mundo_ok = true;
		this.player_position = other.player_position.copy();
		this.block_size = other.block_size;

		// Me parece desagradable que si clono un tipo T me devuelva
		// un Object en vez de T.
		
		// Esto esta regular ya que hace una shallow copy y no un deep cope. 
		// Observation no implementa cloneable ni tiene un copy method.
	}

	@Override
	public void actualizar(StateObservation mundo) {
		this.enemigos.clear();
		this.set_grid(mundo);

		ArrayList<Observation>[] cosas_movibles = mundo.getNPCPositions();

		this.block_size = mundo.getBlockSize();
		this.player_position = mundo.getAvatarPosition();

		/*
		for (int x = 0; x < mundo.getObservationGrid().length; x++) {
			for (int y = 0; y < mundo.getObservationGrid()[x].length; y++) {
				for (Observation o : mundo.getObservationGrid()[x][y]) {
					if (o.itype ==  || o.itype == 18) {
					
					}
				}
			}
		}
		*/

		// Si no hay objetos moviles no añadimos nada a enemigos
		if (cosas_movibles == null)
			return;

		for (ArrayList<Observation> cosas_movible : cosas_movibles) {
			for (int j = 0; j < cosas_movible.size(); j++) {
				if (cosas_movible.get(j).itype == 12) {
					enemigos.add(cosas_movible.get(j));
				}
			}
		}


	}

    /*
	public void set_grid(StateObservation m) {
        ArrayList<Observation>[][] estados = m.getObservationGrid();
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.map[i][j] = estados[i][j];
                if (estados[i][j].size() == 0) {
                    grid[i][j] = this.vacio;
                } else {
                    this.set_type(i,j, estados[i][j].get(0).itype);
                    
                    for (Observation o : estados[i][j]) {
                        this.set_type(i, j, o.itype);
                    }
                }
            }
        }
	}
    */
	

	@Override
	protected iType get_type(int type) {
		// TODO Auto-generated method stub
		switch (type) {
		case 1: return Mundo18.iType.Player;
		case 0: return Mundo18.iType.Palmera;
		case 6: return Mundo18.iType.Paloma;
		case 5: return Mundo18.iType.Cuervo;
		default: return Mundo18.iType.Nothing;
		}
	}

    public boolean movable(Vector2d where) {
        for(var obj: this.get_pos_types(where)) {
            if(obj == iType.Palmera) {
                return false;
            }
        }
        return true;
    }
	
	public boolean ok() {
		return true;
	}
}
