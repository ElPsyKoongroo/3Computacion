package si2023.sergiogarcia1alu.shared.utils;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Queue<E> implements Polleable<E> {

    private ArrayDeque<E> elementos;
    
    public Queue() {
        this.elementos = new ArrayDeque<>();
    }

    public E poll() {
        return this.elementos.pollFirst();
    }  
    
    public E poll_last() {
        return this.elementos.pollLast();
    }
    
    public ArrayDeque<E> get_elementos(){
        return this.elementos;
    }
    
    public void add(E element) {
       this.elementos.add(element); 
    }
    
    public void addAll(ArrayList<E> elements) {
        this.elementos.addAll(elements);
    }
}
