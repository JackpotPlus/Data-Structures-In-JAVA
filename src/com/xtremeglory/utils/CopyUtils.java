package com.xtremeglory.utils;

import java.io.*;

public class CopyUtils {
    private static DebugUtils $ = new DebugUtils(CopyUtils.class);

    private CopyUtils() {
        throw new AssertionError();
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T obj) {
        if (!(obj instanceof Serializable)) {
            $.logging("不支持的深拷贝操作", true);
        }
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bout);
            oos.writeObject(obj);

            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bin);
            return (T) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

}
