package com.xtremeglory.data_structure.line.list;

import com.xtremeglory.util.CopyUtils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoubleLinkedList<T> implements List<T> {
    private Logger logger = Logger.getLogger("com.xtremeglory.data_structure.line.list");

    private int size;
    protected Node<T> head;
    protected Node<T> rear;
    protected Node<T> point;

    protected static final class Node<T> {
        T elem;
        Node<T> next;
        Node<T> prev;

        Node() {
            this.elem = null;
            this.next = null;
            this.prev = null;
        }

        Node(T elem, Node<T> prev, Node<T> next, boolean clone) {
            this.elem = clone ? CopyUtils.clone(elem) : elem;
            this.prev = prev;
            this.next = next;
        }
    }

    public DoubleLinkedList() {
        this.size = 0;
        this.head = new Node<>();
        this.rear = this.head;
        this.point = this.head;
    }

    public DoubleLinkedList(Level level) {
        this();
        this.logger.setLevel(level);
    }

    @Override
    public int size() {
        return size;
    }

    protected Node<T> getPrevNode(int index) {
        Node<T> point;
        boolean mode;
        if (size - index >= index) {
            point = head;
            mode = true;
        } else {
            index = size - index;
            point = rear;
            mode = false;
        }

        while (index > 0) {
            point = mode ? point.next : point.prev;
            --index;
        }
        return point;
    }

    protected Node<T> getNode(int index) {
        return getPrevNode(index).next;
    }

    @Override
    public void insert(T elem, int index, boolean clone) {
        if (elem == null) {
            //线性表允许插入null元素,但会发出警告
            logger.warning("当前插入元素为null");
        }

        if (rangeCheck(index, true)) {
            Node<T> prev = getPrevNode(index);
            Node<T> node = new Node<>(elem, prev, prev.next, clone);
            prev.next = node;
            if (node.next != null) {
                node.next.prev = node;
            } else {
                //是最后一个元素,更新指针
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
        if (rangeCheck(index, false)) {
            Node<T> prev = getPrevNode(index);
            Node<T> node = prev.next;
            prev.next = prev.next.next;
            if (prev.next != null) {
                prev.next.prev = prev;
            } else {
                rear = prev;
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
            while (index < size) {
                if (elem.equals(point.elem)) {
                    return index;
                }
                point = point.next;
                ++index;
            }
        } else {
            while (index < size) {
                if (point.elem == null) {
                    return index;
                }
                point = point.next;
                ++index;
            }
        }
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
            while (index < size) {
                if (point.elem != null && cmp.compare(elem, point.elem) == 0) {
                    return index;
                }
                point = point.next;
                ++index;
            }
        } else {
            while (index < size) {
                if (point.elem == null) {
                    return index;
                }
                point = point.next;
                ++index;
            }
        }
        return ELEM_NOT_EXIST_INDEX;
    }

    @Override
    public List<T> reverse(boolean clone) {
        return reverse(head, new DoubleLinkedList<>(), clone);
    }

    protected List<T> reverse(Node<T> node, DoubleLinkedList<T> list, boolean clone) {
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
        return new ListIterator(this.head);
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
