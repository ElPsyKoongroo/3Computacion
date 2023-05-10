package si2023.sergiogarcia1alu.p05.operadores;

import si2023.sergiogarcia1alu.p05.recursos.*;
import si2023.sergiogarcia1alu.strips.ConjuncionMeta;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.StripsState;
import tools.Vector2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaparAbujero extends Operador {

    private Vector2d jugador_pos;
    private Vector2d bloque_piedra;
    private Vector2d bloque_bujero;
    private Vector2d[] POSICIONES = new Vector2d[]{
            new Vector2d(0,1),
            new Vector2d(0,-1),
            new Vector2d(1,0),
            new Vector2d(-1,0),
    };
    public TaparAbujero() {}
    public TaparAbujero(Vector2d pos, Vector2d bloque_piedra, Vector2d bloque_bujero) {
        super();
        this.jugador_pos = pos;
        this.bloque_piedra = bloque_piedra;
        this.bloque_bujero = bloque_bujero;

//        ConjuncionMeta cm = new ConjuncionMeta(
//            new Jugador(pos),
//            new BloquePiedra(bloque_piedra)
//        );


        this.precondiciones.add(new Jugador(pos));
        this.precondiciones.add(new BloquePiedra(bloque_piedra));

        this.precondiciones.add(new Gujero(bloque_bujero));
//        this.precondiciones.add(cm);

        this.lista_adicion.add(new Jugador(bloque_piedra));
        this.lista_adicion.add(new BloqueLibre(bloque_piedra));
        this.lista_adicion.add(new BloqueLibre(bloque_bujero));

        this.lista_supresion.add(new Jugador(pos));
        this.lista_supresion.add(new BloquePiedra(bloque_piedra));
        this.lista_supresion.add(new Gujero(bloque_bujero));

        SetAccion(bloque_piedra.copy().subtract(pos));
    }

    @Override
    public ArrayList<Operador> gen_posibilidades(Meta m, StripsState estado_actual) {

        switch (m.type)
        {
            case 0: { // BloqueLibre
                return tapar(((BloqueLibre)m).posicion, estado_actual);
            }
            case 7: { // Jugador
                return mover_piedra(((Jugador)m).posicion, estado_actual);
            }
            default: return new ArrayList<>();
        }

    }

    public ArrayList<Operador> mover_piedra(Vector2d posicion_piedra, StripsState estado_actual) {
        ArrayList<Operador> operadores = new ArrayList<>();

        Arrays.stream(POSICIONES)
            .filter(pos -> estado_actual.get_raw_estado_actual()
                    .get(RecursosTypes.Gujero.Value)
                    .stream()
                    .map(obj -> ((Gujero)obj).posicion)
                    .anyMatch(gujero_pos -> gujero_pos.equals(posicion_piedra.copy().add(pos))))
            .filter(pos -> estado_actual.get_raw_estado_actual()
                    .get(RecursosTypes.Pared.Value)
                    .stream()
                    .map(obj -> ((Pared)obj).posicion)
                    .noneMatch(pos_pared -> pos_pared.equals(posicion_piedra.copy().subtract(pos))))
            .forEach(dir_libre ->
            {
                operadores.add(
                new TaparAbujero(
                        posicion_piedra.copy().subtract(dir_libre),
                        posicion_piedra,
                        posicion_piedra.copy().add(dir_libre)
                ));
            }
        );
        return operadores;
    }

    public ArrayList<Operador> tapar(Vector2d posicion_dst, StripsState estado_actual) {

        ArrayList<Operador> operadores = new ArrayList<>();

        boolean hay_gujero = estado_actual.get_raw_estado_actual()
                .get(RecursosTypes.Gujero.Value)
                .stream()
                .map(obj -> ((Gujero)obj).posicion)
                .anyMatch(pos_gujero -> pos_gujero.equals(posicion_dst));

        if (!hay_gujero) return operadores;

        ArrayList<Vector2d> paredes_pos = (ArrayList<Vector2d>)
                estado_actual.get_raw_estado_actual_type(RecursosTypes.Pared.Value)
                        .stream().map(par -> ((Pared)par).posicion)
                        .collect(Collectors.toList());


        ArrayList<Vector2d[]> posiciones_validas = (ArrayList<Vector2d[]>) Arrays.stream(POSICIONES)
                .map(dir -> new Vector2d[]{posicion_dst.copy().add(dir), posicion_dst.copy().add(dir.copy().mul(2))})
                .filter(pareja -> Arrays.stream(pareja).noneMatch(paredes_pos::contains))
                .collect(Collectors.toList());

        for(Vector2d[] posiciones : posiciones_validas) {
            operadores.add(new TaparAbujero(posiciones[1], posiciones[0], posicion_dst));
        }
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
        TaparAbujero other = (TaparAbujero) obj;

        return  this.bloque_piedra.equals(other.bloque_piedra) &&
                this.bloque_bujero.equals(other.bloque_bujero) &&
                this.jugador_pos.equals(other.jugador_pos);
    }

    @Override
    public int hashCode(){
        int hash = 31;
        hash = Objects.hash(this.bloque_piedra.x, this.bloque_piedra.y, hash);
        hash = Objects.hash(this.jugador_pos.x, this.jugador_pos.y, hash);
        hash = Objects.hash(this.bloque_bujero.x, this.bloque_bujero.y, hash);
        return hash;
    }


    @Override
    public Operador clone() {
        return this;
    }
}
