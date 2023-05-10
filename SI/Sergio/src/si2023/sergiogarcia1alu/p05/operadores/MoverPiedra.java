package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.p06.Apilar;
import si2023.sergiogarcia1alu.strips.ConjuncionMeta;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class MoverPiedra extends Operador {
    private Vector2d jugador_pos;
    private Vector2d bloque_piedra;
    private Vector2d siguiente_bloque;

    private Vector2d[] POSICIONES = new Vector2d[]{
        new Vector2d(0,1),
        new Vector2d(0,-1),
        new Vector2d(1,0),
        new Vector2d(-1,0),
    };
    public MoverPiedra() {}
    public MoverPiedra(Vector2d pos, Vector2d bloque_piedra, Vector2d siguiente_bloque) {
        super();
        this.jugador_pos = pos;
        this.bloque_piedra = bloque_piedra;
        this.siguiente_bloque = siguiente_bloque;

        ConjuncionMeta cm = new ConjuncionMeta(
                new Jugador(this.jugador_pos),
                new BloquePiedra(this.bloque_piedra),
                new BloqueLibre(this.siguiente_bloque)
        );


        this.precondiciones.add(cm);

        this.lista_adicion.add(new Jugador(this.bloque_piedra));
        this.lista_adicion.add(new BloqueLibre(this.bloque_piedra));
        this.lista_adicion.add(new BloquePiedra(this.siguiente_bloque));

        this.lista_supresion.add(new Jugador(this.jugador_pos));
        this.lista_supresion.add(new BloquePiedra(this.bloque_piedra));
        this.lista_supresion.add(new BloqueLibre(this.siguiente_bloque));

        SetAccion(this.bloque_piedra.copy().subtract(this.jugador_pos));

    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {
        //ArrayList<Operador> operadores = new ArrayList<>();
        switch (m.type) {
            case 0: /* Bloque Libre */ {
                return despejar_camino(((BloqueLibre)m).posicion, estado_actual);
            }
            case 1: /* Bloque Piedra */ {
                return desplaza_piedra(((BloquePiedra)m).posicion, estado_actual);
            }
            case 7: /* Jugador */ {
                return despejar_camino(((Jugador)m).posicion, estado_actual);
            }
            default:
                return new ArrayList<>();
        }
    }


    public ArrayList<Operador> desplaza_piedra(Vector2d posicion_dst, StripsState estado_actual) {

        ArrayList<Vector2d> paredes_pos = (ArrayList<Vector2d>)
                estado_actual.get_raw_estado_actual_type(RecursosTypes.Pared.Value)
                .stream().map(par -> ((Pared)par).posicion)
                .collect(Collectors.toList());


        ArrayList<Vector2d[]> posiciones_validas = (ArrayList<Vector2d[]>) Arrays.stream(POSICIONES)
            .map(dir -> new Vector2d[]{posicion_dst.copy().add(dir), posicion_dst.copy().add(dir.copy().mul(2))})
            .filter(pareja -> Arrays.stream(pareja).noneMatch(paredes_pos::contains))
            .collect(Collectors.toList());

        ArrayList<Operador> operadores = new ArrayList<>();

        for(Vector2d[] posiciones : posiciones_validas) {
            operadores.add(new MoverPiedra(posiciones[1], posiciones[0], posicion_dst));
        }
        return operadores;
    }

    public ArrayList<Operador> despejar_camino(Vector2d posicion_piedra, StripsState estado_actual) {
        ArrayList<Operador> operadores = new ArrayList<>();

        boolean hay_piedra = estado_actual.get_raw_estado_actual()
                .get(RecursosTypes.BloquePiedra.Value)
                .stream()
                .map(obj -> ((BloquePiedra)obj).posicion)
                .anyMatch(pos_piedra -> pos_piedra.equals(posicion_piedra));

        if (!hay_piedra) return operadores;

        Arrays.stream(POSICIONES)
                .filter(pos -> estado_actual.get_raw_estado_actual()
                        .get(RecursosTypes.Pared.Value)
                        .stream()
                        .map(obj -> ((Pared)obj).posicion)
                        .noneMatch(pared_pos -> pared_pos.equals(pos.copy().add(posicion_piedra)) && pared_pos.equals(pos.copy().subtract(posicion_piedra))))
                .forEach(dir_libre -> operadores.add(
                        new MoverPiedra(
                                posicion_piedra.copy().add(dir_libre),
                                posicion_piedra,
                                posicion_piedra.copy().subtract(dir_libre)
                        )
                ));
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
        MoverPiedra other = (MoverPiedra) obj;

        return  this.bloque_piedra.equals(other.bloque_piedra) &&
                this.siguiente_bloque.equals(other.siguiente_bloque) &&
                this.jugador_pos.equals(other.jugador_pos);
    }

    @Override
    public int hashCode(){
        int hash = 33;
        hash = Objects.hash(this.bloque_piedra.x, this.bloque_piedra.y, hash);
        hash = Objects.hash(this.jugador_pos.x, this.jugador_pos.y, hash);
        hash = Objects.hash(this.siguiente_bloque.x, this.siguiente_bloque.y, hash);
        return hash;
        //return Objects.hash(this.jugador_pos.x, this.jugador_pos.y, this.next_bloque.x, this.next_bloque.y, 14);
    }


    @Override
    public Operador clone() {
        return this;
    }
}
