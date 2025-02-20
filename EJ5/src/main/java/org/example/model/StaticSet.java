package org.example.model;

public class StaticSet implements Set {
    private static final int MAX = 10000;
    private final int[] array;
    private int count;

    public StaticSet() {
        this.array = new int[MAX];
        this.count = 0;
    }

    @Override
    public void add(int a) {
        // Si el elemento ya existe, no se agrega
        for (int i = 0; i < count; i++) {
            if (array[i] == a) {
                return;
            }
        }
        array[count] = a;
        count++;
    }

    @Override
    public void remove(int a) {
        for (int i = 0; i < count; i++) {
            if (array[i] == a) {
                array[i] = array[count - 1];  // reemplazo por el último elemento
                count--;
                return;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    @Override
    public int choose() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede elegir un elemento de un conjunto vacío");
        }
        // Para simplicidad, devolvemos el primer elemento
        return array[0];
    }
}
