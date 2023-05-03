package si2023.sergiogarcia1alu.p06;

import si2023.sergiogarcia1alu.strips.Meta;

import java.util.Objects;

public class Despejado extends Meta implements Comparable<Despejado> {
    int x;

    public Despejado(int x) {
        this.x = x;
    }

    public Despejado(Despejado other) {
        this.x = other.x;
    }

    public String toString() {
        return String.format("Despejado %d", this.x);
    }

    @Override
    protected int calcule_hash() {
        this.cached = true;
        this.cached_hash = Objects.hash(x);
        return this.cached_hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Despejado other = (Despejado) obj;
        return x == other.x;
    }

    @Override
    public int compareTo(Despejado other) {
        return Integer.compare(this.x, other.x);
    }
}
