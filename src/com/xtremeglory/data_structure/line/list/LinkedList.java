package com.xtremeglory.data_structure.line.list;

import com.xtremeglory.util.CopyUtils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 带有头结点和尾指针的单链表
 *
 * @param <T>
 */
public class LinkedList<T> implements List<T> {
    private Logger logger = Logger.getLogger("com.xtremeglory.data_structure.line.list");

    private int size;
    protected Node<T> head;
    protected Node<T> rear;
    protected Node<T> point;

    protected static final class Node<T> {
        T elem;
        Node<T> next;

        Node() {
            this.elem = null;
            this.next = null;
        }

        Node(T elem, Node<T> next, boolean clone) {
            this.elem = clone ? CopyUtils.clone(elem) : elem;
            this.next = next;
        }
    }

    public LinkedList() {
        this.size = 0;
        this.head = new Node<>();
        this.rear = this.head;
        this.point = this.head;
    }

    public LinkedList(Level level) {
        this();
        this.logger.setLevel(level);
    }

    @Override
    public int size() {
        return size;
    }

    protected Node<T> getPrevNode(int index) {
        Node<T> point = head;
        if (index == size) {
            point = rear;
        } else {
            while (index > 0) {
                point = point.next;
                --index;
            }
        }
        return point;
    }

    protected Node<T> getNode(int index) {
        return index == size - 1 ? rear : getPrevNode(index).next;
    }

    @Override
    public void insert(T elem, int index, boolean clone) {
        //判空
        if (elem == null) {
            //线性表允许插入null元素,但会发出警告
            logger.warning("当前插入元素为null");
        }
        //检查索引
        if (rangeCheck(index, true)) {
            //插入元素
            //如果是插入到最后一个,直接插入
            Node<T> point = getPrevNode(index);
            Node<T> node = new Node<>(elem, point.next, clone);
            point.next = node;
            if (index == size) {//如果是最后一个,还需要更新尾指针
                rear = node;
            }
            ++size;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @Override
    public T get(int index) {
        if (rangeCheck(index, false)) {
            return getNode(index).elem;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @Override
    public void set(T elem, int index, boolean clone) {
        if (rangeCheck(index, false)) {
            getNode(index).elem = clone ? CopyUtils.clone(elem) : elem;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @Override
    public T remove(int index) {
        //检查索引
        if (rangeCheck(index, false)) {
            Node<T> point = getPrevNode(index);
            Node<T> node = point.next;
            point.next = point.next.next;
            if (index == size - 1) {//如果是最后一个,还需要更新尾指针
                rear = point;
            }
            --size;
            return node.elem;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @Override
    public void clear() {
        size = 0;
        head = new Node<>();
        rear = head;
    }

    @Override
    public void destroy() {
        size = 0;
        head = null;
        rear = null;
    }

    @Override
    public int indexOf(T elem, int begin) {
        if (!rangeCheck(begin, false)) {
            throw new ArrayIndexOutOfBoundsException(begin);
        }
        int index = begin;
        Node<T> point = getNode(begin);
        if (elem != null) {
            while (point != null) {
                if (elem.equals(point.elem)) {
                    return index;
                }
                point = point.next;
                ++index;
            }
        } else {
            while (point != null) {
                if (point.elem == null) {
                    return index;
                }
                point = point.next;
                ++index;
            }
        }

        logger.info("未找到元素:" + elem);
        return ELEM_NOT_EXIST_INDEX;
    }

    @Override
    public int indexOf(T elem, Comparator<T> cmp, int begin) {
        if (!rangeCheck(begin, false)) {
            throw new ArrayIndexOutOfBoundsException(begin);
        }
        int index = begin;
        Node<T> point = getNode(begin);
        if (elem != null) {
            while (point != null) {
                if (point.elem != null && cmp.compare(elem, point.elem) == 0) {
                    return index;
                }
                point = point.next;
                ++index;
            }
        } else {
            while (point != null) {
                if (point.elem == null) {
                    return index;
                }
                point = point.next;
                ++index;
            }
        }

        logger.info("未找到元素:" + elem);
        return ELEM_NOT_EXIST_INDEX;
    }

    @Override
    public List<T> reverse(boolean clone) {
        return reverse(head, new LinkedList<>(), clone);
    }

    protected List<T> reverse(Node<T> node, LinkedList<T> list, boolean clone) {
        if (node.next != null) {
            reverse(node.next, list, clone);
        }
        if (node != head) {
            list.insertLast(node.elem, clone);
        }
        return list;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(head);
    }

    class ListIterator implements Iterator<T> {
        Node<T> node;

        ListIterator(Node<T> node) {
            this.node = node.next;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public T next() {
            return node.elem;
        }
    }
}
