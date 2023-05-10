package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.BloqueLibre;
import si2023.sergiogarcia1alu.p05.recursos.BloquePiedra;
import si2023.sergiogarcia1alu.p05.recursos.Gujero;
import si2023.sergiogarcia1alu.p05.recursos.Jugador;
import si2023.sergiogarcia1alu.p06.Apilar;
import si2023.sergiogarcia1alu.strips.ConjuncionMeta;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Moverme extends Operador {

    private Vector2d jugador_pos;
    private Vector2d next_bloque;
    private Vector2d[] POSICIONES = new Vector2d[]{
            new Vector2d(0.0,1.0),
            new Vector2d(0.0,-1.0),
            new Vector2d(1.0,0.0),
            new Vector2d(-1.0,0.0),
    };

    public Moverme(){}

    public Moverme(Vector2d pos, Vector2d next_bloque) {
        super();
        this.jugador_pos = pos;
        this.next_bloque = next_bloque;

//        ConjuncionMeta centimetros = new ConjuncionMeta(
//                new Jugador(this.jugador_pos),
//                new BloqueLibre(this.next_bloque)
//        );
//        this.precondiciones.add(centimetros);

        this.precondiciones.add(new Jugador(pos));
        this.precondiciones.add(new BloqueLibre(next_bloque));



        this.lista_adicion.add(new Jugador(this.next_bloque));

        this.lista_supresion.add(new Jugador(this.jugador_pos));

        SetAccion(next_bloque.copy().subtract(pos));
    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {
        ArrayList<Operador> operadores = new ArrayList<>();

        if (!(m instanceof Jugador)) return operadores;

        Jugador posicion_final = (Jugador)m;

        if(
                estado_actual.get_raw_estado_actual().get(RecursosTypes.BloquePiedra.Value)
                        .stream().anyMatch(p -> ((BloquePiedra)p).posicion.equals(posicion_final.posicion))
        ) {
            return operadores;
        }

        ArrayList<Vector2d> pos_adyacentes = (ArrayList<Vector2d>) Arrays.stream(POSICIONES)
                .map(posicion -> posicion_final.posicion.copy().add(posicion))
                .collect(Collectors.toList());

        ArrayList<Moverme> posiciones_libres_adyacentes = (ArrayList<Moverme>) estado_actual.get_raw_estado_actual()
                .get(RecursosTypes.BloqueLibre.Value)
                .stream()
                .map(p -> ((BloqueLibre)p).posicion)
                .filter(pos_adyacentes::contains)
                .map(p -> new Moverme(p, posicion_final.posicion))
                //.filter(p -> !p.Contrario(estado_actual.get_solucion().get(estado_actual.get_solucion().size()-1)))
                .collect(Collectors.toList());

        ArrayList<Moverme> posiciones_gujero_adyacentes = (ArrayList<Moverme>) estado_actual.get_raw_estado_actual()
                .get(RecursosTypes.Gujero.Value)
                .stream()
                .map(p -> ((Gujero)p).posicion)
                .filter(pos_adyacentes::contains)
                .map(p -> new Moverme(p, posicion_final.posicion))
                //.filter(p -> !p.Contrario(estado_actual.get_solucion().get(estado_actual.get_solucion().size()-1)))
                .collect(Collectors.toList());

        operadores.addAll(posiciones_libres_adyacentes);
        operadores.addAll(posiciones_gujero_adyacentes);


        /*
        *         operadores.addAll(
                pos_adyacentes.stream()
                        .filter(posiciones_libres_adyacentes::contains)
                        .map(pos -> new Moverme(pos, posicion_final.posicion))
                        .collect(Collectors.toList()));
        operadores.addAll(
                pos_adyacentes.stream()
                        .filter(posiciones_gujero_adyacentes::contains)
                        .map(pos -> new Moverme(pos, posicion_final.posicion))
                        .collect(Collectors.toList()));

        * */

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
        Moverme other = (Moverme) obj;
        return this.jugador_pos.equals(other.jugador_pos) && this.next_bloque.equals(other.next_bloque);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.jugador_pos.x, this.jugador_pos.y, this.next_bloque.x, this.next_bloque.y, 14);
    }

    @Override
    public Operador clone() {
        return this;
    }
}
