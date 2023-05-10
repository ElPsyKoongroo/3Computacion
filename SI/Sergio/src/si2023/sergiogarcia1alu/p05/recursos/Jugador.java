package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.CogerLlave;
import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class Jugador extends Meta implements Comparable<Jugador>{
    public Vector2d posicion;
    public Jugador(Vector2d pos)  {
        posicion = pos;
        type = RecursosTypes.Jugador.Value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        Jugador other = (Jugador)obj;

        return this.posicion.x == other.posicion.x && this.posicion.y == other.posicion.y;
    }

    @Override
    protected int calcule_hash() {
        this.cached_hash = Objects.hash(RecursosTypes.Pared.Value, posicion.x, posicion.y);
        this.cached = true;
        return this.cached_hash;
    }

    @Override
    public int compareTo(Jugador other) {
        if(this.posicion.x < other.posicion.x) return -1;
        if(this.posicion.x > other.posicion.x) return 1;

        return Double.compare(this.posicion.y, other.posicion.y);
    }
}
