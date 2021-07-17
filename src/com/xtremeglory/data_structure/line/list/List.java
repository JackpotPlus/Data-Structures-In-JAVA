package com.xtremeglory.data_structure.line.list;

import java.util.Comparator;

public interface List<T> extends Iterable<T> {
    //状态变量
    int ELEM_NOT_EXIST_INDEX = -1;

    /**
     * 判断线性表是否为空表
     *
     * @return 线性表是否为空表
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 获取线性表元素数量
     *
     * @return 元素数量
     */
    int size();

    /**
     * 检查是否是合法下标
     *
     * @param index  被检查的下标
     * @param extend 是否是插入操作
     * @return 下标是否合法
     */
    default boolean rangeCheck(int index, boolean extend) {
        if (extend) {
            return index >= 0 && index <= size();
        } else {
            return index >= 0 && index < size();
        }
    }

    //增

    /**
     * 插入元素到线性表
     *
     * @param elem  待插入元素
     * @param index 元素被插入的索引，范围0<=index<=list.size
     */
    default void insert(T elem, int index) {
        insert(elem, index, false);
    }

    /**
     * 插入元素到线性表
     *
     * @param elem  待插入元素
     * @param index 元素被插入的索引，范围0<=index<=list.size
     * @param clone 是否执行深拷贝
     */
    void insert(T elem, int index, boolean clone);

    /**
     * 插入元素到线性表尾部
     *
     * @param elem 待插入元素
     */
    default void insertLast(T elem) {
        insertLast(elem, false);
    }

    /**
     * 插入元素到线性表尾部
     *
     * @param elem  待插入元素
     * @param clone 是否执行深拷贝
     */
    default void insertLast(T elem, boolean clone) {
        insert(elem, size(), clone);
    }

    //查

    /**
     * 通过下标获取元素值
     *
     * @param index 待取元素下标
     * @return 该位置元素，如果没有则返回null
     */
    T get(int index);

    /**
     * 获取最后一个元素值
     *
     * @return 该位置元素，如果没有则返回null
     */
    default T getLast() {
        return get(size() - 1);
    }

    //改

    /**
     * 修改该位置元素为指定值
     *  @param elem  被插入元素
     * @param index 指定位置下标
     * @param clone 是否执行深拷贝
     */
    void set(T elem, int index, boolean clone);

    /**
     * 修改该位置元素为指定值
     *
     * @param index 指定位置下标
     * @param elem  被插入元素
     */
    default void set(int index, T elem) {
        set(elem, index, false);
    }

    /**
     * 用指定数据填充指定长度线性表元素
     *
     * @param elem 指定数据
     * @param size 复制长度
     */
    default void set(T elem, int size) {
        set(elem, size, false);
    }

    //删

    /**
     * 删除指定位置元素
     *
     * @param index 删除的位置
     * @return 被删除的元素
     */
    T remove(int index);

    /**
     * 删除最后位置元素
     *
     * @return 被删除的元素
     */
    default T removeLast() {
        return remove(size() - 1);
    }

    /**
     * 清空线性表，并继续使用
     */
    void clear();

    /**
     * 销毁线性表
     */
    void destroy();

    /**
     * 获取符合equals方法条件的第一个元素下标
     *
     * @param elem 待获取元素
     * @return 对应元素
     */
    default int indexOf(T elem) {
        return indexOf(elem, 0);
    }

    /**
     * 获取从第begin下标开始的,符合equals方法条件的第一个元素下标
     *
     * @param elem  待获取元素
     * @param begin 开始的下标
     * @return 对应元素
     */
    int indexOf(T elem, int begin);

    /**
     * 获取下标
     *
     * @param elem 待获取元素
     * @param cmp  通过比较器定制比较规则,返回第一个匹配的元素,即使得cmp方法结果为0的元素
     * @return 对应元素
     */
    default int indexOf(T elem, Comparator<T> cmp) {
        return indexOf(elem, cmp, 0);
    }

    /**
     * 获取下标
     *
     * @param elem  待获取元素
     * @param cmp   通过比较器定制比较规则,返回第一个匹配的元素,即使得cmp方法结果为0的元素
     * @param begin 开始的下标
     * @return 对应元素
     */
    int indexOf(T elem, Comparator<T> cmp, int begin);

    /**
     * 获取第一个符合条件的元素
     *
     * @param elem 待查找元素
     * @return 被查找的元素
     */
    default T search(T elem) {
        return search(elem, 0);
    }

    /**
     * 从开始下标起获取第一个符合条件的元素
     *
     * @param elem  待查找元素
     * @param begin 开始的下标
     * @return 被查找的元素
     */
    default T search(T elem, int begin) {
        int index = indexOf(elem, begin);
        if (index != ELEM_NOT_EXIST_INDEX) {
            return this.get(index);
        }
        return null;
    }

    /**
     * 通过比较器定制比较规则,从开始下标起获取第一个符合条件的元素
     *
     * @param elem 待查找元素
     * @param cmp  通过比较器定制比较规则,返回第一个匹配的元素,即使得cmp方法结果为0的元素
     * @return 被查找的元素
     */
    default T search(T elem, Comparator<T> cmp) {
        return search(elem, cmp, 0);
    }

    /**
     * 通过比较器定制比较规则,从begin下标起获取第一个符合条件的元素
     *
     * @param elem  待查找元素
     * @param cmp   通过比较器定制比较规则,返回第一个匹配的元素,即使得cmp方法结果为0的元素
     * @param begin 开始的下标
     * @return 被查找的元素
     */
    default T search(T elem, Comparator<T> cmp, int begin) {
        int index = indexOf(elem, cmp, begin);
        if (index != ELEM_NOT_EXIST_INDEX) {
            return this.get(index);
        }
        return null;
    }

    /**
     * 对应元素是否存在
     *
     * @param elem 待查元素
     * @return 待查元素是否存在
     */
    default boolean contains(T elem) {
        return contains(elem, 0);
    }

    /**
     * 查找从begin下标开始,对应元素是否存在
     *
     * @param elem  待查找元素
     * @param begin 开始的下标
     * @return 对应元素是否存在
     */
    default boolean contains(T elem, int begin) {
        return indexOf(elem, begin) != ELEM_NOT_EXIST_INDEX;
    }

    /**
     * 查找从begin下标开始,对应元素是否存在
     *
     * @param elem  待查找元素
     * @param cmp   通过比较器定制比较规则,返回第一个匹配的元素,即使得cmp方法结果为0的元素
     * @param begin 开始的下标
     * @return 对应元素是否存在
     */
    default boolean contains(T elem, Comparator<T> cmp, int begin) {
        return indexOf(elem, cmp, begin) != ELEM_NOT_EXIST_INDEX;
    }

    /**
     * 查找从begin下标开始,对应元素是否存在
     *
     * @param elem 待查找元素
     * @param cmp  通过比较器定制比较规则,返回第一个匹配的元素,即使得cmp方法结果为0的元素
     * @return 对应元素是否存在
     */
    default boolean contains(T elem, Comparator<T> cmp) {
        return contains(elem, cmp, 0);
    }

    default void print() {
        StringBuilder sb = new StringBuilder();
        for (T elem : this) {
            sb.append(elem.toString()).append(",");
        }
        if (sb.length() > 1) {
            System.out.println("[" + sb.substring(0, sb.length() - 1) + "]");
        } else {
            System.out.println("[]");
        }
    }

    /*下列方法为练习题中添加到本API的方法*/
    //[数据结构与算法分析] 第68页3.9题
    default void addAll(Iterable<? extends T> items, boolean clone) {
        for (T elem : items) {
            insertLast(elem, clone);
        }
    }

    default void addAll(Iterable<? extends T> items) {
        addAll(items, false);
    }

    //[数据结构与算法分析] 第68页3.10题
    default void removeAll(Iterable<? extends T> items) {
        for (T elem : items) {
            remove(indexOf(elem));
        }
    }

    //[数据结构与算法分析] 第69页3.16题,略做修改
    List<T> reverse(boolean clone);

    default List<T> reverse() {
        return reverse(false);
    }


}
