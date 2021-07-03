package com.xtremeglory.data_structure.line.list;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<T> implements List<T> {
    private int size = 0;


    private static final class Node<T> {
        T elem;
        Node next;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(T elem, int index, boolean clone) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void set(int index, T elem, boolean clone) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public int indexOf(T elem, int begin) {
        return 0;
    }

    @Override
    public int indexOf(T elem, Comparator<T> cmp, int begin) {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
