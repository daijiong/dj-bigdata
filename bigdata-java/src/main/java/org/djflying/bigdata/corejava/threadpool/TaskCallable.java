package org.djflying.bigdata.corejava.threadpool;

import java.util.concurrent.Callable;

/**
 * 实现Callable接口的任务
 *
 * @author dj4817
 * @version $Id: TaskCallable.java, v 0.1 2017/11/29 13:29 dj4817 Exp $$
 */
public class TaskCallable implements Callable<String> {

    private int flag;

    public TaskCallable() {
    }

    public TaskCallable(int flag) {
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
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {

        Thread currentThread = Thread.currentThread();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentThread.getName() + " 启动时间：" + currentTimeMillis / 1000);
        System.out.println(currentThread.getName() + " is working..." + getFlag());
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(currentThread.getName() + " finished working..." + getFlag());
        return getFlag() + "";
    }
}
