package org.djflying.bigdata.corejava.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示堆内存溢出
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:/dump
 * -XX:+HeapDumpOnOutOfMemoryError表示可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析，文件在项目中。
 * -XX:HeapDumpPath表示dump文件存放的位置
 *
 * @author dj4817
 * @version $Id: HeapOOM.java, v 0.1 2017/12/13 14:15 dj4817 Exp $$
 */
public class HeapOOM {

    /**
     * 堆对象
     */
    static class OOMObject {

        public String[] list = new String[32 * 1024];
    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
