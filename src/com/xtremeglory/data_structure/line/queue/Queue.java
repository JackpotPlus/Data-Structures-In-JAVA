package com.xtremeglory.data_structure.line.queue;

public interface Queue<T> {
    boolean isEmpty();

    boolean isFull();

    int size();

    void enQueue(T elem);

    void enQueueIfNotNull(T elem);

    T front();

    T deQueue();
}
