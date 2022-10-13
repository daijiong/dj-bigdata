package org.djflying.bigdata.corejava.thread.sync;

/**
 * synchronized例子：火车售票系统
 * 客票一共有100张，分三个窗口来卖。
 *
 * @author dj4817
 * @version $Id: TicketWindowLockTest.java, v 0.1 2017/11/28 12:51 dj4817 Exp $$
 */
public class TicketWindow implements Runnable {

    private int tickets;

    /**
     * 无参构造器
     */
    public TicketWindow() {
    }

    /**
     * 全参构造器
     *
     * @param tickets
     */
    public TicketWindow(int tickets) {
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
            synchronized (this) {
                if (this.getTickets() > 0) {
                    System.out.println("还剩余票:" + this.getTickets() + "张");
                    this.sellOne();
                    System.out.println(Thread.currentThread().getName() + "卖出一张火车票,还剩" + this.getTickets() + "张");
                } else {
                    System.out.println("余票不足,暂停出售!");
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        TicketWindow ticketWindow = new TicketWindow(100);
        Thread thread1 = new Thread(ticketWindow, "一号窗口");
        Thread thread2 = new Thread(ticketWindow, "二号窗口");
        Thread thread3 = new Thread(ticketWindow, "三号窗口");
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
