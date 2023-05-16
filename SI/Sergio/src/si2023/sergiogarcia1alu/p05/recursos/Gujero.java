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
        long hash = RecursosTypes.Gujero.Value;

        hash ^= (long)(this.posicion.x + 0x9e3779b9 + (hash<<6) + (hash>>2));
        hash ^= (long)(this.posicion.y + 0x9e3779b9 + (hash<<6) + (hash>>2));

        this.cached_hash = (int)hash;
        this.cached = true;
        return this.cached_hash;
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
