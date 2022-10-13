package org.djflying.bigdata.corejava.thread;

import java.util.Random;

/**
 * 通过实现Runnable接口方式实现线程
 *
 * @author dj4817
 * @version $Id: ThreadWithImplementsTest.java, v 0.1 2017/11/28 10:54 dj4817 Exp $$
 */
public class ThreadWithImplementsTest implements Runnable {

    private String name;

    /**
     * 空参构造器
     */
    public ThreadWithImplementsTest() {
    }

    /**
     * 全参构造器
     *
     * @param name
     */
    public ThreadWithImplementsTest(String name) {
        this.name = name;
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
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        Thread thread1 = new Thread(new ThreadWithImplementsTest("a"), "thread1");
        Thread thread2 = new Thread(new ThreadWithImplementsTest("b"), "thread2");
        // 开启一个新的线程，执行run()方法
        thread1.start();
        thread2.start();
        // 只执行主线程中的run()方法
        //thread1.run();
        //thread2.run();
    }
}
