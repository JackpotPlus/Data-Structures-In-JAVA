package com.xtremeglory.data_structure.line.queue;

public interface Queue<T> {

    boolean isEmpty();

    boolean isFull();

    int size();

    default void enQueue(T elem) {
        enQueue(elem, false);
    }

    void enQueue(T elem, boolean clone);

    default void enQueueIfNotNull(T elem) {
        enQueueIfNotNull(elem, false);
    }

    default void enQueueIfNotNull(T elem, boolean clone) {
        if (elem != null) {
            enQueue(elem, clone);
        } else {
            throw new NullPointerException();
        }
    }

    T front();

    T deQueue();
}
