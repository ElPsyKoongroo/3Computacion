package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;

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

//        Vector2d posicion_jugador =
//                estado_actual
//                        .get_estado_actual()
//                        .get(RecursosTypes.Jugador.Value)
//                        .stream()
//                        .map(met-> (Jugador)met)
//                        .findFirst()
//                        .get()
//                        .posicion;
//
//        ArrayList<Vector2d> piedras = new ArrayList<>();
//        for(Meta recurso: estado_actual.get_estado_actual().get(RecursosTypes.BloquePiedra)) {
//            if (recurso instanceof BloquePiedra) {
//                piedras.add(((BloquePiedra) recurso).posicion);
//            }
//        }
//
//        if (!(m instanceof BloqueLibre)) { return operadores; }
//
//        BloqueLibre meta = (BloqueLibre) m;
//
//        if (piedras.stream().noneMatch(pos -> pos.equals(meta.posicion))) { return operadores; }
//
//        for (Vector2d pos_piedra: piedras) {
//            if (!pos_piedra.equals(meta.posicion)) continue;
//            for (Vector2d pos : POSICIONES) {
//                Vector2d posaux = pos_piedra.subtract(pos);
//                operadores.add(
//                        new MoverPiedra(posicion_jugador, pos_piedra, posaux)
//                );
//            }
//        }
//


        return operadores;
    }
    @Override
    public Operador clone() {
        return this;
    }
}
