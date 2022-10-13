package org.djflying.bigdata.corejava.jvm.demo;

/**
 * Java线程测试2：
 * 测试线程的死锁情况
 *
 * @author dj4817
 * @version $Id: TestDeadThread.java, v 0.1 2017/12/13 12:48 dj4817 Exp $$
 */
public class TestDeadThread implements Runnable {

    private int a;
    private int b;

    /**
     * 无参构造器
     */
    public TestDeadThread() {
    }

    /**
     * 全参构造器
     *
     * @param a
     * @param b
     */
    public TestDeadThread(int a, int b) {
        this.a = a;
        this.b = b;
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

        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(a + b);
            }
        }
    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {

        // 创建100个线程
        for (int i = 0; i < 100; i++) {
            new Thread(new TestDeadThread(1, 2)).start();
            new Thread(new TestDeadThread(2, 1)).start();
        }
    }
}
