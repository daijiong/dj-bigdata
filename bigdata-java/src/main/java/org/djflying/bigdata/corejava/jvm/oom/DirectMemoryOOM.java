package org.djflying.bigdata.corejava.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存溢出
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author dj4817
 * @version $Id: DirectMemoryOOM.java, v 0.1 2017/12/13 15:36 dj4817 Exp $$
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) throws IllegalAccessException {

        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
