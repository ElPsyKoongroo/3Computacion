package si2023.sergiogarcia1alu.strips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import si2023.sergiogarcia1alu.shared.utils.StripsStack;

public class StripsState {

    private ArrayList<Operador> solucion;
    private final HashMap<Integer,HashSet<Meta>> estado_actual;
    private final StripsStack<IStackeable> stack_objetivos;

    private Integer cache_hash = null;
    private boolean cached;
    public StripsState(ArrayList<Meta> ea, ArrayList<IStackeable> objetivos) {
        this.estado_actual = new HashMap<>();// new HashSet<>(ea);

        for (int i = 1; i < 10; i++) {
            estado_actual.put(i, new HashSet<>());
        }

        for (Meta estado : ea) {
            estado_actual.get(estado.type).add(estado);
        }


        this.stack_objetivos = new StripsStack<>();
        this.stack_objetivos.addAll(objetivos);
        this.solucion = new ArrayList<>();
        this.cached = false;
    }

    public StripsState(ArrayList<Meta> ea, ConjuncionMeta objetivos) {
        this.estado_actual = new HashMap<>();// new HashSet<>(ea);

        for (int i = 1; i < 10; i++) {
            estado_actual.put(i, new HashSet<>());
        }

        for (Meta estado : ea) {
            estado_actual.get(estado.type).add(estado);
        }

        this.stack_objetivos = new StripsStack<>();
        this.stack_objetivos.add(objetivos);
        this.solucion = new ArrayList<>();
        this.cached = false;
    }

    @SuppressWarnings({"unchecked", "CopyConstructorMissesField"})
    public StripsState(StripsState other) {
        this.solucion = new ArrayList<>();
        this.solucion = (ArrayList<Operador>) other.solucion.clone();

        this.estado_actual = (HashMap<Integer, HashSet<Meta>>) other.estado_actual.clone();
        this.stack_objetivos = new StripsStack<>(other.stack_objetivos);
        this.cached = false;
    }

    public ArrayList<Operador> get_solucion() {
        return solucion;
    }

    public HashMap<Integer, HashSet<Meta>> get_raw_estado_actual() {
        return this.estado_actual;
    }

    public HashMap<Integer, HashSet<Meta>> get_estado_actual() {
        return new HashMap<>(estado_actual);
    }

    public HashSet<Meta> get_raw_estado_actual_type(int type) {
        return this.estado_actual.get(type);
    }

    public HashSet<Meta> get_estado_actual_type(int type) {
        return new HashSet<>(estado_actual.get(type));
    }

    public StripsStack<IStackeable> get_stack_objetivos() {
        return this.stack_objetivos;
    }

    public boolean es_ejecutable(Operador a) {
        return a.precondiciones.stream().allMatch(precondicion ->{
            return this.estado_actual.get((precondicion).type).contains(precondicion);
        });
    }

    public void add_solucion(Operador a) {
        this.solucion.add(a);
    }

    /**
     * Añade a la lista de objetivos los pre requisitos una accion.
     * <p>
     * Aunque el algoritmo funcione esta funcion no es correcta. Para que sea
     * correcta descomentar la ultima linea y comentar las dos primeras.
     * <p> <br>
     *     Para el juego Torres de Hanoi no tiene importancia añadir las precondiciones
     *     como Conjunto, es mejor añadirlas como metas individuales en el orden correcto.
     * </p>
     */
    public void add_pre_requisitos(Operador a) {
        ConjuncionMeta c = new ConjuncionMeta(a.get_precondiciones());
        this.stack_objetivos.add(c);
//        this.stack_objetivos.addAll(a.get_precondiciones());
    }

    public void add_metas(ArrayList<IStackeable> metas) {
        this.stack_objetivos.addAll(metas);
    }

    public void add_objetivo(IStackeable obj) {
        this.stack_objetivos.add(obj);
    }

    public void add_accion(Operador a) {
        this.stack_objetivos.add(a);
    }

    /**
     * @param meta Meta que tiene que cumplirse
     * @return Devuelve true si el estado contiene la meta
     */
    public boolean cumple(Meta meta) {
        return this.estado_actual.get(meta.type).contains(meta);
    }

    /**
     *
     * @param metas Conjuncion de metas que tienen que cumplirse
     * @return Devuelve true si se cumplen todas las metas de la conjuncion
     */
    public boolean cumple(ConjuncionMeta metas) {
        // Esta es una manera "mas bonica" de hacerlo, funcionan las dos.
        // return this.estado_actual.containsAll(metas.get_recursos());

        return metas.get_recursos().stream().allMatch(this::cumple);

//        for (Meta meta: metas.get_recursos()) {
//            if(!cumple(meta)) return false;
//        }
//        return true;
    }

    public void elimina_meta(ConjuncionMeta metas) {
        metas.get_recursos().forEach(this.estado_actual::remove);
    }

    /**
     * Este metodo se usa unicamente para comprobar si hay bucle ya que no tiene
     * en cuenta la ultima meta de la pila
     *
     * @param meta Meta
     * @return Devuelve true si la meta está en la pila.
     */
    public boolean contiene_meta(Meta meta) {
        for(int i = 0; i<this.get_stack_objetivos().size()-1; i++){
            if(meta.equals(this.get_stack_objetivos().get_index(i))){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (this.cached) {
            return this.cache_hash;
        }

        int hash = 1;
        int counter = 1;
        for (int i = 1; i < 10; i++) {
            for (Meta m : this.estado_actual.get(i)) {
                hash = (hash * m.hashCode()) + counter;
                counter++;
            }
        }
        for (int j = 0; j < this.stack_objetivos.size(); j++) {
            hash = (hash * this.stack_objetivos.get_index(j).hashCode()) + counter;
            counter++;
        }

        this.cache_hash = hash;
        this.cached = true;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        if (this == obj)
            return true;

        StripsState other = (StripsState) obj;

        if (other.stack_objetivos.size() != this.stack_objetivos.size())
            return false;

        // Activar para better performance en discos < 4
        if (false) {
            return this.hashCode() == other.hashCode();
        }

        for (int i = 0; i < this.stack_objetivos.size(); i++) {
            if (!other.stack_objetivos.get_index(i).equals(this.stack_objetivos.get_index(i))) {
                return false;
            }
        }

        for(int i = 1;i < 10; ++i) {
            for (Meta m : this.estado_actual.get(i)) {
                if (!other.estado_actual.get(i).contains(m))
                    return false;
            }
        }

        return true;

    }
}
