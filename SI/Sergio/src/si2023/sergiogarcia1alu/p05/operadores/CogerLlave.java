package si2023.sergiogarcia1alu.p05.operadores;

import ontology.Types;
import si2023.sergiogarcia1alu.p04.agente.nodo.Estado;
import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.p06.Apilar;
import si2023.sergiogarcia1alu.strips.ConjuncionMeta;
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

        this.precondiciones.add(new Llave(this.bloque_llave));

        ConjuncionMeta cm = new ConjuncionMeta(
            new BloqueLibre(this.jugador_pos),
            new Jugador(this.jugador_pos)
        );

//        this.precondiciones.add(new BloqueLibre(this.jugador_pos));
//        this.precondiciones.add(new Jugador(this.jugador_pos));

        this.precondiciones.add(cm);

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

        if (m.type != RecursosTypes.TengoLlave.Value) return operadores;

        Optional<Llave> llave = estado_actual
                        .get_raw_estado_actual()
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

        ArrayList<Vector2d> posiciones_paredes_adyacentes = (ArrayList<Vector2d>) estado_actual.get_raw_estado_actual()
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        CogerLlave other = (CogerLlave) obj;
        return this.bloque_llave.equals(other.bloque_llave) &&
                this.jugador_pos.equals(other.jugador_pos);
    }

    @Override
    public int hashCode(){
        long hash = 331;

        hash ^= (long)(this.jugador_pos.x + 0x9e3779b9 + (hash<<6) + (hash>>2));
        hash ^= (long)(this.jugador_pos.y + 0x9e3779b9 + (hash<<6) + (hash>>2));

        hash ^= (long)(this.bloque_llave.x + 0x9e3779b9 + (hash<<6) + (hash>>2));
        hash ^= (long)(this.bloque_llave.y + 0x9e3779b9 + (hash<<6) + (hash>>2));
        return (int)hash;
    }


    @Override
    public Operador clone() {
        return this;
    }
}
