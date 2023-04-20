package si2023.sergiogarcia1alu.p04;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.p04.agente.mente.CEstrella;
import si2023.sergiogarcia1alu.p04.agente.mente.CEstrella.Recorrido;
import si2023.sergiogarcia1alu.p04.agente.nodo.Estado;
import si2023.sergiogarcia1alu.shared.Mundo50;
import si2023.sergiogarcia1alu.shared.Mundo50.iType;
import si2023.sergiogarcia1alu.shared.utils.Stack;
import tools.ElapsedCpuTimer;
import tools.Vector2d;

// Si no funciona bien es por que le falta oligoelementos <3
public class AgenteSuperInteligente extends AbstractPlayer {
    String[][] map;
    Stack<ACTIONS> acciones;
    private static final int JUGADOR_NORMAL = 1;
    private int contador = 0;

    public AgenteSuperInteligente(StateObservation state, ElapsedCpuTimer timer) {
        Mundo50 mundo = new Mundo50(state);

        CEstrella estrellita = new CEstrella(mundo, Recorrido.Anchura);

        Vector2d meta = mundo.get_pos_iType(iType.Goal).get(0);
        Vector2d player_pos = mundo.get_player_pos_block();

        Estado inicial = new Estado(mundo, player_pos, null, null, 0, 5);

        this.acciones = estrellita.busqueda(inicial, meta);
        mundo.print_map();
//        print_map();
    }

    @Override
    public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {

        // Descomentar esto para que se muestre el mapa por consola.
        // print_map();
        while (!this.acciones.isEmpty()) {
            int a = stateObs.getAvatarType();
            if (a == JUGADOR_NORMAL) {
                ACTIONS ac = this.acciones.poll();
                return ac;
            }
            return ACTIONS.ACTION_NIL;
        }
        return ACTIONS.ACTION_NIL;
    }
}
