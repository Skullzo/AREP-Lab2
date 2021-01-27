package edu.escuelaing.arep.model;

import java.util.Iterator;

public class LinkedListIterator<E> implements Iterator<E> {
    Node<E> current;

    /**
     * Constructor de la clase LinkedListIterator.
     * @param actual Actual nodo en el que se encuentra el iterador.
     */
    public LinkedListIterator(Node<E> actual) {
        current = actual;
    }

    /**
     * Verifica si el actual nodo tiene un nodo siguiente.
     * @return boolean
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Obtiene el valor del nodo actual
     * @return E
     */
    public E next() {
        E data = current.getData();
        current = current.getNext();
        return data;
    }

    public void remove() {}
}
