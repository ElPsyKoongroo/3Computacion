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

public class Salir extends Operador {

    private Vector2d jugador_pos;
    private Vector2d puerta;
    final private Vector2d[] POSICIONES = new Vector2d[]{
            new Vector2d(0,1),
            new Vector2d(0,-1),
            new Vector2d(1,0),
            new Vector2d(-1,0),
    };
    public Salir() {}
    public Salir(Vector2d pos, Vector2d puerta)
    {
        super();
        this.jugador_pos = pos;
        this.puerta = puerta;

        this.precondiciones.add(new Jugador(this.jugador_pos));
        this.precondiciones.add(new Puerta(this.puerta));

        this.lista_adicion.add(new Jugador(this.puerta));
        this.lista_adicion.add(new HeSalido());

        this.lista_supresion.add(new Jugador(this.jugador_pos));

        SetAccion(puerta.copy().subtract(pos));
    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {

        ArrayList<Operador> operadores = new ArrayList<>();

        if (!(m instanceof HeSalido)) return operadores;

        Vector2d posicion_puerta = estado_actual
                .get_estado_actual()
                .get(RecursosTypes.Puerta.Value)
                .stream()
                .map(met-> (Puerta)met)
                .findFirst()
                .get().posicion;

        ArrayList<Vector2d> pos_adyacentes = (ArrayList<Vector2d>) Arrays.stream(POSICIONES)
                .map(posicion -> posicion_puerta.copy().add(posicion))
                .collect(Collectors.toList());

        ArrayList<Vector2d> posiciones_paredes_adyacentes = (ArrayList<Vector2d>) estado_actual.get_estado_actual()
                .get(RecursosTypes.Pared.Value)
                .stream()
                .map(p -> ((Pared)p).posicion)
                .filter(pos_adyacentes::contains)
                .collect(Collectors.toList());

        operadores.addAll(
                pos_adyacentes.stream()
                        .filter(pos -> !posiciones_paredes_adyacentes.contains(pos))
                        .map(pos -> new Salir(pos, posicion_puerta))
                        .collect(Collectors.toList()));

        return operadores;
    }

    @Override
    public Operador clone() {
        return this;
    }
}
