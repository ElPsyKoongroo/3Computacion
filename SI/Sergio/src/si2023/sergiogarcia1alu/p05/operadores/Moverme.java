package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;
import si2023.sergiogarcia1alu.shared.Mundo04;
import java.util.ArrayList;
import java.util.Arrays;

public class Moverme extends Operador {

    private Vector2d jugador_pos;
    private Vector2d next_bloque;
    private Vector2d[] POSICIONES = new Vector2d[]{
            new Vector2d(0,1),
            new Vector2d(0,-1),
            new Vector2d(1,0),
            new Vector2d(-1,0),
    };

    public Moverme(){

    }
    public Moverme(Vector2d pos, Vector2d next_bloque) {
        super();
        this.jugador_pos = pos;
        this.next_bloque = next_bloque;

        this.precondiciones.add(new Jugador(pos));
        this.precondiciones.add(new BloqueLibre(next_bloque));

        this.lista_adicion.add(new Jugador(next_bloque));
        //this.lista_adicion.add(new BloqueLibre(pos));

        this.lista_supresion.add(new Jugador(pos));
        //this.lista_supresion.add(new BloqueLibre(next_bloque));
    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {
        ArrayList<Operador> posibilidades = new ArrayList<>();
        Vector2d posicion_jugador = null;
        for (Meta recurso : estado_actual.get_estado_actual()) {
            if (recurso instanceof Jugador) {
                posicion_jugador = ((Jugador)recurso).posicion;
            }
        }

        if (m instanceof Jugador) {
            Jugador meta = (Jugador) m;

            assert posicion_jugador != null;
            if (posicion_jugador.x == meta.posicion.x) {
                posibilidades.add(
                        new Moverme(posicion_jugador, meta.posicion)
                );
            }
            else if (posicion_jugador.y == meta.posicion.y) {
                posibilidades.add(
                        new Moverme(posicion_jugador, meta.posicion)
                );
            }

        } else if (m instanceof BloqueLibre) {
            BloqueLibre slot = (BloqueLibre) m;
            if( !slot.posicion.equals(posicion_jugador)) return posibilidades;

            for(Meta rec : estado_actual.get_estado_actual()) {
                Vector2d posJ = posicion_jugador;
                if(rec instanceof BloqueLibre) {
                    BloqueLibre recursito = (BloqueLibre) rec;

                    if(Arrays.stream(POSICIONES).noneMatch(vector ->
                            vector.equals(posJ.subtract(recursito.posicion)))) continue;
                    posibilidades.add(
                            new Moverme(posicion_jugador, recursito.posicion));
                    }
                }
            }
        return posibilidades;
    }


    @Override
    public Operador clone() {
        return this;
    }
}
