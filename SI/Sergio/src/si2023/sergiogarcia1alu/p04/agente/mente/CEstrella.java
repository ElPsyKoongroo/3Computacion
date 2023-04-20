package si2023.sergiogarcia1alu.p04.agente.mente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.DoubleAccumulator;

import si2023.sergiogarcia1alu.shared.utils.*;

import ontology.Types.ACTIONS;
import si2023.sergiogarcia1alu.p04.agente.nodo.Estado;
import si2023.sergiogarcia1alu.shared.Mundo50;
import si2023.sergiogarcia1alu.shared.utils.Polleable;
import tools.Vector2d;

// Todos sabemos que C es mejor lenguaje que A y que B
public class CEstrella {

    public static enum Recorrido {
        Anchura,
        Profundidad
    }

    class Pair {
        private final double X;
        private final double Y;

        Pair(double x, double y) {
            this.X = x;
            this.Y = y;
        }
        
        Pair(int x, double y) {
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

    private Recorrido tipo;
    private final Map<Vector2d, ACTIONS> traductor = new HashMap<Vector2d, ACTIONS>();


    public CEstrella(Mundo50 mundo) {
        this.tipo = Recorrido.Anchura;
        traductor.put(Estado.DOWN, ACTIONS.ACTION_DOWN);
        traductor.put(Estado.UP, ACTIONS.ACTION_UP);
        traductor.put(Estado.RIGHT, ACTIONS.ACTION_RIGHT);
        traductor.put(Estado.LEFT, ACTIONS.ACTION_LEFT);
    }
    
    public CEstrella(Mundo50 mundom, Recorrido t) {
        this.tipo = t; 
        traductor.put(Estado.DOWN, ACTIONS.ACTION_DOWN);
        traductor.put(Estado.UP, ACTIONS.ACTION_UP);
        traductor.put(Estado.RIGHT, ACTIONS.ACTION_RIGHT);
        traductor.put(Estado.LEFT, ACTIONS.ACTION_LEFT);
        traductor.put(Estado.NIL, ACTIONS.ACTION_NIL);
    }

    private Stack<ACTIONS> get_camino(Estado e) {
        Stack<ACTIONS> acciones = new Stack<>();
        while (e.parent != null) {
            Vector2d a = e.movimiento();
            while(a != null) {
                acciones.add(this.traductor.get(a));
                a = e.movimiento();
            }
            e = e.parent;
        }
        return acciones;
    }

    private Pair make_pair(int x, double y) {
        return new Pair(x, y);
    }
    
    private Pair make_pair(Vector2d pos) {
        return new Pair(pos.x, pos.y);
    }

    public Stack<ACTIONS> busqueda(Estado inicial, Vector2d fin) {
        // ArrayDeque<Estado> abiertos = new ArrayDeque<>();

        Polleable<Estado> abiertos;
        if (this.tipo == Recorrido.Anchura) abiertos = new Queue<Estado>();
        else abiertos = new Stack<Estado>();
        
        Map<Pair, Pair> cerrados = new HashMap<>();
        ArrayList<Stack<ACTIONS>> rutas = new ArrayList<>();

        abiertos.add(inicial);
        cerrados.put(this.make_pair(inicial.get_player_pos()), new Pair(0, 5));
        while (!abiertos.isEmpty()) {
            Estado estado_actual = abiertos.poll();

            if (estado_actual.get_player_pos().equals(fin) && estado_actual.vida > 0) {
                rutas.add(this.get_camino(estado_actual));
            } else {
                cerrados.put(
                    this.make_pair(estado_actual.get_player_pos()), 
                    this.make_pair(estado_actual.movimientos, estado_actual.get_vida())
                );
            }

            ArrayList<Estado> sucesores = estado_actual.genera_sucesores();
            for (Estado s : sucesores) {
                Pair x = this.make_pair(s.get_player_pos());
                if (!cerrados.containsKey(x)) {
                    abiertos.add(s);
                } else if (cerrados.get(x).X > s.movimientos || cerrados.get(x).Y <= s.get_vida()) {
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
