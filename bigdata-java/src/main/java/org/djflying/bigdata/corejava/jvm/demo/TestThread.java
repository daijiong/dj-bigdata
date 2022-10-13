package org.djflying.bigdata.corejava.jvm.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Java线程测试1：
 * 1. 测试一个执行死循环的线程。
 * 2. 测试一个一直在等待的线程。
 *
 * @author dj4817
 * @version $Id: TestThread.java, v 0.1 2017/12/13 11:21 dj4817 Exp $$
 */
public class TestThread {

    /**
     * 线程死循环
     */
    public static void createBusyThread() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("createBusyThread");
                while (true) {
                }
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待
     *
     * @param lock
     */
    public static void createLockThread(final Object lock) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("createLockThread");
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "testLockThread");
        thread.start();
    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // 等待输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        // 创建一个执行死循环的线程。
        createBusyThread();
        br.readLine();
        // 创建一个一直等待的线程。
        Object object = new Object();
        createLockThread(object);
    }
}
