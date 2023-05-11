package si2023.sergiogarcia1alu.strips;

import si2023.sergiogarcia1alu.shared.utils.Polleable;
import si2023.sergiogarcia1alu.shared.utils.Queue;
import si2023.sergiogarcia1alu.shared.utils.Stack;
import si2023.sergiogarcia1alu.shared.utils.StripsStack;

import java.util.ArrayList;
import java.util.HashSet;

public class Strips {

    public enum TipoRecorrido {
        Anchura,
        Profundidad
    }

    // Echo de menos los Enums algebraicos de Rust :(
    /**
    * Este enum indica lo que va sucediendo cuando se comprueban las metas, si hay:<br><br>
    *   Bucle           => Si hay Bucle<br>
    *   NuevosEstados   => Se han generado nuevos estados<br>
    *   Igual           => Si no se han generado nuevos estados<br>
    *   CumpleMeta      => Si se cumple la meta<br><br>
    *
    * De esta forma puedo controlar lo que ocurre facilmente.
    */
    private enum EstadoMeta {
        Bucle,
        NuevosEstados,
        Igual,
        CumpleMeta
    }

    /*
    *
    * La interfaz Polleable tiene como ventaja la facil sustitucion de una Queue/Stack para
    * cambiar el tipo de recorrido Anchura/Profundidad.
    *
    * */
    private final Polleable<StripsState> estados;

    private final HashSet<StripsState> visitados = new HashSet<>();
    private final ArrayList<Operador> acciones_disponibles;
    static ArrayList<Meta> objetivo_meta = new ArrayList<>();
    public ArrayList<Operador> solucion = new ArrayList<>();

    private int n_visitas = 0;

    public Strips(StripsState estado_inicial, ArrayList<Operador> acciones, ArrayList<Meta> meta, TipoRecorrido t) {

        if (t == TipoRecorrido.Anchura) {
            this.estados = new Queue<>();
        } else {
            this.estados = new Stack<>();
        }

        this.estados.add(estado_inicial);

        this.acciones_disponibles = new ArrayList<>();
        this.acciones_disponibles.addAll(acciones);
        objetivo_meta = meta;
    }

    private boolean hay_bucle(StripsState estado_actual, Meta meta_actual) {
        // Para una solucion optima en profundidad toquetear un poco estas linea
        // el valor tiene que ser (2^n) - 1. Este es el numero de movimientos
        // optimos para el juego "Torres de Hanoi"

        // boolean solucion_bucle = estado_actual.get_solucion().size() > 20;
        return estado_actual.contiene_meta(meta_actual);
    }

    private EstadoMeta meta_simple(final StripsState estado_actual, Meta meta_actual) {
        if (estado_actual.cumple(meta_actual)) {
            return EstadoMeta.CumpleMeta;
        }
        if (hay_bucle(estado_actual, meta_actual)) {
            return EstadoMeta.Bucle;
        }

        ArrayList<StripsState> siguientes_estados = new ArrayList<>();


        for (Operador acc : this.acciones_disponibles) {
            ArrayList<Operador> posibilidades = acc.gen_posibilidades(meta_actual, estado_actual);
            for (Operador pos : posibilidades) {
                StripsState copia = new StripsState(estado_actual);
                copia.add_accion(pos);
                siguientes_estados.add(copia);
            }
        }

        if (siguientes_estados.isEmpty())
            return EstadoMeta.Igual;

        this.estados.addAll(siguientes_estados);
        return EstadoMeta.NuevosEstados;
    }

    private EstadoMeta meta_compuesta(StripsState estado_actual, ConjuncionMeta meta_actual) {
        if (estado_actual.cumple(meta_actual)) {
            return EstadoMeta.CumpleMeta;
        }


        if (estado_actual.contiene_meta(meta_actual)) {
            return EstadoMeta.Bucle;
        }


        ArrayList<IStackeable> copia = new ArrayList<>(meta_actual.get_recursos());

        ArrayList<ArrayList<IStackeable>> permutaciones = this.generatePerm(copia);

        for (ArrayList<IStackeable> permutacion : permutaciones) {
            StripsState nuevo_estado = new StripsState(estado_actual);
            nuevo_estado.get_stack_objetivos().poll();
            nuevo_estado.add_metas(permutacion);
            this.estados.add(nuevo_estado);
        }
        return EstadoMeta.NuevosEstados;

    }

    /*
     *
     * Devuelve un EstadoMeta indicando si se ha hecho algo.
     *
     */
    private EstadoMeta calcula_metas(final StripsState estado_actual, final IStackeable meta_actual) {
        if (meta_actual.getClass() != ConjuncionMeta.class) {
            return this.meta_simple(estado_actual, (Meta) meta_actual);
        } else {
            return this.meta_compuesta(estado_actual, (ConjuncionMeta) meta_actual);
        }
    }

    private void prueba_estado(final StripsState estado_actual) {
        IStackeable elemento = estado_actual.get_stack_objetivos().peek();

        if (elemento.is_accion()) {
            Operador operador_actual = (Operador) elemento;
            StripsState copia;
            if (estado_actual.es_ejecutable(operador_actual)) {
                copia = operador_actual.aplica_accion(estado_actual);
            } else {
                copia = operador_actual.add_prerequisitos(estado_actual);
            }
            this.estados.add(copia);
        } else {
            final EstadoMeta program_state = this.calcula_metas(estado_actual, elemento);
            if (program_state == EstadoMeta.CumpleMeta) {
                final StripsState copia = new StripsState(estado_actual);
                copia.get_stack_objetivos().poll();
                this.estados.add(copia);
            }
        }

    }

    public void resolver() {
        final long start = System.currentTimeMillis();
        int n_estados = 0;
        int repetidos = 0;
        while (!estados.isEmpty()) {
            StripsState estado_actual = estados.poll();

            if (n_estados == 60){
                System.out.println('-');
            }

            if ( this.es_meta(estado_actual) ) {
                final long end = System.currentTimeMillis();

                System.out.println("Meta: " + n_estados + " || visitas: " + n_visitas + " || repes: " + repetidos);
//                for (final Operador a : estado_actual.get_solucion()) {
//                    System.out.println(a);
//                }
                System.out.println("Solucion: " + (end - start) + "ms");
                this.solucion = estado_actual.get_solucion();
                return;
            }

            n_estados++;

            if (visitados.contains(estado_actual)) {
                repetidos++;
                continue;
            }
            this.visitados.add(estado_actual);

            this.n_visitas++;

            this.prueba_estado(estado_actual);
        }
        System.out.println("No hemos resuelto nada, iteraciones: " + n_estados);
    }

    private boolean es_meta(StripsState estado) {
        return objetivo_meta.stream().allMatch(estado::cumple);
    }

    /*
    *
    *
    * Codigo copiado enteramente de StackOverflow. 0 vergüenza
    *
    *
    * */
    private <T> ArrayList<ArrayList<T>> generatePerm(ArrayList<T> original) {
        if (original.isEmpty()) {
            ArrayList<ArrayList<T>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        T firstIntegerlement = original.remove(0);
        ArrayList<ArrayList<T>> returnValue = new ArrayList<>();
        ArrayList<ArrayList<T>> permutations = generatePerm(original);
        for (ArrayList<T> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                ArrayList<T> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstIntegerlement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }
}
