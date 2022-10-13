package org.djflying.bigdata.corejava.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock类的lock()方法使用测试
 *
 * @author dj4817
 * @version $Id: LockTest.java, v 0.1 2017/11/28 14:29 dj4817 Exp $$
 */
public class LockTest implements Runnable {

    static Lock lock = new ReentrantLock();

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        Thread currentThread = Thread.currentThread();
        lock.lock();
        try {
            System.out.println(currentThread.getName() + "得到了锁");
            Thread.sleep(1000);
            // 业务逻辑处理
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(currentThread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        LockTest test = new LockTest();
        Thread thread1 = new Thread(test, "thread1");
        Thread thread2 = new Thread(test, "thread2");
        thread1.start();
        thread2.start();
    }
}
