package org.djflying.bigdata.corejava.threadpool;

import java.util.concurrent.*;

/**
 * 通过线程池执行线程（Callable方式）
 *
 * Callable方式跟Runnable方式的区别：
 * 1. Runnable的run方法不会有任何返回结果，所以主线程无法获得任务线程的返回值。
 * 2. Callable的call方法可以返回结果，但是主线程在获取时是被阻塞，需要等待任务线程返回结果之后才能拿到结果。
 *
 * @author dj4817
 * @version $Id: ThreadPoolWithCallableTest.java, v 0.1 2017/11/29 12:42 dj4817 Exp $$
 */
public class ThreadPoolWithCallableTest implements Callable<String> {

    private int flag;

    public ThreadPoolWithCallableTest() {
    }

    public ThreadPoolWithCallableTest(int flag) {
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
        System.out.println(currentThread.getName() + "正在执行" + this.getFlag() + "任务");
        Thread.sleep(5000);
        return "当前线程名称：" + currentThread.getName();
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
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolWithCallableTest test = new ThreadPoolWithCallableTest();
        int cpuNums = Runtime.getRuntime().availableProcessors();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(cpuNums);
        for (int i = 0; i < 5; i++) {
            test.setFlag(i);
            Future<String> future = fixedThreadPool.submit(test);
            // 从Future中get结果，这个方法是会被阻塞的，一直要等到线程任务返回结果
            String result = future.get();
            System.out.println(result);
        }
        fixedThreadPool.shutdown();
    }
}
