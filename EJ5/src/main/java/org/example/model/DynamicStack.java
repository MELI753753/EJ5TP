package org.example.model;

import org.example.model.nodes.Node;

public class DynamicStack implements Stack {
    private Node top; // referencia al nodo tope

    @Override
    public int getTop() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener el tope de una pila vacía");
        }
        return top.getValue();
    }

    @Override
    public boolean isEmpty() {
        return (top == null);
    }

    @Override
    public void add(int a) {
        // Creamos un nuevo nodo que apunta al anterior tope
        top = new Node(a, top);
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar de una pila vacía");
        }
        top = top.getNext();
    }
}

