package org.djflying.bigdata.corejava.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock类的tryLock()方法使用测试
 *
 * @author dj4817
 * @version $Id: TryLockTest.java, v 0.1 2017/11/28 14:56 dj4817 Exp $$
 */
public class TryLockTest implements Runnable {

    private static Lock lock = new ReentrantLock();

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

        Thread thread = Thread.currentThread();
        boolean result = lock.tryLock();
        if (result) {
            try {
                System.out.println(thread.getName() + "得到了锁");
                Thread.sleep(1000);
                // 业务逻辑处理
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName() + "没得到了锁");
        }
    }

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        TryLockTest test = new TryLockTest();
        Thread thread1 = new Thread(test, "thread1");
        Thread thread2 = new Thread(test, "thread2");
        thread1.start();
        thread2.start();
    }
}
