package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;

public class CogerLlave extends Operador {

    private Vector2d jugador_pos;
    private Vector2d bloque_llave;
    private Vector2d[] POSICIONES = new Vector2d[]{
            new Vector2d(0,1),
            new Vector2d(0,-1),
            new Vector2d(1,0),
            new Vector2d(-1,0),
    };
    public CogerLlave() {}
    public CogerLlave(Vector2d pos, Vector2d bloque_llave)
    {
        super();
        this.jugador_pos = pos;
        this.bloque_llave = bloque_llave;

        this.precondiciones.add(new Jugador(this.jugador_pos));
        this.precondiciones.add(new Llave(this.bloque_llave));

        this.lista_adicion.add(new Jugador(this.bloque_llave));
        this.lista_adicion.add(new BloqueLibre(this.jugador_pos));
        this.lista_adicion.add(new TengoLlave());

        this.lista_supresion.add(new Jugador(this.jugador_pos));
        this.lista_supresion.add(new Llave(this.bloque_llave));


    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {
        ArrayList<Operador> operadores = new ArrayList<>();
        if (!(m instanceof TengoLlave)) return operadores;

        Llave pos_llave = null;
        for(Meta recurso: estado_actual.get_estado_actual()) {
            if (recurso instanceof Llave) {
                pos_llave = (Llave) recurso;
            }
        }

        Vector2d posicion_jugador = null;
        for (Meta recurso : estado_actual.get_estado_actual()) {
            if (recurso instanceof Jugador) {
                posicion_jugador = ((Jugador)recurso).posicion;
            }
        }

        assert pos_llave != null;
        assert posicion_jugador != null;

        for(Vector2d pos: this.POSICIONES) {
            operadores.add(
                    new CogerLlave(posicion_jugador.copy().subtract(pos), pos_llave.posicion)
            );
        }

        return operadores;
    }
    @Override
    public Operador clone() {
        return this;
    }
}
