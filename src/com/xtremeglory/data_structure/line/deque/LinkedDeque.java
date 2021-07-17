package com.xtremeglory.data_structure.line.deque;

import com.xtremeglory.data_structure.line.list.LinkedList;
import com.xtremeglory.data_structure.line.list.List;

/**
 * 双端队列只提供链表实现,这是因为数组实现效率较低
 *
 * @param <T>
 */
public class LinkedDeque<T> implements Deque<T> {
    private List<T> deque = new LinkedList<>();

    @Override
    public int size() {
        return deque.size();
    }

    @Override
    public void push(T elem, boolean clone) {
        deque.insert(elem, 0, clone);
    }

    @Override
    public T pop() {
        return deque.remove(0);
    }

    @Override
    public void inject(T elem, boolean clone) {
        deque.insertLast(elem, clone);
    }

    @Override
    public T eject() {
        return deque.removeLast();
    }
}
