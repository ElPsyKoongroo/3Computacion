package si2023.sergiogarcia1alu.p05.operadores;

import ontology.Types;
import si2023.sergiogarcia1alu.p04.agente.nodo.Estado;
import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.p06.Apilar;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.*;
import java.util.stream.Collectors;

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
    public CogerLlave(Vector2d pos, Vector2d bloque_llave) {
        super();
        this.jugador_pos = pos;
        this.bloque_llave = bloque_llave;

        this.precondiciones.add(new Jugador(this.jugador_pos));
        this.precondiciones.add(new Llave(this.bloque_llave));

        this.lista_adicion.add(new Jugador(this.bloque_llave));
        this.lista_adicion.add(new BloqueLibre(this.bloque_llave));
        this.lista_adicion.add(new TengoLlave());

        this.lista_supresion.add(new Jugador(this.jugador_pos));
        this.lista_supresion.add(new Llave(this.bloque_llave));

        //  3 2
        //- 3 3
        //  0 1
        SetAccion(bloque_llave.copy().subtract(pos));
    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {
        ArrayList<Operador> operadores = new ArrayList<>();

        if (!(m instanceof TengoLlave)) return operadores;

        Optional<Llave> llave = estado_actual
                        .get_estado_actual()
                        .get(RecursosTypes.Llave.Value)
                        .stream()
                        .map(met-> (Llave)met)
                        .findFirst();

        // En principio siempre deberia haber una llave en el mapa
        // si queremos coger la llave pero por si acaso.
        if(!llave.isPresent()) return operadores;

        Vector2d posicion_llave = llave.get().posicion;

        ArrayList<Vector2d> pos_adyacentes =(ArrayList<Vector2d>) Arrays.stream(POSICIONES)
                .map(posicion -> posicion_llave.copy().add(posicion))
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
                .map(pos -> new CogerLlave(pos, posicion_llave))
                .collect(Collectors.toList()));

        return operadores;

    }

    @Override
    public Operador clone() {
        return this;
    }
}
