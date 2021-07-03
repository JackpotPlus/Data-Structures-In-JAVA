package com.xtremeglory.data_structure.line.stack;

import com.xtremeglory.data_structure.line.list.ArrayList;
import com.xtremeglory.data_structure.line.list.List;
import com.xtremeglory.utils.DebugUtils;

public class ArrayStack<T> implements Stack<T> {
    private DebugUtils $ = new DebugUtils(this.getClass());
    private List<T> stack = new ArrayList<>();

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
        if (this.size() > 0) {
            return stack.getLast();
        }
        $.logging("当前栈为空栈!", true);
        return null;
    }

    @Override
    public T pop() {
        if (this.size() > 0) {
            return stack.removeLast();
        }
        $.logging("当前栈为空栈!", true);
        return null;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        stack.top();
        stack.push(1);
        stack.pushIfNotNull(null);
        stack.pop();
        stack.pop();
    }
}
