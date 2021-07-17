package com.xtremeglory.data_structure.line.queue;

import com.xtremeglory.exeption.EmptyQueueException;
import com.xtremeglory.util.CopyUtils;

public class ArrayQueue<T> implements Queue<T> {
    private static final int DEFAULT_SIZE = 20;
    private static final int INCREASE_SIZE = 20;

    protected Object[] element;
    //front指向队首元素
    //rear指向队尾元素的下一个元素
    //初始时，front指向0，rear指向0
    //队空时，front==rear
    //队满时，rear+1%size==front
    protected int front, rear;
    protected int capacity;

    public ArrayQueue() {
        this.element = new Object[DEFAULT_SIZE];
        this.front = 0;
        this.rear = 0;
        this.capacity = DEFAULT_SIZE;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    @Override
    public int size() {
        return rear < front ?
                capacity - front + rear :
                rear - front;
    }

    @Override
    public void enQueue(T elem, boolean clone) {
        if (isFull()) {
            realloc();
        }

        element[rear] = clone ? CopyUtils.clone(elem) : elem;
        rear = (rear + 1) % capacity;
    }

    private void realloc() {
        Object[] past = this.element;
        Object[] current = new Object[capacity + INCREASE_SIZE];
        element = current;

        //迁移元素
        if (rear < front) {
            int next = capacity - front;
            System.arraycopy(past, front, current, 0, next);
            System.arraycopy(past, front, current, next, rear);
        } else {
            System.arraycopy(past, front, current, 0, rear - front);
        }

        capacity += INCREASE_SIZE;

        //重置front和rear
        front = 0;
        rear = size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T front() {
        if (!isEmpty()) {
            return (T) element[front];
        } else {
            throw new EmptyQueueException();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deQueue() {
        if (!isEmpty()) {
            front = (front + 1) % capacity;
            return (T) element[front - 1];
        } else {
            throw new EmptyQueueException();
        }
    }
}
