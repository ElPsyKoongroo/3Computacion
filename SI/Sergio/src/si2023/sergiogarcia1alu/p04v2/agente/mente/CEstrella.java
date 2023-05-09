package si2023.sergiogarcia1alu.p04v2.agente.mente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import si2023.sergiogarcia1alu.shared.utils.*;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.p04v2.agente.nodo.Estado;
import si2023.sergiogarcia1alu.shared.Mundo16;
import si2023.sergiogarcia1alu.shared.utils.Polleable;
import tools.Vector2d;

// Todos sabemos que C es mejor lenguaje que A y que B
public class CEstrella {

    public static enum Recorrido {
        Profundidad,
        Anchura
    }

    class Pair {
        private final double X;
        private final double Y;

        Pair(double x, double y) {
            this.X = x;
            this.Y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null)
                return false;
            if (this.getClass() != o.getClass())
                return false;
            Pair other = (Pair) o;
            return this.X == other.X && this.Y == other.Y;
        }

        @Override
        public int hashCode() {
            return (int) (this.X * 100 + this.Y);
        }
    }

    private final Map<Vector2d, ACTIONS> traductor = new HashMap<Vector2d, ACTIONS>();
    private Recorrido tipo;


    public CEstrella(Mundo16 mundo) {
        this.tipo = Recorrido.Anchura;
        this.set_acciones();
    }
    
    public CEstrella(Mundo16 mundom, Recorrido t) {
        this.tipo = t; 
        this.set_acciones();
    }

    private void set_acciones() {
       this.traductor.put(Estado.DOWN, ACTIONS.ACTION_DOWN);
       this.traductor.put(Estado.UP, ACTIONS.ACTION_UP);
       this.traductor.put(Estado.RIGHT, ACTIONS.ACTION_RIGHT);
       this.traductor.put(Estado.LEFT, ACTIONS.ACTION_LEFT);
    }

    private Stack<ACTIONS> get_camino(Estado e) {
        Stack<ACTIONS> acciones = new Stack<>();
        while (e.parent != null) {
            acciones.add(this.traductor.get(e.accion));
            e = e.parent;
        }
        return acciones;
    }

    private Pair make_pair(Vector2d pos) {
        return new Pair(pos.x, pos.y);
    }

    public Stack<ACTIONS> busqueda(Estado inicial, Vector2d fin) {
        // ArrayDeque<Estado> abiertos = new ArrayDeque<>();

        Polleable<Estado> abiertos;
        if (this.tipo == Recorrido.Anchura) abiertos = new Queue<Estado>();
        else abiertos = new Stack<Estado>();
        
        Map<Pair, Integer> cerrados = new HashMap<>();
        ArrayList<Stack<ACTIONS>> rutas = new ArrayList<>();

        abiertos.add(inicial);
        cerrados.put(this.make_pair(inicial.get_player_pos()), 0);
        while (!abiertos.isEmpty()) {
            Estado estado_actual = abiertos.poll();

            if (estado_actual.get_player_pos().equals(fin)) {
                rutas.add(this.get_camino(estado_actual));
            } else {
                cerrados.put(this.make_pair(estado_actual.get_player_pos()), estado_actual.movimientos);
            }

            ArrayList<Estado> sucesores = estado_actual.genera_sucesores();
            for (Estado s : sucesores) {
                Pair x = this.make_pair(s.get_player_pos());
                if (!cerrados.containsKey(x)) {
                    abiertos.add(s);
                } else if (cerrados.get(x) > s.movimientos) {
                    abiertos.add(s);
                }
            }

        }
        
        System.out.println("Rutas: " + rutas.size());
        for(Stack<ACTIONS> ruta: rutas) {
            System.out.println("\tRuta: " + ruta.size());
        }
        System.out.println("====================================\n");
        return rutas.get(0);
    }
}
