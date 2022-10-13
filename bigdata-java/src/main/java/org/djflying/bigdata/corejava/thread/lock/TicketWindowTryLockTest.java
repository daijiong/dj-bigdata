package org.djflying.bigdata.corejava.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock类的tryLock()方法实现火车票销售系统
 *
 * @author dj4817
 * @version $Id: TicketWindowLockTest.java, v 0.1 2017/11/28 12:51 dj4817 Exp $$
 */
public class TicketWindowTryLockTest implements Runnable {

    private int tickets;

    private Lock lock = new ReentrantLock();

    /**
     * 无参构造器
     */
    public TicketWindowTryLockTest() {
    }

    /**
     * 全参构造器
     *
     * @param tickets
     */
    public TicketWindowTryLockTest(int tickets) {
        this.tickets = tickets;
    }

    /**
     * Getter method for property <tt>tickets</tt>.
     *
     * @return property value of tickets
     */
    public int getTickets() {
        return tickets;
    }

    /**
     * Setter method for property <tt>tickets</tt>.
     *
     * @param tickets value to be assigned to property tickets
     */
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    /**
     * 销售一张客票
     */
    public void sellOne() {
        this.tickets--;
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

        while (true) {
            boolean result = false;
            try {
                result = lock.tryLock(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (result) {
                try {
                    System.out.println(Thread.currentThread().getName() + "获得了锁");
                    if (this.getTickets() > 0) {
                        System.out.println("还剩余票:" + this.getTickets() + "张");
                        this.sellOne();
                        System.out.println(Thread.currentThread().getName() + "卖出一张火车票,还剩" + this.getTickets() + "张");
                    } else {
                        System.out.println("余票不足,暂停出售!");
                        break;
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放了锁");
                    System.out.println();
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + "没有获得了锁");
                break;
            }
        }
    }

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        TicketWindowTryLockTest ticketWindowLockTest = new TicketWindowTryLockTest(100);
        Thread thread1 = new Thread(ticketWindowLockTest, "一号窗口");
        Thread thread2 = new Thread(ticketWindowLockTest, "二号窗口");
        Thread thread3 = new Thread(ticketWindowLockTest, "三号窗口");
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
