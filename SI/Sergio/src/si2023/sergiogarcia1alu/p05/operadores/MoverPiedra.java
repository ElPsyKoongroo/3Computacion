package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;
import java.util.Arrays;
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

        this.precondiciones.add(new Jugador(this.jugador_pos));
        this.precondiciones.add(new BloquePiedra(this.bloque_piedra));
        this.precondiciones.add(new BloqueLibre(this.siguiente_bloque));

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
        ArrayList<Operador> operadores = new ArrayList<>();

        if (!(m instanceof BloqueLibre)) return operadores;

        boolean hay_piedra =  estado_actual.get_estado_actual()
                .get(RecursosTypes.BloquePiedra.Value)
                .stream()
                .map(obj -> ((BloquePiedra)obj).posicion)
                .anyMatch(pos_piedra -> pos_piedra.equals(((BloqueLibre)m).posicion));

        if (!hay_piedra) return operadores;

        Vector2d posicion_piedra = ((BloqueLibre)m).posicion;

        /*

                    U
                  L S Wall
                    D

                    [Up, Down]
         */


        Arrays.stream(POSICIONES)
                .filter(pos -> estado_actual.get_estado_actual()
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
    public Operador clone() {
        return this;
    }
}
