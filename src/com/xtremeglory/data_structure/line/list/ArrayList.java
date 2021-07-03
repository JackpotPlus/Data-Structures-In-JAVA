package com.xtremeglory.data_structure.line.list;

import com.xtremeglory.utils.CopyUtils;
import com.xtremeglory.utils.DebugUtils;

import java.util.Comparator;
import java.util.Iterator;

//有序线性表，表的存取以及删除操作不会影响已有元素的顺序
public final class ArrayList<T> implements List<T> {
    private DebugUtils $ = new DebugUtils(this.getClass());

    private static final int DEFAULT_SIZE = 100;
    private static final int INCREASE_SIZE = 20;

    //元素存储位置
    private Object[] elements;
    //已占用大小
    private int size;
    //最大大小
    private int capacity;

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            $.logging("线性表初始大小不能小于等于0", true, true, true);
        }
        if (capacity < DEFAULT_SIZE) {
            capacity = DEFAULT_SIZE;
        }
        this.elements = new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    @Override
    public int size() {
        return this.size;
    }

    private boolean rangeCheck(int index, boolean extend) {
        if (extend) {
            return index >= 0 && index <= this.size;
        } else {
            return index >= 0 && index < this.size;
        }
    }

    @Override
    public void insert(T elem, int index, boolean clone) {
        //判空
        if (elem == null) {
            //线性表允许插入null元素,但会发出警告
            $.logging("当前插入元素为null", false);
        }
        //检查索引
        if (!rangeCheck(index, true)) {
            $.logging("非法的访问下标:" + index + ",当前线性表长度为:" + size, true);
            return;
        }

        //检查空间是否充足
        if (this.size == this.capacity) {
            //扩充容量
            Object[] past = this.elements;
            this.elements = new Object[this.capacity + INCREASE_SIZE];
            this.capacity += INCREASE_SIZE;
            System.arraycopy(past, 0, this.elements, 0, past.length);
        }

        //插入操作
        if (this.size - index > 0) {
            System.arraycopy(this.elements, index, this.elements, index + 1, this.size - index);
        }
        ++this.size;
        if (clone) {
            this.elements[index] = CopyUtils.clone(elem);
        } else {
            this.elements[index] = elem;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (rangeCheck(index, false)) {
            return (T) this.elements[index];
        }
        $.logging("非法的访问下标:" + index + ",当前线性表长度为:" + size, true);
        return null;
    }

    @Override
    public void set(int index, T elem, boolean clone) {
        if (rangeCheck(index, false)) {
            if (clone) {
                elements[index] = CopyUtils.clone(elem);
            } else {
                elements[index] = elem;
            }
        }
        $.logging("非法的访问下标:" + index + ",当前线性表长度为:" + size, true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        if (rangeCheck(index, false)) {
            T elem = (T) this.elements[index];
            System.arraycopy(this.elements, index + 1, this.elements, index, this.size - index - 1);
            --this.size;
            return elem;
        }
        $.logging("非法的访问下标:" + index + ",当前线性表长度为:" + size, true);
        return null;
    }

    @Override
    public void clear() {
        this.size = 0;
    }

    @Override
    public void destroy() {
        this.elements = null;
        this.size = 0;
        this.capacity = 0;
    }

    @Override
    public int indexOf(T elem, int begin) {
        for (int i = begin; i < this.size; ++i) {
            Object element = this.elements[i];
            if (elem.equals(element)) {
                return i;
            }
        }
        $.logging("元素不存在:" + elem, false);
        return ELEM_NOT_EXIST_INDEX;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int indexOf(T elem, Comparator<T> cmp, int begin) {
        for (int i = begin; i < this.size; ++i) {
            T element = (T) this.elements[i];
            if (cmp.compare(elem, element) == 0) {
                return i;
            }
        }
        $.logging("元素不存在:" + elem + ",当前使用自定义比较逻辑", false);
        return ELEM_NOT_EXIST_INDEX;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(this);
    }

    private static class ListIterator<T> implements Iterator<T> {
        private ArrayList<T> list;
        private int index = -1;

        ListIterator(ArrayList<T> list) {
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
}
