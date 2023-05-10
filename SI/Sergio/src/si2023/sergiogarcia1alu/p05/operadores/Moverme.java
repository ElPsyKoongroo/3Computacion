package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.Mover;
import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;
import si2023.sergiogarcia1alu.shared.Mundo04;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
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

    public Moverme(){

    }
    public Moverme(Vector2d pos, Vector2d next_bloque) {
        super();
        this.jugador_pos = pos;
        this.next_bloque = next_bloque;

        this.precondiciones.add(new Jugador(this.jugador_pos));
        this.precondiciones.add(new BloqueLibre(this.next_bloque));

        this.lista_adicion.add(new Jugador(this.next_bloque));

        this.lista_supresion.add(new Jugador(this.jugador_pos));

        SetAccion(next_bloque.copy().subtract(pos));
    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {
        ArrayList<Operador> operadores = new ArrayList<>();

        if (!(m instanceof Jugador)) return operadores;

        Jugador posicion_final = (Jugador)m;

        ArrayList<Vector2d> pos_adyacentes = (ArrayList<Vector2d>) Arrays.stream(POSICIONES)
                .map(posicion -> posicion_final.posicion.copy().add(posicion))
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
                        .filter(posicion -> !(posicion.x < 0 || posicion.x > 4))
                        .filter(posicion -> !(posicion.y < 0 || posicion.y > 5))
                        .map(pos -> new Moverme(pos, posicion_final.posicion))
                        .collect(Collectors.toList()));

        return operadores;
    }


    @Override
    public Operador clone() {
        return this;
    }
}
