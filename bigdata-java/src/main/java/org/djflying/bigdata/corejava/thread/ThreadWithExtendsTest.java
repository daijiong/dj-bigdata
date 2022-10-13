package org.djflying.bigdata.corejava.thread;

import java.util.Random;

/**
 * 通过继承方式实现线程
 *
 * @author dj4817
 * @version $Id: ThreadWithExtendsTest.java, v 0.1 2017/11/28 10:21 dj4817 Exp $$
 */
public class ThreadWithExtendsTest extends Thread {

    private String name;

    /**
     * 空参构造器
     */
    public ThreadWithExtendsTest() {
    }

    /**
     * 全参构造器
     *
     * @param name
     */
    public ThreadWithExtendsTest(String name) {
        this.name = name;
    }


    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {

        // 获取当前线程的名称
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "线程的run方法被调用……");
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(random.nextInt(10) * 100);
                System.out.println(threadName + "...." + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        ThreadWithExtendsTest threadWithExtendsTest1 = new ThreadWithExtendsTest("a");
        ThreadWithExtendsTest threadWithExtendsTest2 = new ThreadWithExtendsTest("b");
        // 开启一个新的线程，执行run()方法
        threadWithExtendsTest1.start();
        threadWithExtendsTest2.start();
        // 只执行主线程中的run()方法
        //threadWithExtendsTest1.run();
        //threadWithExtendsTest2.run();
    }
}


