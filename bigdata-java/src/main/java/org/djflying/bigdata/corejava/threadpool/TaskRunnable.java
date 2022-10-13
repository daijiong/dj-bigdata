package org.djflying.bigdata.corejava.threadpool;

/**
 * 实现了Runnable接口的任务
 *
 * @author dj4817
 * @version $Id: TaskRunnable.java, v 0.1 2017/11/29 13:31 dj4817 Exp $$
 */
public class TaskRunnable implements Runnable {

    private int flag;

    public TaskRunnable() {
    }

    public TaskRunnable(int flag) {
        this.flag = flag;
    }

    /**
     * Getter method for property <tt>flag</tt>.
     *
     * @return property value of flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Setter method for property <tt>flag</tt>.
     *
     * @param flag value to be assigned to property flag
     */
    public void setFlag(int flag) {
        this.flag = flag;
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
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentThread.getName() + " 启动时间：" + currentTimeMillis / 1000);
        System.out.println(currentThread.getName() + " is working..." + flag);
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(currentThread.getName() + " finished working..." + flag);
    }
}
