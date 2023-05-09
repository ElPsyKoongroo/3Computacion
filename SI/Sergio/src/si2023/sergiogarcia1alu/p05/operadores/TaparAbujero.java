package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;

public class TaparAbujero extends Operador {

    private Vector2d jugador_pos;
    private Vector2d bloque_piedra;
    private Vector2d bloque_bujero;
    public TaparAbujero() {}
    public TaparAbujero(Vector2d pos, Vector2d bloque_piedra, Vector2d bloque_bujero) {
        super();
        this.jugador_pos = pos;
        this.bloque_piedra = bloque_piedra;
        this.bloque_bujero = bloque_bujero;

        this.precondiciones.add(new Jugador(pos));
        this.precondiciones.add(new BloquePiedra(bloque_piedra));
        this.precondiciones.add(new BloqueLibre(bloque_bujero));

        this.lista_adicion.add(new Jugador(bloque_piedra));
        this.lista_adicion.add(new BloqueLibre(pos));
        this.lista_adicion.add(new BloquePiedra(bloque_bujero));

        this.lista_supresion.add(new Jugador(pos));
        this.lista_supresion.add(new BloquePiedra(bloque_piedra));
        this.lista_supresion.add(new BloqueLibre(bloque_bujero));

        SetAccion(bloque_piedra.copy().subtract(pos));

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
