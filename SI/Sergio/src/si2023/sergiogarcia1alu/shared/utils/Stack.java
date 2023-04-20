package si2023.sergiogarcia1alu.shared.utils;

import java.util.ArrayDeque;

public class Stack<E> implements Polleable<E> {

    private ArrayDeque<E> elementos;

    public Stack() {
        this.elementos = new ArrayDeque<>();
    }

    public E poll() {
        return this.elementos.pollFirst();
    }

    public ArrayDeque<E> get_elementos() {
        return this.elementos;
    }

    public void add(E element) {
        this.elementos.addFirst(element);
    }
}
