package com.xtremeglory.data_structure.line.queue;

import com.xtremeglory.data_structure.line.list.LinkedList;
import com.xtremeglory.data_structure.line.list.List;
import com.xtremeglory.exeption.EmptyQueueException;

public class LinkedQueue<T> implements Queue<T> {
    private List<T> queue = new LinkedList<>();

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void enQueue(T elem, boolean clone) {
        queue.insertLast(elem, clone);
    }

    @Override
    public T front() {
        if (!queue.isEmpty()) {
            return queue.get(0);
        } else {
            throw new EmptyQueueException();
        }
    }

    @Override
    public T deQueue() {
        if (!queue.isEmpty()) {
            return queue.remove(0);
        } else {
            throw new EmptyQueueException();
        }
    }
}
