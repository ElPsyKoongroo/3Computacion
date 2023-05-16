package si2023.sergiogarcia1alu.shared.utils;

import java.util.Objects;

public class MyPair<E> {
    public E one;
    public E chu;

    public MyPair(E x, E y){
        this.one = x;
        this.chu = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        MyPair other = (MyPair) obj;

        return this.one.equals(other.one) && this.chu.equals(other.chu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.one, this.chu);
    }
}
