package si2023.sergiogarcia1alu.shared.utils;

import java.util.ArrayDeque;

public interface Polleable<E> {
    public void add(E e);

    public ArrayDeque<E> get_elementos();

    public default boolean isEmpty() {
        return this.get_elementos().isEmpty();
    };

    /**
     * 
     * Saca un elemento de la lista de elementos y lo devuelve.
     * 
     * */
    public E poll();

    public default int size() {
        return this.get_elementos().size();
    }
}
