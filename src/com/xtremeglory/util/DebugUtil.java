package com.xtremeglory.util;

public class DebugUtil {
    public static int randomInt(int size, int offset) {
        return offset + (int) (Math.random() * size);
    }

    public static int randomInt(int size) {
        return (int) (Math.random() * size);
    }
}
