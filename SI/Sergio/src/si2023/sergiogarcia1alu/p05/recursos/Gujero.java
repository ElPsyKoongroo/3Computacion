package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class Gujero extends Meta implements Comparable<Gujero> {

    public Vector2d posicion;
    public Gujero(Vector2d pos) {
        posicion = pos;
        type = RecursosTypes.Gujero.Value;
    }
    @Override
    protected int calcule_hash() {
        this.cached_hash = Objects.hash(RecursosTypes.Gujero.Value, posicion.x, posicion.y);
        this.cached = true;
        return cached_hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        Gujero other = (Gujero) obj;
        return this.posicion.equals(other.posicion);

    }

    @Override
    public int compareTo(Gujero other) {
        if(this.posicion.x < other.posicion.x) return -1;
        if(this.posicion.x > other.posicion.x) return 1;

        return Double.compare(this.posicion.y, other.posicion.y);
    }
}
