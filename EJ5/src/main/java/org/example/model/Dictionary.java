package org.example.model;

public interface Dictionary {
    int get(int key);
    Set getKeys();
    void add(int key, int value);
    void remove(int key);
}
