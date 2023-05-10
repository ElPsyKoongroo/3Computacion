package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.ConjuncionMeta;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class CogerSeta extends Operador {

    private Vector2d jugador_pos;
    private Vector2d next_bloque;
    final private Vector2d[] POSICIONES = new Vector2d[]{
            new Vector2d(0,1),
            new Vector2d(0,-1),
            new Vector2d(1,0),
            new Vector2d(-1,0),
    };
    public CogerSeta(){}
    public CogerSeta(Vector2d pos, Vector2d next_bloque) {
        super();
        this.jugador_pos = pos;
        this.next_bloque = next_bloque;

        this.precondiciones.add(new Jugador(this.jugador_pos));
        this.precondiciones.add(new BloqueLibre(this.jugador_pos));
        this.precondiciones.add(new Seta(this.next_bloque));

        this.lista_adicion.add(new Jugador(next_bloque));
        this.lista_adicion.add(new BloqueLibre(this.next_bloque));
        this.lista_adicion.add(new TengoSeta());

        this.lista_supresion.add(new Jugador(pos));
        this.lista_supresion.add(new Seta(next_bloque));

        SetAccion(next_bloque.copy().subtract(pos));
    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {
        ArrayList<Operador> operadores = new ArrayList<>();

        if (!(m instanceof TengoSeta)) return operadores;

        Optional<Seta> seta = estado_actual
                .get_raw_estado_actual()
                .get(RecursosTypes.Seta.Value)
                .stream()
                .map(met-> (Seta)met)
                .findFirst();

        // En principio siempre deberia haber una seta el mapa
        // si queremos coger la seta pero por si acaso.
        if(!seta.isPresent()) return operadores;

        Vector2d posicion_seta = seta.get().posicion;

        ArrayList<Vector2d> pos_adyacentes = (ArrayList<Vector2d>) Arrays.stream(POSICIONES)
                .map(posicion -> posicion_seta.copy().add(posicion))
                .collect(Collectors.toList());

        ArrayList<Vector2d> posiciones_paredes_adyacentes = (ArrayList<Vector2d>) estado_actual.get_raw_estado_actual()
                .get(RecursosTypes.Pared.Value)
                .stream()
                .map(p -> ((Pared)p).posicion)
                .filter(pos_adyacentes::contains)
                .collect(Collectors.toList());

        operadores.addAll(
                pos_adyacentes.stream()
                        .filter(pos -> !posiciones_paredes_adyacentes.contains(pos))
                        .map(pos -> new CogerSeta(pos, posicion_seta))
                        .collect(Collectors.toList()));

        return operadores;
    }



    @Override
    public Operador clone() {
        return this;
    }
}
