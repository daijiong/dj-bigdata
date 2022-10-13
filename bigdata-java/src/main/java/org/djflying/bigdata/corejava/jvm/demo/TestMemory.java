package org.djflying.bigdata.corejava.jvm.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Java内存GC测试
 * 运行时设置的参数为：-Xms100m -Xmx100m -XX:+UseSerialGC
 *
 * @author dj4817
 * @version $Id: TestMemory.java, v 0.1 2017/12/13 10:44 dj4817 Exp $$
 */
public class TestMemory {

    /**
     * 需要添加到堆中的大对象
     */
    static class OOMObject {

        public byte[] placeholder = new byte[64 * 1024];
    }

    /**
     * 堆处理
     * 每隔50ms向堆中添加一个对象，一共添加100次，这样堆中会有100个对象。
     *
     * @param num
     * @throws Exception
     */
    public static void fillHeap(int num) throws Exception {

        ArrayList<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        // 手动触发GC，观察堆中的情况
        System.gc();
    }

    /**
     * 主程序
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // 等待输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        fillHeap(1000);
        // 手动触发GC，观察堆中的情况
        System.gc();
        Thread.sleep(20000000);
    }
}
