package com.xtremeglory.utils;

public class DebugUtils {
    private Class<?> clazz;
    private boolean thr = false;
    private boolean log = true;

    public DebugUtils(Class<?> clazz) {
        this.clazz = clazz;
    }

    public DebugUtils(Class<?> clazz, boolean thr, boolean log) {
        this.clazz = clazz;
        this.thr = thr;
        this.log = log;
    }

    public void logging(String msg, boolean error) {
        logging(msg, thr, log, error);
    }

    public void logging(String msg, boolean thr, boolean log, boolean error) {
        if (thr) {
            throw new RuntimeException((error ? "[ERROR]" : "[WARNING]") + msg + "->" + '[' + clazz.getSimpleName() + ']');
        } else if (log) {
            System.err.println((error ? "[ERROR]" : "[WARNING]") + msg + "->" + '[' + clazz.getSimpleName() + ']');
        }
    }
}
