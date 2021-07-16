package com.xtremeglory.data_structure.line.list;

import com.xtremeglory.util.CopyUtils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.logging.Level;
import java.util.logging.Logger;

//有序线性表，表的存取以及删除操作不会影响已有元素的顺序
public class ArrayList<T> implements List<T>, RandomAccess {
    private Logger logger = Logger.getLogger("com.xtremeglory.data_structure.line.list");

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
            throw new IllegalArgumentException("线性表的初始长度必须大于0!");
        }
        if (capacity < DEFAULT_SIZE) {
            capacity = DEFAULT_SIZE;
        }
        this.elements = new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public ArrayList(Level level) {
        this();
        logger.setLevel(level);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(T elem, int index, boolean clone) {
        //判空
        if (elem == null) {
            //线性表允许插入null元素,但会发出警告
            logger.warning("输入元素为null");
        }
        //检查索引
        if (rangeCheck(index, true)) {
            //检查空间是否充足
            if (size == capacity) {
                //扩充容量
                Object[] past = elements;
                elements = new Object[this.capacity + INCREASE_SIZE];
                capacity += INCREASE_SIZE;
                System.arraycopy(past, 0, this.elements, 0, past.length);
            }

            //插入操作
            if (size - index > 0) {
                System.arraycopy(elements, index, elements, index + 1, size - index);
            }
            elements[index] = clone ? CopyUtils.clone(elem) : elem;
            ++size;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (rangeCheck(index, false)) {
            return (T) elements[index];
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @Override
    public void set(T elem, int index, boolean clone) {
        if (rangeCheck(index, false)) {
            elements[index] = clone ? CopyUtils.clone(elem) : elem;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        if (rangeCheck(index, false)) {
            T elem = (T) elements[index];
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
            --size;
            return elem;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public void destroy() {
        elements = null;
        size = 0;
        capacity = 0;
    }

    @Override
    public int indexOf(T elem, int begin) {
        if (!rangeCheck(begin, false)) {
            throw new ArrayIndexOutOfBoundsException(begin);
        }
        if (elem != null) {
            for (int i = begin; i < size; ++i) {
                Object element = elements[i];
                if (elem.equals(element)) {
                    return i;
                }
            }
        } else {
            for (int i = begin; i < size; ++i) {
                if (elements[i] == null) {
                    return i;
                }
            }
        }
        logger.info("未找到元素:" + elem);
        return ELEM_NOT_EXIST_INDEX;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int indexOf(T elem, Comparator<T> cmp, int begin) {
        if (!rangeCheck(begin, false)) {
            throw new ArrayIndexOutOfBoundsException(begin);
        }
        if (elem != null) {
            for (int i = begin; i < size; ++i) {
                T element = (T) elements[i];
                if (element != null && cmp.compare(elem, element) == 0) {
                    return i;
                }
            }
        } else {
            for (int i = begin; i < size; ++i) {
                if (elements[i] == null) {
                    return i;
                }
            }
        }
        logger.info("未找到元素:" + elem);
        return ELEM_NOT_EXIST_INDEX;
    }

    @Override
    public List<T> reverse(boolean clone) {
        List<T> list = new ArrayList<>();
        for (int i = size - 1; i > -1; --i) {
            list.insertLast(this.get(i));
        }
        return list;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T elem : this) {
            sb.append(elem == null ? "<null>" : elem.toString()).append(",");
        }
        if (sb.length() > 1) {
            return "[" + sb.substring(0, sb.length() - 1) + "]";
        } else {
            return "[]";
        }
    }
}
