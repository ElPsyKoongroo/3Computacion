package si2023.sergiogarcia1alu.shared.utils;

import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.ArrayList;

public interface Polleable<E> {
    void add(E e);

    AbstractCollection<E> get_elementos();

    default boolean isEmpty() {
        return this.get_elementos().isEmpty();
    };

    /**
     * 
     * Saca un elemento de la lista de elementos y lo devuelve.
     * 
     * */
    E poll();

    default int size() {
        return this.get_elementos().size();
    }

    void addAll(ArrayList<E> elements);
}
