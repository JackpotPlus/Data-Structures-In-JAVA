package com.xtremeglory.data_structure.line.stack;

import com.xtremeglory.data_structure.line.list.DoubleLinkedList;
import com.xtremeglory.data_structure.line.list.List;

import java.util.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {
    private List<T> stack = new DoubleLinkedList<>();

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void push(T elem, boolean clone) {
        stack.insertLast(elem, clone);
    }

    @Override
    public T top() {
        if (!isEmpty()) {
            return stack.getLast();
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public T pop() {
        if (!isEmpty()) {
            return stack.removeLast();
        } else {
            throw new EmptyStackException();
        }
    }
}
