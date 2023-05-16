package si2023.sergiogarcia1alu.strips;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

import si2023.sergiogarcia1alu.p05.AgenteSuperInteligente;
import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.p05.operadores.TaparAbujero;
import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.shared.utils.MyPair;
import si2023.sergiogarcia1alu.shared.utils.StripsStack;
import tools.Vector2d;

public class StripsState implements Comparable<StripsState>{

    public static final ArrayList<Vector2d> pos_paredes = new ArrayList<>();

    // Esto añade una mejora del 200%
    static final int[] orden_check_recursos = new int[]{
            RecursosTypes.Jugador.Value,
            RecursosTypes.BloquePiedra.Value,
            RecursosTypes.TengoLlave.Value,
            RecursosTypes.TengoSeta.Value,
    };

    private ArrayList<Operador> solucion;
    private final HashMap<Integer,HashSetMetas> estado_actual;
    private final StripsStack<IStackeable> stack_objetivos;
    public Integer stack_hash = null, recursos_hash = null;

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
        //CalculateHashes();
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
        //CalculateHashes();
    }

    @SuppressWarnings({"unchecked", "CopyConstructorMissesField"})
    public StripsState(StripsState other) {
        this.solucion = (ArrayList<Operador>) other.solucion.clone();

        this.estado_actual = new HashMap<>();

        for (int i = 0; i < RecursosTypes.SIZE; i++) {
            estado_actual.put(i,(HashSetMetas)(other.get_raw_estado_actual_type(i).clone()));
        }

        this.stack_objetivos = new StripsStack<>(other.stack_objetivos);
        this.cached = false;

        //this.cache_hash = other.cache_hash;
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
        return (HashSetMetas)(estado_actual.get(type).clone());
    }

    public StripsStack<IStackeable> get_stack_objetivos() {
        return this.stack_objetivos;
    }

    public boolean es_ejecutable(Operador a) {
        return a.precondiciones.stream().allMatch(precondicion -> {
            if(precondicion instanceof Meta)
                return this.cumple((Meta)precondicion);
            return this.cumple((ConjuncionMeta)precondicion);
        });
    }

    public boolean es_bucle_previo(Operador a) {
        for(int i = 0; i<this.stack_objetivos.size(); i++){
            IStackeable elemento_actual = this.stack_objetivos.get_index(i);
            if (elemento_actual instanceof Operador){
                if (a.equals((Operador)elemento_actual)) return true;
            }
        }
        return false;
//        return a.precondiciones.stream().anyMatch(precondicion -> {
//            if(precondicion instanceof Operador)
//                return this.contiene_meta((Meta)precondicion);
//            return this.contiene_meta((ConjuncionMeta)precondicion);
//        });
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
//    public void add_pre_requisitos(Operador a) {
//        this.stack_objetivos.addAll(a.get_precondiciones());
//        this.stack_objetivos.addAll(a.get_precondiciones());
//    }

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
        metas.get_recursos()
                .forEach(m -> this.get_raw_estado_actual_type(m.type).remove(m));
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
            IStackeable elemento_actual = this.get_stack_objetivos().get_index(i);
            if (elemento_actual instanceof Meta) {
                if(con_meta.contains((Meta)elemento_actual)){
                    return true;
                }
            } else if (elemento_actual instanceof ConjuncionMeta) {
                ConjuncionMeta other_conj = (ConjuncionMeta) elemento_actual;
                if (con_meta.get_recursos().stream().anyMatch(other_conj::contains)) {
                    return true;
                }
            }

        }
        return false;
    }


    public MyPair<Integer> get_hashses() {
        if (this.recursos_hash == null) {
            this.CalculateHashes();
        }

        return new MyPair<>(this.recursos_hash, this.stack_hash);
    }
    private void CalculateHashes() {
        long hash = 1;
        int counter = 1;
        for (int i: orden_check_recursos) {
            for (Meta m : this.estado_actual.get(i)) {
                hash ^= (m.hashCode() + 0x9e3779b9L + (hash<<6) + (hash>>2));
                hash ^= (counter + 0x9e3779b9L + (hash<<6) + (hash>>2));
                counter++;
            }
        }
        this.recursos_hash = (int)hash;
        counter = 0;
        hash = 1;
        for (int j = 0; j < this.stack_objetivos.size(); j++) {
            hash ^= (this.stack_objetivos.get_index(j).hashCode() + 0x9e3779b9L + (hash<<6) + (hash>>2));
            hash ^= (counter + 0x9e3779b9L + (hash<<6) + (hash>>2));
            counter++;
        }
        this.stack_hash = (int)hash;
        //this.cache_hash = Objects.hash(recursos_hash, stack_hash);

    }

    @Override
    public int hashCode() {
        if(this.cached)
            return this.cache_hash;

        long hash = 1;
        int counter = 1;
        for (int i: orden_check_recursos) {
            for (Meta m : this.estado_actual.get(i)) {
                hash ^= (m.hashCode() + 0x9e3779b9L + (hash<<6) + (hash>>2));
                hash ^= (counter + 0x9e3779b9L + (hash<<6) + (hash>>2));
                counter++;
            }
        }
        //counter = 0;
        for (int j = 0; j < this.stack_objetivos.size(); j++) {
            //hash = Objects.hash(hash, this.stack_objetivos.get_index(j).hashCode(), counter);
            hash ^= (this.stack_objetivos.get_index(j).hashCode() + 0x9e3779b9L + (hash<<6) + (hash>>2));
            hash ^= (counter + 0x9e3779b9L + (hash<<6) + (hash>>2));
            counter++;
        }
        hash = Objects.hash(hash, counter);
        this.cache_hash = (int)hash;
        this.cached = true;
        return (int)hash;
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

        // Es mejor comprobar primero el stack, mejora en PC SERGIO ~4.16%
        for (int i = 0; i < this.stack_objetivos.size(); i++) {
            if (!other.stack_objetivos.get_index(i).equals(this.stack_objetivos.get_index(i))) {
                return false;
            }
        }

        for(int i : orden_check_recursos) {
            if (this.get_raw_estado_actual_type(i).size() != other.get_raw_estado_actual_type(i).size()) {
                return false;
            }
            //int finalI = i;
            if (!(other.get_raw_estado_actual_type(i).containsAll(this.get_raw_estado_actual_type(i)))) {
                return false;
            }
        }

        return true;

    }


    public void to_file(int acciones) {
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter("Trace.txt", true));
        } catch(Exception e){
            System.out.println("you got an error");
            return ;
        }

        int MAX_X = AgenteSuperInteligente.x_size;
        int MAX_Y = AgenteSuperInteligente.y_size;

        char[][] map = new char[MAX_X][MAX_Y];

        for(int x = 0; x<MAX_X; x++) {
            for(int y = 0; y<MAX_Y; y++){
                map[x][y] = ' ';
            }
        }

        for(int type = 0; type < RecursosTypes.SIZE; type++){
            for(Meta m: this.get_raw_estado_actual_type(type)){
                Vector2d pos;
                switch (type) {
                    case 0: {
                        pos = ((BloqueLibre) m).posicion;
                        map[(int) pos.x][(int) pos.y] = '.';
                        break;
                    }
                    case 1: {
                        pos = ((BloquePiedra) m).posicion;
                        map[(int) pos.x][(int) pos.y] = 'S';
                        break;
                    }
                    case 2: {
                        pos = ((Gujero) m).posicion;
                        map[(int) pos.x][(int) pos.y] = 'H';
                        break;
                    }
                    case 3: {
                        pos = ((Seta) m).posicion;
                        map[(int) pos.x][(int) pos.y] = 'm';
                        break;
                    }
                    case 4: {
                        pos = ((Llave) m).posicion;
                        map[(int) pos.x][(int) pos.y] = 'k';
                        break;
                    }
                    case 6: {
                        pos = ((Puerta) m).posicion;
                        map[(int) pos.x][(int) pos.y] = 'D';
                        break;
                    }
                    case 7: {
                        pos = ((Jugador) m).posicion;
                        map[(int) pos.x][(int) pos.y] = 'A';
                        break;
                    }
                    case 9: {
                        pos = ((Pared) m).posicion;
                        map[(int) pos.x][(int) pos.y] = 'W';
                        break;
                    }
                }

            }
        }
        try {
            for(int j = 0; j<MAX_Y; j++) {
                for (int i = 0; i < MAX_X; i++) {
                    writer.write(map[i][j]);
                }
                writer.write('\n');
            }
            writer.write("\n");
            writer.write(String.valueOf(acciones));
            writer.write("\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.err.println("Error al escribir");
        }
    }

    @Override
    public int compareTo(StripsState other) {
        int this_agujeros_tapados = 0;
        int other_agujeros_tapados = 0;

       this_agujeros_tapados = this.get_estado_actual_type(RecursosTypes.Gujero.Value).size();
       other_agujeros_tapados = other.get_estado_actual_type(RecursosTypes.Gujero.Value).size();

       if (this_agujeros_tapados > other_agujeros_tapados) {
           return -1;
       }
       else if (this_agujeros_tapados == other_agujeros_tapados) {
           return 0;
       }  else {
           return 1;
       }
        //return Integer.compare(other_agujeros_tapados, this_agujeros_tapados);
    }
}
