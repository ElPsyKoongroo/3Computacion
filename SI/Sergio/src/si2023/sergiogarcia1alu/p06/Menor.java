package si2023.sergiogarcia1alu.p06;

import si2023.sergiogarcia1alu.strips.Meta;

import java.util.Objects;

public class Menor extends Meta {
    int x, y;
    public Menor(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Menor(Menor other) {
        this.x = other.x;
        this.y = other.y;
    }
    
    @Override
    public String toString() {
        return String.format("Menor %d - %d", this.x, this.y);
    }

    @Override
    protected int calcule_hash() {
        this.cached = true;
        this.cached_hash = Objects.hash(x, y)*13;
        return this.cached_hash;
    }


//    @Override
//    public int hashCode() {
//        return Objects.hash(x, y)*13;
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Menor other = (Menor) obj;
        return x == other.x && y == other.y;
    }
}
