package com.xtremeglory.data_structure.list.array;

public class Queue<T> {
    private static final int DEFAULT_SIZE = 20;
    private static final int INCREASE_SIZE = 20;

    private Object[] element;
    //front指向队首元素
    //rear指向队尾元素的下一个元素
    //初始时，front指向0，rear指向0
    //队空时，front==rear
    //队满时，rear+1%size==front
    private int front, rear;
    private int count;

    public Queue() {
        element = new Object[DEFAULT_SIZE];
        count = DEFAULT_SIZE;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % count == front;
    }

    public int size() {
        return rear < front ?
                count - front + rear :
                rear - front;
    }

    public void enQueue(T elem) {
        if (isFull()) {
            realloc();
        }

        element[rear] = elem;
        rear = (rear + 1) % count;
    }

    public void enQueueIfNotNull(T elem) {
        if (elem != null) {
            enQueue(elem);
        }
    }

    private void realloc() {
        Object[] past = this.element;
        Object[] current = new Object[count + INCREASE_SIZE];
        element = current;

        //迁移元素
        if (rear < front) {
            int next = count - front;
            System.arraycopy(past, front, current, 0, next);
            System.arraycopy(past, front, current, next, rear);
        } else {
            System.arraycopy(past, front, current, 0, rear - front);
        }

        count += INCREASE_SIZE;

        //重置front和rear
        front = 0;
        rear = size();
    }

    @SuppressWarnings("unchecked")
    public T front() {
        if (!isEmpty()) {
            return (T) element[front];
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T deQueue() {
        if (!isEmpty()) {
            front = (front + 1) % count;
            return (T) element[front - 1];
        }
        return null;
    }

    public boolean contains(T elem) {
        if (this.isEmpty()) {
            return false;
        }
        if (front < rear) {
            for (int i = front; i < rear; i = (i + 1) % count) {
                Object e = element[i];
                if (e.equals(elem)) {
                    return true;
                }
            }
        } else {
            for (int i = front; i >= front || i < rear; i = (i + 1) % count) {
                Object e = element[i];
                if (e.equals(elem)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Queue<Integer> list = new Queue<>();
        int[] array = {5, 6, 8, 9, 1, 2, 5, 7};
        for (int a : array) {
            list.enQueue(a);
        }
        System.out.println(list.contains(10));

    }
}
