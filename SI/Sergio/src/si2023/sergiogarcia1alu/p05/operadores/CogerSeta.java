package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;

public class CogerSeta extends Operador {

    private Vector2d jugador_pos;
    private Vector2d next_bloque;
    public CogerSeta(){}
    public CogerSeta(Vector2d pos, Vector2d next_bloque) {
        super();
        this.jugador_pos = pos;
        this.next_bloque = next_bloque;

        this.precondiciones.add(new Jugador(pos));
        this.precondiciones.add(new Seta(next_bloque));

        this.lista_adicion.add(new Jugador(next_bloque));
        this.lista_adicion.add(new TengoSeta());

        this.lista_supresion.add(new Jugador(pos));
        this.lista_supresion.add(new Seta(next_bloque));
    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {
        return new ArrayList<>();
    }
    @Override
    public Operador clone() {
        return this;
    }
}
