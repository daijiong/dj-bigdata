package org.djflying.bigdata.corejava.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用读写锁实现多个线程同时进行读操作
 *
 * @author dj4817
 * @version $Id: ReentrantReadWriteLockTest.java, v 0.1 2017/11/28 18:35 dj4817 Exp $$
 */
public class ReentrantReadWriteLockTest implements Runnable {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 读操作,用读锁来锁定
     *
     * @param thread
     */
    public void get(Thread thread) {

        lock.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 写操作，用写锁来锁定
     *
     * @param thread
     */
    public void write(Thread thread) {

        lock.writeLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在进行写操作");
            }
            System.out.println(thread.getName() + "写操作完毕");
        } finally {
            lock.writeLock().unlock();
        }
    }

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
        get(currentThread);
        write(currentThread);
    }


    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
        Thread thread1 = new Thread(test, "thread1");
        Thread thread2 = new Thread(test, "thread2");
        thread1.start();
        thread2.start();
    }
}
