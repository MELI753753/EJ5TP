package org.example.model.nodes;

public class DictionaryNode {
    private int key;
    private int value;
    private DictionaryNode next;

    public DictionaryNode(int key, int value, DictionaryNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public DictionaryNode getNext() {
        return next;
    }

    public void setNext(DictionaryNode next) {
        this.next = next;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
