package org.djflying.bigdata.corejava.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过线程池执行线程（Runnable方式）
 *
 * @author dj4817
 * @version $Id: ThreadPoolWithRunnableTest.java, v 0.1 2017/11/29 12:33 dj4817 Exp $$
 */
public class ThreadPoolWithRunnableTest implements Runnable {

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
        System.out.println("thread name: " + currentThread.getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        ThreadPoolWithRunnableTest test = new ThreadPoolWithRunnableTest();
        // 创建一个线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            cachedThreadPool.execute(test);
        }
        cachedThreadPool.shutdown();
    }
}
