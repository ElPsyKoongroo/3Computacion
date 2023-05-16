package si2023.sergiogarcia1alu.shared.utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class PriorityQ<E> implements Polleable<E> {
    private final PriorityQueue<E> cola_prioridad = new PriorityQueue<>();

    @Override
    public void add(E e) {
        this.cola_prioridad.add(e);
    }

    @Override
    public PriorityQueue<E> get_elementos() {
        return this.cola_prioridad;
    }

    public E poll() {
        return this.cola_prioridad.poll();
    }

    @Override
    public void addAll(ArrayList<E> elements) {
        this.cola_prioridad.addAll(elements);
    }
}
