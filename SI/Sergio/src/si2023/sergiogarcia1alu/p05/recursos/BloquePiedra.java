package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class BloquePiedra extends Meta implements Comparable<BloquePiedra> {
    public Vector2d posicion;
    public BloquePiedra(Vector2d pos)
    {
        posicion = pos;
        type = RecursosTypes.BloquePiedra.Value;
    }
    @Override
    protected int calcule_hash(){
        this.cached_hash = Objects.hash(RecursosTypes.BloquePiedra.Value, posicion.x, posicion.y);
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

        BloquePiedra other = (BloquePiedra) obj;
        return this.posicion.equals(other.posicion);
    }

    @Override
    public int compareTo(BloquePiedra other) {
        if(this.posicion.x < other.posicion.x) return -1;
        if(this.posicion.x > other.posicion.x) return 1;

        return Double.compare(this.posicion.y, other.posicion.y);
    }
}
