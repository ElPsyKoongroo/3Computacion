package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class Pared extends Meta implements Comparable<Pared> {
    public Vector2d posicion;
    public Pared(Vector2d pos) {
        posicion = pos;
        type = RecursosTypes.Pared.Value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        Pared other = (Pared) obj;
        return this.posicion.equals(other.posicion);
    }

    @Override
    protected int calcule_hash() {
        long hash = RecursosTypes.Pared.Value;

        hash ^= (long)(this.posicion.x + 0x9e3779b9 + (hash<<6) + (hash>>2));
        hash ^= (long)(this.posicion.y + 0x9e3779b9 + (hash<<6) + (hash>>2));

        this.cached_hash = (int)hash;
        this.cached = true;
        return this.cached_hash;
    }

    @Override
    public int compareTo(Pared other) {
        if(this.posicion.x < other.posicion.x) return -1;
        if(this.posicion.x > other.posicion.x) return 1;

        return Double.compare(this.posicion.y, other.posicion.y);
    }
}
