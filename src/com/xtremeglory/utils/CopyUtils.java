package com.xtremeglory.utils;

import java.io.*;

public class CopyUtils {
    private CopyUtils() {
        throw new AssertionError();
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T obj) throws Exception {
        if (!(obj instanceof Serializable)) {
            System.err.println("[error] elem:" + obj + " can't serialize,fail in deep copy!");
        }
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();

        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

}
