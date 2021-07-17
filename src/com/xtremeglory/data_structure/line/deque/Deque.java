package com.xtremeglory.data_structure.line.deque;

public interface Deque<T> {

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    default void push(T elem) {
        push(elem, false);
    }

    /**
     * 插入到双端队列的前端
     *
     * @param elem
     * @param clone
     */
    void push(T elem, boolean clone);

    default void pushIfNotNull(T elem, boolean clone) {
        if (elem != null) {
            push(elem, clone);
        } else {
            throw new NullPointerException();
        }
    }

    default void pushIfNotNull(T elem) {
        if (elem != null) {
            push(elem, false);
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * 删除双端队列的前端
     *
     * @return
     */
    T pop();

    default void inject(T elem) {
        inject(elem, false);
    }

    /**
     * 插入到双端队列的尾端
     *
     * @param elem
     */
    void inject(T elem, boolean clone);

    default void injectIfNotNull(T elem, boolean clone) {
        if (elem != null) {
            inject(elem, clone);
        } else {
            throw new NullPointerException();
        }
    }

    default void injectIfNotNull(T elem) {
        if (elem != null) {
            inject(elem, false);
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * 删除双端队列的尾端
     *
     * @return
     */
    T eject();
}
