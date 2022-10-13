package org.djflying.bigdata.corejava.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock类的lockInterruptibly()方法使用测试
 *
 * @author dj4817
 * @version $Id: LockInterruptiblyTest.java, v 0.1 2017/11/28 15:43 dj4817 Exp $$
 */
public class LockInterruptiblyTest implements Runnable {

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

        Thread currentThread = Thread.currentThread();
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(currentThread.getName() + "得到了锁");
                Thread.sleep(10000);
                // 业务逻辑处理
            } finally {
                lock.unlock();
                System.out.println(currentThread.getName() + "释放了锁");
            }
        } catch (Exception e) {
            System.out.println(currentThread.getName() + "被中断");
        }
    }

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        LockInterruptiblyTest test = new LockInterruptiblyTest();
        Thread thread1 = new Thread(test, "thread1");
        Thread thread2 = new Thread(test, "thread2");
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }
}

