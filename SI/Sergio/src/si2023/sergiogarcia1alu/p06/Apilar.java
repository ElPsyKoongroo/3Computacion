package si2023.sergiogarcia1alu.p06;

import java.util.ArrayList;
import java.util.Objects;

import si2023.sergiogarcia1alu.strips.Operador;
import si2023.sergiogarcia1alu.strips.Meta;
import si2023.sergiogarcia1alu.strips.StripsState;

public class Apilar extends Operador {

    private int x;
    private int y;
    private int z;

    public Apilar() {}

    /**
     *
     * Mueve x desde y hasta z
     *
     * */
    public Apilar(int x2, int y2, int z2) {
        //super();

        this.x = x2;
        this.y = y2;
        this.z = z2;

        // Precondiciones
        this.precondiciones.add(new Menor(x2, z2));
        this.precondiciones.add(new Sobre(x2, y2));
        this.precondiciones.add(new Despejado(x2));
        this.precondiciones.add(new Despejado(z2));


        // Adicion
        this.lista_adicion.add(new Despejado(y2));
        this.lista_adicion.add(new Sobre(x2, z2));

        // Supresion
        this.lista_supresion.add(new Despejado(z2));
        this.lista_supresion.add(new Sobre(x2, y2));

    }


    @Override
    public ArrayList<Operador> gen_posibilidades(Meta meta2, StripsState estado_actual) {

        ArrayList<Operador> pos = new ArrayList<>();

//        if (meta2.getClass() == Sobre.class) {
//            Sobre meta = (Sobre) meta2;
//            for(Meta m: estado_actual.get_estado_actual()) {
//                if(m.getClass() == Sobre.class) {
//                    Sobre s = (Sobre) m;
//                    if(s.x == meta.x) {
//                        int x = meta.x;
//                        int y = s.y;
//                        int z = meta.y;
//                        if (x != y && (x <= z || z < 0)) {
//                            pos.add(new Apilar(x,y,z));
//                        }
//                    }
//                }
//            }
//        } else if (meta2.getClass() == Despejado.class) {
//            Despejado objetivo = (Despejado) meta2;
//            int coso_que_hay_que_mover = 0;
//            for(Meta meta: estado_actual.get_estado_actual()) {
//                if(meta.getClass() == Sobre.class) {
//                    Sobre s = (Sobre) meta;
//                    if(objetivo.x == s.y) {
//                        coso_que_hay_que_mover = s.x;
//                    }
//                }
//            }
//
//            ArrayList<Despejado> despejados = new ArrayList<>();
//            for(Meta meta: estado_actual.get_estado_actual()) {
//                if (meta.getClass() == Despejado.class) {
//                    if(((Despejado)meta).x < 0 || ((Despejado)meta).x > coso_que_hay_que_mover) {
//                        despejados.add((Despejado)meta);
//                    }
//                }
//            }
//
//            for(Despejado d: despejados) {
//                pos.add(new Apilar(coso_que_hay_que_mover, objetivo.x, d.x));
//            }
//
//        } else {
//           // Nothing
//        }

        return pos;
    }

    @Override
    public String toString() {
        return String.format("Apilar  %d|%2d|%2d", this.x, this.y, this.z);
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Apilar other = (Apilar) obj;
        return x == other.x && y == other.y && z == other.z;
    }


    @Override
    public Operador clone() {
        return this;
    }

}
