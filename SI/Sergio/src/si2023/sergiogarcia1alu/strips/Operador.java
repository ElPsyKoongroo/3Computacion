package si2023.sergiogarcia1alu.strips;

import ontology.Types;
import tools.Vector2d;

import java.util.ArrayList;
import java.util.Objects;


public abstract class Operador implements IStackeable {


    protected final ArrayList<IStackeable> precondiciones;
    protected final ArrayList<Meta> lista_adicion;
    protected final ArrayList<Meta> lista_supresion;

    protected Types.ACTIONS accion;

    protected int hash;
    protected boolean hash_calculed = false;



    public Operador() {
        this.precondiciones = new ArrayList<>();
        this.lista_adicion = new ArrayList<>();
        this.lista_supresion = new ArrayList<>();
    }

    /**
     * Returns a shallow copy of Accion.
     * 
     * Da igual que sea shallow porque la accion no se va a modificar en ningun
     * momento. Estaria wapo que java tuviera cosas del palo final que funcionaran
     * bien de verdad.
     * 
     * @param other Who ?
     */
    @SuppressWarnings("unchecked")
    public Operador(Operador other) {
        this.lista_adicion = (ArrayList<Meta>) other.lista_adicion.clone();
        this.lista_supresion = (ArrayList<Meta>) other.lista_supresion.clone();
        this.precondiciones = (ArrayList<IStackeable>) other.precondiciones.clone();
    }

    public Types.ACTIONS GetAction() {
        return this.accion;
    }

    protected void SetAccion(Vector2d diff) {
        if(diff.x < 0) {
            accion = Types.ACTIONS.ACTION_LEFT;
        }
        else if (diff.x > 0) {
            accion = Types.ACTIONS.ACTION_RIGHT;
        }
        else if (diff.y < 0) {
            accion = Types.ACTIONS.ACTION_UP;
        }
        else if (diff.y > 0){
            accion = Types.ACTIONS.ACTION_DOWN;
        }
        else {
            accion = Types.ACTIONS.ACTION_NIL;
        }
    }

    protected boolean Contrario(Operador otro)
    {
        if(otro.getClass() != this.getClass()) return false;
        switch (otro.accion)
        {
            case ACTION_DOWN: return accion == Types.ACTIONS.ACTION_UP;
            case ACTION_UP: return accion == Types.ACTIONS.ACTION_DOWN;
            case ACTION_LEFT: return accion == Types.ACTIONS.ACTION_RIGHT;
            case ACTION_RIGHT:  return accion == Types.ACTIONS.ACTION_LEFT;
            default: return accion == Types.ACTIONS.ACTION_NIL;
        }
    }

    public ArrayList<IStackeable> get_precondiciones() {
        return precondiciones;
    }

    public ArrayList<Meta> get_lista_adicion() {
        return lista_adicion;
    }

    public ArrayList<Meta> get_lista_supresion() {
        return lista_supresion;
    }

    public abstract ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual);

    // Los suppress esto es porque Java no tiene ni idea de que cuando clona un
    // objeto de tipo T devuelve otro tipo T y no un Object pero como no da pa'mah
    // pues es lo que toca.
    @SuppressWarnings("unchecked")
    public StripsState aplica_accion(StripsState estado) {
        StripsState copia = new StripsState(estado);

        copia.get_stack_objetivos().poll();
        this.lista_supresion.forEach(supr -> {
            copia.get_raw_estado_actual_type(supr.type).remove(supr);
        });

        ArrayList<Meta> adicion = (ArrayList<Meta>) this.lista_adicion.clone();

        this.lista_adicion.forEach(addi -> {
            copia.get_raw_estado_actual_type(addi.type).add(addi);
        });
        //copia.get_raw_estado_actual().addAll(adicion);
        copia.get_solucion().add(this);

        return copia;
    }
    public StripsState add_prerequisitos(StripsState estado) {
        StripsState copia = new StripsState(estado);

        copia.get_stack_objetivos().addAll(this.get_precondiciones());
        return copia;
    }

    public boolean hay_bucle(StripsState estado) {
        for(IStackeable pre: this.get_precondiciones()){
            if (pre.getClass() == ConjuncionMeta.class) {
                if (estado.contiene_meta((ConjuncionMeta)pre)) return true;
            }
//            else if(pre instanceof Meta) {
//                if (estado.contiene_meta((Meta)pre)) return true;
//            }
        }
        return false;
    }

    public final boolean is_accion() {
        return true;
    }

    public abstract Operador clone();

    @Override
    public abstract int hashCode();

    /*
    @Override
    public int hashCode() {
        if(hash_calculed) return hash;
        hash_calculed = true;
        hash = 14;
        for(Meta m : this.lista_supresion)
        {
            hash = Objects.hash(hash, m.hashCode());
        }
        for(Meta m : this.lista_adicion)
        {
            hash = Objects.hash(hash, m.hashCode());
        }
        for(IStackeable m : this.precondiciones)
        {
            hash = Objects.hash(hash, m.hashCode());
        }
        return hash;
    }
    */


}
