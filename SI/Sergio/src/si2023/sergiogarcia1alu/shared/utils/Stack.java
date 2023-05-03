package si2023.sergiogarcia1alu.shared.utils;

import java.util.ArrayDeque;
import java.util.ArrayList;

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

    public E peek() {
        return this.elementos.peekFirst();
    }

    public void add(E element) {
        this.elementos.addFirst(element);
    }

    public void add_unique(E elemet) {
        if (!this.elementos.contains(elemet)) {
            this.add(elemet);
        }
    }
    
    
    public void addAll(ArrayList<E> elements) {
        for (E element : elements) {
            this.add(element);
        }
//        this.elementos.addAll(elements);
    }
}
