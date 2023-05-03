package si2023.sergiogarcia1alu.shared.utils;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class StripsStack<E> {

    private ArrayList<E> elementos;

    public StripsStack() {
        this.elementos = new ArrayList<>();
    }

    public StripsStack(StripsStack<E> other) {
        this.elementos = (ArrayList<E>) other.elementos.clone();
    }

    public void remove(E o) {
        for(int i = this.elementos.size()-1; i>=0; --i) {
            if(this.elementos.equals(o)) {
                this.elementos.remove(i);
                return;
            }
        }
    }
    
    public E poll() {
        return this.elementos.remove(this.elementos.size()-1);
    }

    public ArrayList<E> get_elementos() {
        return this.elementos;
    }

    public E peek() {
        return this.elementos.get(this.elementos.size()-1);
   }
    
    public void add(E element) {
        this.elementos.add(element);
    }
    
    public void addAll(ArrayList<E> elements) {
        this.elementos.addAll(elements);
    }
    
    public E get_index(int i) {
        return this.elementos.get(i);
    }
    
    public int size() {return this.elementos.size();}
    
    public boolean isEmpty() {return this.elementos.isEmpty();}
    
    public boolean contains(Object o) { return this.elementos.contains(o);}

    @Override
    public StripsStack<E> clone() {
        // StripsStack<E> clone = (StripsStack<E>) super.clone();
        return new StripsStack<>(this);
    }
}


