package si2023.sergiogarcia1alu.strips;

import java.util.*;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.shared.utils.StripsStack;

public class StripsState {

    private ArrayList<Operador> solucion;
    private final HashMap<Integer,HashSetMetas> estado_actual;
    private final StripsStack<IStackeable> stack_objetivos;

    private Integer cache_hash = null;
    private boolean cached;
    public StripsState(ArrayList<Meta> ea, ArrayList<IStackeable> objetivos) {
        this.estado_actual = new HashMap<>();// new HashSet<>(ea);

        for (int i = 0; i < RecursosTypes.SIZE; i++) {
            estado_actual.put(i, new HashSetMetas());
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

        for (int i = 0; i < RecursosTypes.SIZE; i++) {
            estado_actual.put(i, new HashSetMetas());
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

        //this.estado_actual = (HashMap<Integer, HashSetMetas>) other.estado_actual.clone();

        this.estado_actual = new HashMap<>();

        for (int i = 0; i < RecursosTypes.SIZE; i++) {
            estado_actual.put(i, new HashSetMetas(other.estado_actual.get(i)));
        }

        this.stack_objetivos = new StripsStack<>(other.stack_objetivos);
        this.cached = false;
    }

    public ArrayList<Operador> get_solucion() {
        return this.solucion;
    }

    public HashMap<Integer, HashSetMetas> get_raw_estado_actual() {
        return this.estado_actual;
    }

    public HashMap<Integer, HashSetMetas> get_estado_actual() {
        return new HashMap<>(estado_actual);
    }

    public HashSetMetas get_raw_estado_actual_type(int type) {
        return this.estado_actual.get(type);
    }

    public HashSetMetas get_estado_actual_type(int type) {
        return new HashSetMetas(estado_actual.get(type));
    }

    public StripsStack<IStackeable> get_stack_objetivos() {
        return this.stack_objetivos;
    }

    public boolean es_ejecutable(Operador a) {
        return a.precondiciones.stream().allMatch(precondicion ->{
            if(precondicion instanceof Meta)
                return this.cumple((Meta)precondicion);
            return this.cumple((ConjuncionMeta)precondicion);
        });
    }

    public void add_solucion(Operador a) {
        this.solucion.add(a);
    }

    /**
     * Añade a la lista de objetivos los prerequisitos una accion.
     * <p>
     * Aunque el algoritmo funcione esta funcion no es correcta. Para que sea
     * correcta descomentar la ultima linea y comentar las dos primeras.
     * <p> <br>
     *     Para el juego Torres de Hanoi no tiene importancia añadir las precondiciones
     *     como Conjunto, es mejor añadirlas como metas individuales en el orden correcto.
     * </p>
     */
    public void add_pre_requisitos(Operador a) {
        this.stack_objetivos.addAll(a.get_precondiciones());
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
            if(!(this.get_stack_objetivos().get_index(i) instanceof Meta)) continue;
            if(meta.equals(this.get_stack_objetivos().get_index(i))){
                return true;
            }
        }
        return false;
    }

    public boolean contiene_meta(ConjuncionMeta con_meta) {
        for(int i = 0; i<this.get_stack_objetivos().size()-1; i++){
            if(!(this.get_stack_objetivos().get_index(i) instanceof Meta)) continue;
            if(con_meta.contains((Meta)this.get_stack_objetivos().get_index(i))){
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
        for (int i = 0; i < RecursosTypes.SIZE; i++) {
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
        {
            int a = 0;
            return true;
        }


        StripsState other = (StripsState) obj;

        if (other.stack_objetivos.size() != this.stack_objetivos.size())
            return false;

        for (int i = 0; i < this.stack_objetivos.size(); i++) {
            if (!other.stack_objetivos.get_index(i).equals(this.stack_objetivos.get_index(i))) {
                return false;
            }
        }


        for(int i = 0; i < RecursosTypes.SIZE; ++i) {
            if (this.get_estado_actual().get(i).size() != other.get_estado_actual().get(i).size()) {
                return false;
            }
            int finalI = i;
            if (!(other.get_estado_actual().get(finalI).containsAll(this.get_estado_actual().get(i)))) {
                return false;
            }
        }



        int a = 0;
        return true;

    }
}
