package com.xtremeglory.data_structure.line;

import com.xtremeglory.data_structure.line.list.DoubleLinkedList;
import com.xtremeglory.data_structure.line.list.List;
import com.xtremeglory.util.DebugUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListTest {
    private List<Integer> list;
    private SimpleList sl;

    @Before
    public void init() {
        list = new DoubleLinkedList<>();
        sl = new SimpleList();
    }

    private void testEqualList() {
        Assert.assertEquals(list.size(), sl.size);
        for (int i = 0; i < sl.size; ++i) {
            if (sl.array[i] != null) {
                Assert.assertEquals((long) list.get(i), (long) sl.array[i]);
            } else {
                Assert.assertNull(list.get(i));
            }
        }
    }

    private void testInsert() {
        int elem = DebugUtil.randomInt(500);
        int index = DebugUtil.randomInt(list.size() + 200);
        if (index > list.size()) {
            Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.insert(elem, index));
        } else {
            list.insert(elem, index);
            sl.insert(elem, index);
            testEqualList();
        }
    }

    private void testInsertNull() {
        int index = DebugUtil.randomInt(list.size() + 200);
        if (index > list.size()) {
            Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.insert(null, index));
        } else {
            list.insert(null, index);
            sl.insert(null, index);
            testEqualList();
        }
    }

    private void testInsertLast() {
        int elem = DebugUtil.randomInt(500);
        list.insertLast(elem);
        sl.insert(elem, sl.size);
        testEqualList();
    }

    private void testInsertLastNull() {
        list.insertLast(null);
        sl.insert(null, sl.size);
        testEqualList();
    }

    private void testGetLast() {
        if (list.size() == 0) {
            Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.getLast());
        } else {
            if (sl.array[sl.size - 1] != null) {
                Assert.assertEquals((long) list.getLast(), (long) sl.array[sl.size - 1]);
            } else {
                Assert.assertNull(list.getLast());
            }
        }
    }

    private void testSet() {
        int elem = DebugUtil.randomInt(500);
        int index = DebugUtil.randomInt(list.size() + 200);
        if (index >= list.size()) {
            Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set((Integer) elem, index));
        } else {
            list.set((Integer) elem, index);
            sl.array[index] = elem;
            testEqualList();
        }
    }

    private void testSetNull() {
        int index = DebugUtil.randomInt(list.size() + 200);
        if (index >= list.size()) {
            Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set(null, index));
        } else {
            list.set(null, index);
            sl.array[index] = null;
            testEqualList();
        }
    }

    private void testRemove() {
        int index = DebugUtil.randomInt(list.size() + 200);
        if (index >= list.size()) {
            Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.remove(index));
        } else {
            Integer ret = list.remove(index);
            if (sl.array[index] != null) {
                Assert.assertEquals(ret, sl.array[index]);
            } else {
                Assert.assertNull(ret);
            }
            sl.remove(index);
            testEqualList();
        }
    }

    private void testRemoveLast() {
        if (list.size() == 0) {
            Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.removeLast());
        } else {
            Integer ret = list.removeLast();
            if (sl.array[sl.size - 1] != null) {
                Assert.assertEquals(ret, sl.array[sl.size - 1]);
            } else {
                Assert.assertNull(ret);
            }
            sl.remove(sl.size - 1);
            testEqualList();
        }
    }

    private void testIndexOf() {
        int index = DebugUtil.randomInt(list.size());
        if (list.size() > 0) {
            Assert.assertEquals(sl.indexOf(sl.array[index]), list.indexOf(sl.array[index]));
        }
    }

    private void testReverse() {
        Assert.assertEquals(list.size(), sl.size);
        List<Integer> reversed = list.reverse();
        for (int i = 0; i < sl.size; ++i) {
            if (sl.array[i] != null) {
                Assert.assertEquals((long) reversed.get(sl.size - i - 1), (long) sl.array[i]);
            } else {
                Assert.assertNull(reversed.get(sl.size - i - 1));
            }
        }
        reversed = reversed.reverse();
        Assert.assertEquals(list.size(), reversed.size());
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) != null) {
                Assert.assertEquals((long) reversed.get(i), (long) list.get(i));
            } else {
                Assert.assertNull(reversed.get(i));
            }
        }
    }

    private void dispatch() {
        int cas = DebugUtil.randomInt(11);
        switch (cas) {
            case 0:
                testInsert();
                break;
            case 1:
                testInsertNull();
                break;
            case 2:
                testInsertLast();
                break;
            case 3:
                testInsertLastNull();
                break;
            case 4:
                testGetLast();
                break;
            case 5:
                testSet();
                break;
            case 6:
                testSetNull();
                break;
            case 7:
                testRemove();
                break;
            case 8:
                testRemoveLast();
                break;
            case 9:
                testIndexOf();
                break;
            case 10:
                testReverse();
                break;
        }
    }

    @Test
    public void test() {
        int test_case = 20000;
        for (int i = 0; i < test_case; ++i) {
            System.out.println(i + "/" + test_case);
            dispatch();
        }
    }
}

class SimpleList {

    Integer[] array;
    int size;

    SimpleList() {
        int test_case = 1000000;
        array = new Integer[test_case];
        size = 0;
    }

    void insert(Integer elem, int index) {
        if (index >= 0 && index <= size) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = elem;
            ++size;
        }
    }

    void remove(int index) {
        if (index >= 0 && index < size) {
            if (size - 1 - index >= 0) System.arraycopy(array, index + 1, array, index, size - 1 - index);
            --size;
        }
    }

    int indexOf(Integer elem) {
        if (elem == null) {
            for (int i = 0; i < size; ++i) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if (elem.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}