package org.djflying.bigdata.corejava.thread.sync;

/**
 * synchronized同步锁
 *
 * @author dj4817
 * @version $Id: SynchronizedTest.java, v 0.1 2017/11/28 11:30 dj4817 Exp $$
 */
public class SynchronizedTest {

    private String name;

    /**
     * 无参构造器
     */
    public SynchronizedTest() {
    }

    /**
     * 全参构造器
     *
     * @param name
     */
    public SynchronizedTest(String name) {
        this.name = name;
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

        SynchronizedTest synchronizedTest = new SynchronizedTest("a");
        SynchronizedTest synchronizedTest2 = new SynchronizedTest("b");

        new Thread("thread1") {

            @Override
            public void run() {

                System.out.println(this.getName() + "线程的run方法被调用……");
                synchronized (synchronizedTest) {
                    try {
                        System.out.println("锁名称为：" + synchronizedTest.getName());
                        System.out.println(this.getName() + " start");
                        Thread.sleep(5000);
                        System.out.println(this.getName() + "醒了");
                        System.out.println(this.getName() + " end");
                        // 如果发生异常，jvm会自动将锁释放
                        int i = 1 / 0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread("thread2") {

            @Override
            public void run() {

                System.out.println(this.getName() + "线程的run方法被调用……");
                // 如果不是同一把锁，线程2不用等待直接执行
                //synchronized (synchronizedTest2) {
                // 争抢同一把锁时，线程1没释放之前，线程2只能等待
                synchronized (synchronizedTest) {
                    System.out.println("锁名称为：" + synchronizedTest.getName());
                    System.out.println(this.getName() + " start");
                    System.out.println(this.getName() + " end");
                }
            }
        }.start();
    }
}
