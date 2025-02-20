package org.example.model;

public interface Stack {
    int getTop();
    boolean isEmpty();
    void add(int a);    // método push
    void remove();      // método pop
}
