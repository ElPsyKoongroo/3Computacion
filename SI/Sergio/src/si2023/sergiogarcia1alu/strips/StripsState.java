package si2023.sergiogarcia1alu.strips;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import si2023.sergiogarcia1alu.shared.utils.StripsStack;

public class StripsState {

    private ArrayList<Accion> solucion;
    private final HashSet<Meta> estado_actual;
    private final StripsStack<IStackeable> stack_objetivos;

    private Integer cache_hash = null;
    private boolean cached;

    public StripsState(ArrayList<Meta> ea, ArrayList<IStackeable> objetivos) {
        this.estado_actual = new HashSet<>(ea);// new HashSet<>(ea);
        this.stack_objetivos = new StripsStack<>();
        this.stack_objetivos.addAll(objetivos);
        this.solucion = new ArrayList<>();
        this.cached = false;
    }

    public StripsState(ArrayList<Meta> ea, ConjuncionMeta objetivos) {
        this.estado_actual = new HashSet<>(ea);// new HashSet<>(ea);
        this.stack_objetivos = new StripsStack<>();
        this.stack_objetivos.add(objetivos);
        this.solucion = new ArrayList<>();
        this.cached = false;
    }

    @SuppressWarnings({"unchecked", "CopyConstructorMissesField"})
    public StripsState(StripsState other) {
        this.solucion = new ArrayList<>();
        this.solucion = (ArrayList<Accion>) other.solucion.clone();

        this.estado_actual = (HashSet<Meta>) other.estado_actual.clone();
        this.stack_objetivos = new StripsStack<>(other.stack_objetivos);
        this.cached = false;
    }

    public ArrayList<Accion> get_solucion() {
        return solucion;
    }

    public HashSet<Meta> get_raw_estado_actual() {
        return this.estado_actual;
    }

    public ArrayList<Meta> get_estado_actual() {
        return new ArrayList<>(estado_actual);
    }

    public StripsStack<IStackeable> get_stack_objetivos() {
        return this.stack_objetivos;
    }

    public boolean es_ejecutable(Accion a) {
        return this.estado_actual.containsAll(a.get_precondiciones());
    }

    public void add_solucion(Accion a) {
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
    public void add_pre_requisitos(Accion a) {
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

    public void add_accion(Accion a) {
        this.stack_objetivos.add(a);
    }

    /**
     * @param meta Meta que tiene que cumplirse
     * @return Devuelve true si el estado contiene la meta
     */
    public boolean cumple(Meta meta) {
        return this.estado_actual.contains(meta);
    }

    /**
     *
     * @param metas Conjuncion de metas que tienen que cumplirse
     * @return Devuelve true si se cumplen todas las metas de la conjuncion
     */
    public boolean cumple(ConjuncionMeta metas) {
        // Esta es una manera "mas bonica" de hacerlo, funcionan las dos.
        // return this.estado_actual.containsAll(metas.get_recursos());
        boolean encontrado;
        for (Meta meta: metas.get_recursos()) {
            encontrado = false;
            int objetivo = this.estado_actual.size();
            while (--objetivo >= 0 && !encontrado) {
                if (this.estado_actual.contains(meta)) {
                    encontrado = true;
                }
            }
            if (!encontrado) return false;
        }
        return true;
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
        if(this.cached) {
            return this.cache_hash;
        }

        int hash = 1;
        int i = 1;
        for (Meta m : this.estado_actual) {
            hash = (hash * m.hashCode()) + i;
            i++;
        }

        for (int j = 0; j < this.stack_objetivos.size(); j++) {
            hash = (hash * this.stack_objetivos.get_index(j).hashCode()) + i;
            i++;
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

        for (Meta m : this.estado_actual) {
            if (!other.estado_actual.contains(m))
                return false;
        }

        return true;

    }
}
