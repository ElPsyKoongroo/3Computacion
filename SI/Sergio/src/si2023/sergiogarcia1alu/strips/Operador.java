package si2023.sergiogarcia1alu.strips;

import java.util.ArrayList;

public abstract class Accion implements IStackeable {

    protected final ArrayList<IStackeable> precondiciones;
    protected final ArrayList<Meta> lista_adicion;
    protected final ArrayList<IStackeable> lista_supresion;

    public Accion() {
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
    public Accion(Accion other) {
        this.lista_adicion = (ArrayList<Meta>) other.lista_adicion.clone();
        this.lista_supresion = (ArrayList<IStackeable>) other.lista_supresion.clone();
        this.precondiciones = (ArrayList<IStackeable>) other.precondiciones.clone();
    }

    public ArrayList<IStackeable> get_precondiciones() {
        return precondiciones;
    }

    public ArrayList<Meta> get_lista_adicion() {
        return lista_adicion;
    }

    public ArrayList<IStackeable> get_lista_supresion() {
        return lista_supresion;
    }

    public abstract ArrayList<Accion> gen_posibilidades(Meta m, StripsState estado_actual);

    // Los suppress esto es porque Java no tiene ni idea de que cuando clona un
    // objeto de tipo T devuelve otro tipo T y no un Object pero como no da pa'mah
    // pues es lo que toca.
    @SuppressWarnings("unchecked")
    public StripsState aplica_accion(StripsState estado) {
        StripsState copia = new StripsState(estado);

        copia.get_stack_objetivos().poll();
        this.lista_supresion.forEach(copia.get_raw_estado_actual()::remove);
        ArrayList<Meta> adicion = (ArrayList<Meta>) this.lista_adicion.clone();

        copia.get_raw_estado_actual().addAll(adicion);
        copia.get_solucion().add(this);

        return copia;
    }
    
    public StripsState add_prerequisitos(StripsState estado) {
        StripsState copia = new StripsState(estado);

        ArrayList<IStackeable> sobres = new ArrayList<>(this.get_precondiciones());
        copia.get_stack_objetivos().add(new ConjuncionMeta(sobres));
        
        return copia;
    }

    public final boolean is_accion() {
        return true;
    }

    public abstract Accion clone();

//    @Override
//    public IStackeable clone() {
//        return new Accion(this);
//    }

}
