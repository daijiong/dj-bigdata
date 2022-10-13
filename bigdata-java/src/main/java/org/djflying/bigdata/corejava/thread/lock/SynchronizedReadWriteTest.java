package org.djflying.bigdata.corejava.thread.lock;

/**
 * synchronized实现多个线程同时进行读操作
 *
 * @author dj4817
 * @version $Id: SynchronizedReadWriteTest.java, v 0.1 2017/11/28 18:26 dj4817 Exp $$
 */
public class SynchronizedReadWriteTest implements Runnable {

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
    public synchronized void run() {

        Thread currentThread = Thread.currentThread();
        long start = System.currentTimeMillis();
        int i = 0;
        while (System.currentTimeMillis() - start <= 1) {
            i++;
            if (i % 4 == 0) {
                System.out.println(currentThread.getName() + "正在进行写操作");
            } else {
                System.out.println(currentThread.getName() + "正在进行读操作");
            }
        }
        System.out.println(currentThread.getName() + "读写操作完毕");
    }

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        SynchronizedReadWriteTest test = new SynchronizedReadWriteTest();
        Thread thread1 = new Thread(test, "thread1");
        Thread thread2 = new Thread(test, "thread2");
        thread1.start();
        thread2.start();
    }
}
