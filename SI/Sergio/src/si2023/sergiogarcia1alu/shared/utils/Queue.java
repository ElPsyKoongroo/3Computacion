package si2023.sergiogarcia1alu.shared.utils;

import java.util.ArrayDeque;

public class Queue<E> implements Polleable<E> {

    private ArrayDeque<E> elementos;
    
    public Queue() {
        this.elementos = new ArrayDeque<>();
    }

    public E poll() {
        return this.elementos.pollFirst();
    }  
    
    public ArrayDeque<E> get_elementos(){
        return this.elementos;
    }
    
    public void add(E element) {
       this.elementos.add(element); 
    }
}
