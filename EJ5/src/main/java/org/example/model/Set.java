package org.example.model;

public interface Set {
    void add(int a);
    void remove(int a);
    boolean isEmpty();

    /**
     * Precondición: El conjunto no está vacío.
     * @return un elemento (elegido de forma determinista o aleatoria)
     */
    int choose();
}
