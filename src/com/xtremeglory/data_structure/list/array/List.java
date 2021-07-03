package com.xtremeglory.data_structure.list.array;

import com.xtremeglory.utils.CopyUtils;

import java.util.Iterator;

//有序线性表，表的存取以及删除操作不会影响已有元素的顺序
public class List<T> implements Iterable<T> {
    private static final int DEFAULT_SIZE = 100;
    private static final int INCREASE_SIZE = 20;

    public static final int ELEM_NOT_EXIST_INDEX = -1;

    //元素存储位置
    private Object[] elements;
    //已占用大小
    private int size;
    //最大大小
    private int capacity;

    public List() {
        elements = new Object[DEFAULT_SIZE];
        this.size = 0;
        this.capacity = DEFAULT_SIZE;
    }

    public List(int capacity) {
        if (capacity < DEFAULT_SIZE)
            capacity = DEFAULT_SIZE;
        elements = new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public List(int size, T elem) {
        this();
        try {
            while (size > 0) {
                this.add(CopyUtils.clone(elem));
                --size;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List(Object[] array) {
        this.elements = array;
        this.size = array.length;
        this.capacity = array.length;
    }

    /**
     * 如果要使用拷贝构造函数，则必须提前实现Serializable接口
     *
     * @param list
     */
    public List(List<T> list) {
        this(list.capacity);
        try {
            this.size = list.size;
            for (int i = 0; i < this.size; ++i) {
                this.elements[i] = CopyUtils.clone(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List(Stack<T> stack) {
        try {
            stack = new Stack<>(stack);
            while (!stack.isEmpty()) {
                this.add(CopyUtils.clone(stack.pop()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private boolean rangeCheck(int index) {
        return index >= 0 && index < this.size;
    }

    public void insert(T elem, int index) {
        //检查索引
        if (!rangeCheck(index)) {
            if (index != this.size) {
                return;
            }
        }

        //检查空间是否充足
        if (this.size == this.capacity) {
            //扩充容量
            Object[] past = this.elements;
            this.elements = new Object[this.capacity + INCREASE_SIZE];
            System.arraycopy(past, 0, this.elements, 0, past.length);
        }

        //插入操作
        if (this.size - index >= 0) {
            System.arraycopy(this.elements, index, this.elements, index + 1, this.size - index);
            ++this.size;
        }
        this.elements[index] = elem;
    }

    public void add(T elem) {
        insert(elem, size());
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (rangeCheck(index)) {
            return (T) this.elements[index];
        }
        return null;
    }

    public T get() {
        return get(size - 1);
    }

    public void set(int index, T elem) {
        if (rangeCheck(index)) {
            elements[index] = elem;
        }
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (rangeCheck(index)) {
            T elem = (T) this.elements[index];
            System.arraycopy(this.elements, index + 1, this.elements, index, this.size - index - 1);
            --this.size;
            return elem;
        }
        return null;
    }

    public T remove() {
        return remove(size - 1);
    }

    public void clear() {
        this.size = 0;
    }

    public void destroy() {
        this.elements = null;
        this.size = 0;
        this.capacity = 0;
    }

    public int indexOf(T elem) {
        for (int i = 0; i < this.size; ++i) {
            Object element = this.elements[i];
            if (elem.equals(element)) {
                return i;
            }
        }
        return ELEM_NOT_EXIST_INDEX;
    }

    @SuppressWarnings("unchecked")
    public T find(T elem) {
        for (int i = 0; i < this.size; ++i) {
            Object element = this.elements[i];
            if (elem.equals(element)) {
                return (T) element;
            }
        }
        return null;
    }

    public boolean contains(T elem) {
        return find(elem) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(this);
    }

    private static class ListIterator<T> implements Iterator<T> {
        private List<T> list;
        private int index = -1;

        ListIterator(List<T> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return index < list.size() - 1;
        }

        @Override
        public T next() {
            return list.get(++index);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new List<>();
        int[] array = {5, 6, 8, 9, 1, 2, 5, 7};
        for (int a : array) {
            list.add(a);
        }
        for (Integer a : list) {
            System.out.println(a);
        }
    }
}
