package si2023.sergiogarcia1alu.strips;

import java.util.ArrayList;

public abstract class Operador implements IStackeable {

    protected final ArrayList<Meta> precondiciones;
    protected final ArrayList<Meta> lista_adicion;
    protected final ArrayList<Meta> lista_supresion;

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
        this.precondiciones = (ArrayList<Meta>) other.precondiciones.clone();
    }

    public ArrayList<Meta> get_precondiciones() {
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
        //this.lista_supresion.forEach(copia.get_raw_estado_actual()::remove);
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

        ArrayList<Meta> sobres = new ArrayList<>(this.get_precondiciones());
        copia.get_stack_objetivos().add(new ConjuncionMeta(sobres));
        
        return copia;
    }

    public final boolean is_accion() {
        return true;
    }

    public abstract Operador clone();

//    @Override
//    public IStackeable clone() {
//        return new Accion(this);
//    }

}
