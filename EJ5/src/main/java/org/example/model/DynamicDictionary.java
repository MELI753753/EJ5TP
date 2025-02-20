package org.example.model;

import org.example.model.nodes.DictionaryNode;

public class DynamicDictionary implements Dictionary {
    private DictionaryNode node; // primer nodo de la lista

    @Override
    public int get(int key) {
        if (node == null) {
            throw new RuntimeException("Diccionario vacío, no se puede obtener valor");
        }
        DictionaryNode aux = node;
        while (aux != null) {
            if (aux.getKey() == key) {
                return aux.getValue();
            }
            aux = aux.getNext();
        }
        throw new RuntimeException("La clave " + key + " no existe");
    }

    @Override
    public Set getKeys() {
        Set result = new StaticSet();  // usamos la implementación estática de Set
        DictionaryNode aux = node;
        while (aux != null) {
            result.add(aux.getKey());
            aux = aux.getNext();
        }
        return result;
    }

    @Override
    public void add(int key, int value) {
        if (node == null) {
            node = new DictionaryNode(key, value, null);
            return;
        }
        DictionaryNode aux = node;
        while (aux != null) {
            if (aux.getKey() == key) {
                if (aux.getValue() == value) {
                    throw new RuntimeException("El par (key=" + key + ", value=" + value + ") ya existe");
                }
                throw new RuntimeException("La clave " + key + " ya existe");
            }
            if (aux.getNext() == null) {
                aux.setNext(new DictionaryNode(key, value, null));
                return;
            }
            aux = aux.getNext();
        }
    }

    @Override
    public void remove(int key) {
        if (node == null) {
            throw new RuntimeException("La clave " + key + " no existe (diccionario vacío)");
        }
        if (node.getKey() == key) {
            node = node.getNext();
            return;
        }
        DictionaryNode prev = node;
        DictionaryNode current = node.getNext();
        while (current != null) {
            if (current.getKey() == key) {
                prev.setNext(current.getNext());
                return;
            }
            prev = current;
            current = current.getNext();
        }
        throw new RuntimeException("La clave " + key + " no existe");
    }
}
