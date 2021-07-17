package com.xtremeglory.data_structure.line.stack;

public interface Stack<T> {

    /**
     * 判断是否为空栈
     *
     * @return 是否为空栈
     */
    boolean isEmpty();

    /**
     * 获取栈元素数量
     *
     * @return 元素数量
     */
    int size();

    /**
     * 入栈
     *
     * @param elem 入栈元素
     */
    default void push(T elem) {
        push(elem, false);
    }

    /**
     * 入栈
     *
     * @param elem  入栈元素
     * @param clone 是否深拷贝
     */
    void push(T elem, boolean clone);

    /**
     * 入栈
     *
     * @param elem 入栈元素
     */
    default void pushIfNotNull(T elem) {
        pushIfNotNull(elem, false);
    }

    /**
     * 入栈
     *
     * @param elem 入栈元素
     */
    default void pushIfNotNull(T elem, boolean clone) {
        if (elem != null) {
            push(elem, clone);
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * 获取栈顶元素,而不弹出
     *
     * @return 栈顶元素
     */
    T top();

    /**
     * 弹出栈顶元素
     *
     * @return 栈顶元素
     */
    T pop();
}
