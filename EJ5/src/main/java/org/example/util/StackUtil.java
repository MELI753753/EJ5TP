package org.example.util;

import org.example.model.Stack;
import org.example.model.DynamicStack;

public class StackUtil {

    /**
     * Crea y retorna una copia de la pila 'stack' sin modificar la original.
     */
    public static Stack copy(Stack stack) {
        Stack aux = new DynamicStack();
        Stack aux2 = new DynamicStack();

        while (!stack.isEmpty()) {
            aux.add(stack.getTop());
            aux2.add(stack.getTop());
            stack.remove();
        }

        while (!aux2.isEmpty()) {
            stack.add(aux2.getTop());
            aux2.remove();
        }

        while (!aux.isEmpty()) {
            aux2.add(aux.getTop());
            aux.remove();
        }

        return aux2;
    }

    /**
     * Imprime los elementos de la pila (tope primero).
     */
    public static void print(Stack stack) {
        Stack copy = copy(stack);
        while (!copy.isEmpty()) {
            System.out.println(copy.getTop());
            copy.remove();
        }
    }
}
