package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class Seta extends Meta implements Comparable<Seta>{
    public Vector2d posicion;
    public Seta(Vector2d pos) {
        posicion = pos;
        type = RecursosTypes.Seta.Value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        Seta other = (Seta) obj;
        return this.posicion.equals(other.posicion);

    }

    @Override
    protected int calcule_hash()
    {
        return Objects.hash(RecursosTypes.Seta.Value, posicion.x, posicion.y);
    }

    @Override
    public int compareTo(Seta other) {
        if(this.posicion.x < other.posicion.x) return -1;
        if(this.posicion.x > other.posicion.x) return 1;

        return Double.compare(this.posicion.y, other.posicion.y);
    }
}
