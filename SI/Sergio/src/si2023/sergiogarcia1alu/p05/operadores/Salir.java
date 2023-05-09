package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.BloqueLibre;
import si2023.sergiogarcia1alu.p05.recursos.BloquePiedra;
import si2023.sergiogarcia1alu.p05.recursos.Jugador;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;

public class Salir extends Operador {

    private Vector2d jugador_pos;
    private Vector2d bloque_puerta;
    public Salir() {}
    public Salir(Vector2d pos, Vector2d bloque_puerta)
    {
        super();
        this.jugador_pos = pos;
        this.bloque_puerta = bloque_puerta;

        this.precondiciones.add(new Jugador(pos));
        this.precondiciones.add(new BloquePiedra(bloque_puerta));

        this.lista_adicion.add(new Jugador(bloque_puerta));
        this.lista_adicion.add(new BloqueLibre(pos));

        this.lista_supresion.add(new Jugador(pos));
        this.lista_supresion.add(new BloquePiedra(bloque_puerta));
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
