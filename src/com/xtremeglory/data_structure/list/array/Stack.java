package com.xtremeglory.data_structure.list.array;

import com.xtremeglory.utils.CopyUtils;

public class Stack<T> {
    private static final int DEFAULT_SIZE = 100;
    private static final int INCREASE_SIZE = 20;

    private Object[] element;
    //top指向栈顶元素，初始时top为-1
    private int top;
    private int count;

    public Stack() {
        element = new Object[DEFAULT_SIZE];
        top = -1;
        count = DEFAULT_SIZE;
    }

    public Stack(Stack<T> stack) {
        try {
            element = CopyUtils.clone(stack.element);
            top = stack.top;
            count = stack.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == count - 1;
    }

    public int size() {
        return top + 1;
    }

    public void push(T elem) {
        if (isFull()) {
            realloc();
        }

        element[++top] = elem;
    }

    private void realloc() {
        Object[] past = this.element;
        Object[] current = new Object[count + INCREASE_SIZE];

        element = current;

        //迁移元素
        System.arraycopy(past, 0, current, 0, top + 1);
        count += INCREASE_SIZE;
    }

    @SuppressWarnings("unchecked")
    public T top() {
        if (!isEmpty()) {
            return (T) element[top];
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (!isEmpty()) {
            return (T) element[top--];
        }
        return null;
    }
}
