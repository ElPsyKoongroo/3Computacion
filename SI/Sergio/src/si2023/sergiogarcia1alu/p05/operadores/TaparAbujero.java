package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.ConjuncionMeta;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaparAbujero extends Operador {

    private Vector2d jugador_pos;
    private Vector2d bloque_piedra;
    private Vector2d bloque_bujero;
    private Vector2d[] POSICIONES = new Vector2d[]{
            new Vector2d(0,1),
            new Vector2d(0,-1),
            new Vector2d(1,0),
            new Vector2d(-1,0),
    };
    public TaparAbujero() {}
    public TaparAbujero(Vector2d pos, Vector2d bloque_piedra, Vector2d bloque_bujero) {
        super();
        this.jugador_pos = pos;
        this.bloque_piedra = bloque_piedra;
        this.bloque_bujero = bloque_bujero;

//        ConjuncionMeta cm = new ConjuncionMeta(
//            new Jugador(pos),
//            new BloquePiedra(bloque_piedra)
//        );



//
//

//        this.precondiciones.add(cm);
        this.precondiciones.add(new Jugador(pos));
        this.precondiciones.add(new BloquePiedra(bloque_piedra));
        this.precondiciones.add(new Gujero(bloque_bujero));



        this.lista_adicion.add(new Jugador(bloque_piedra));
        this.lista_adicion.add(new BloqueLibre(bloque_piedra));
        this.lista_adicion.add(new BloqueLibre(bloque_bujero));

        this.lista_supresion.add(new Jugador(pos));
        this.lista_supresion.add(new BloquePiedra(bloque_piedra));
        this.lista_supresion.add(new Gujero(bloque_bujero));

        SetAccion(bloque_piedra.copy().subtract(pos));
    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {

        switch (m.type)
        {
            case 0: { // BloqueLibre
                return tapar(((BloqueLibre)m).posicion, estado_actual);
            }

            case 7: { // Jugador
                return mover_piedra(((Jugador)m).posicion, estado_actual);
            }

            default: return new ArrayList<>();
        }

    }

    public ArrayList<Operador> mover_piedra(Vector2d posicion_piedra, StripsState estado_actual) {
        ArrayList<Operador> operadores = new ArrayList<>();

        Arrays.stream(POSICIONES)
            .filter(pos -> estado_actual.get_raw_estado_actual()
                    .get(RecursosTypes.Gujero.Value)
                    .stream()
                    .map(obj -> ((Gujero)obj).posicion)
                    .anyMatch(gujero_pos -> gujero_pos.equals(posicion_piedra.copy().add(pos))))
            .filter(pos -> StripsState.pos_paredes
                    .stream()
                    .noneMatch(pos_pared -> pos_pared.equals(posicion_piedra.copy().subtract(pos))))
            .forEach(dir_libre -> {
                operadores.add(
                new TaparAbujero(
                        posicion_piedra.copy().subtract(dir_libre),
                        posicion_piedra,
                        posicion_piedra.copy().add(dir_libre)
                ));
            }
        );
        return operadores;
    }

    public ArrayList<Operador> tapar(Vector2d posicion_gujero, StripsState estado_actual) {

        ArrayList<Operador> operadores = new ArrayList<>();

        boolean hay_gujero = estado_actual.get_raw_estado_actual()
                .get(RecursosTypes.Gujero.Value)
                .stream()
                .map(obj -> ((Gujero)obj).posicion)
                .anyMatch(pos_gujero -> pos_gujero.equals(posicion_gujero));

        boolean quieren_tapar = estado_actual.get_stack_objetivos()
                .get_elementos()
                .stream()
                .filter(objetivo -> objetivo.getClass() == TaparAbujero.class)
                .map(obj -> ((TaparAbujero)obj).bloque_bujero)
                .anyMatch(pos_gujero -> pos_gujero.equals(posicion_gujero));

        if (!hay_gujero) return operadores;
        if (quieren_tapar) return operadores;

//        ArrayList<Vector2d> paredes_pos = (ArrayList<Vector2d>)
//                estado_actual.get_raw_estado_actual_type(RecursosTypes.Pared.Value)
//                        .stream().map(par -> ((Pared)par).posicion)
//                        .collect(Collectors.toList());


//        if (posicion_gujero.x == 3 && posicion_gujero.y == 2) {
//            System.out.println(" ");
//        }

        ArrayList<Vector2d[]> posiciones_validas = (ArrayList<Vector2d[]>) Arrays.stream(POSICIONES)
                .map(dir -> new Vector2d[]{posicion_gujero.copy().add(dir), posicion_gujero.copy().add(dir.copy().mul(2))})
                .filter(pareja -> Arrays.stream(pareja).noneMatch(StripsState.pos_paredes::contains))
                .sorted(Comparator.comparingDouble(pos -> pos[0].x))
                .collect(Collectors.toList());

        for(Vector2d[] posiciones : posiciones_validas) {
            operadores.add(new TaparAbujero(posiciones[1], posiciones[0], posicion_gujero));
        }
        return operadores;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaparAbujero other = (TaparAbujero) obj;


        return this.bloque_bujero.equals(other.bloque_bujero);
//        return  this.bloque_piedra.equals(other.bloque_piedra) &&
//                this.bloque_bujero.equals(other.bloque_bujero) &&
//                this.jugador_pos.equals(other.jugador_pos);
    }

    @Override
    public int hashCode(){
        long hash = 31;

        // seed ^= hasher(v) + 0x9e3779b9 + (seed<<6) + (seed>>2); // Cortesia de la libreria BOOST
//        hash ^= (long)(this.bloque_piedra.x + 0x9e3779b9L + (hash<<6) + (hash>>2));
//        hash ^= (long)(this.bloque_piedra.y + 0x9e3779b9L + (hash<<6) + (hash>>2));
//
//        hash ^= (long)(this.jugador_pos.x + 0x9e3779b9L + (hash<<6) + (hash>>2));
//        hash ^= (long)(this.jugador_pos.y + 0x9e3779b9L + (hash<<6) + (hash>>2));

        hash ^= (long)(this.bloque_bujero.x + 0x9e3779b9L + (hash<<6) + (hash>>2));
        hash ^= (long)(this.bloque_bujero.y + 0x9e3779b9L + (hash<<6) + (hash>>2));

        return (int)hash;
    }


    @Override
    public Operador clone() {
        return this;
    }
}
