package si2023.sergiogarcia1alu.shared;

import core.game.StateObservation;
import si2023.sergiogarcia1alu.ia.mente.MundoBasics;

public class Mundo04 extends MundoBasics<Mundo04.iType> {
    public static enum iType {
        Pared,
        Puerta,
        Player,
        BloqueMovible,
        Llave,
        Seta,
        Vacio,
        Nothing,
    }

    public Mundo04(StateObservation state) {
        this.map = state.getObservationGrid().clone();
        this.vacio = Mundo04.iType.Nothing;
        this.grid = new Mundo04.iType[state.getObservationGrid().length][state.getObservationGrid()[0].length];
        this.actualizar(state);
    }

    public void actualizar(StateObservation mundo) {
        this.set_grid(mundo);
        this.block_size = mundo.getBlockSize();
        this.player_position = mundo.getAvatarPosition();
    }
    @Override
    protected iType get_type(int type) {
        switch (type) {
            case 0: return iType.Pared;
            case 8: return iType.Puerta;
            case 4: return iType.Player;
            case 9: return iType.BloqueMovible;
            case 7: return iType.Llave;
            case 6: return iType.Seta;
            case 3: return iType.Vacio;
            default: return iType.Nothing;
        }
    }
}
