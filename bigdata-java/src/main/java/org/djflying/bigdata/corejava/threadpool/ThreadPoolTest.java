package org.djflying.bigdata.corejava.threadpool;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 线程池使用实例
 *
 * @author dj4817
 * @version $Id: ThreadPoolTest.java, v 0.1 2017/11/29 13:24 dj4817 Exp $$
 */
public class ThreadPoolTest {

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        // 记录各线程的返回结果
        ArrayList<Future<?>> results = new ArrayList<>();
        int cpuNums = Runtime.getRuntime().availableProcessors();
        // 创建固定数量线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(cpuNums);
        // 创建调度线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(cpuNums);
        for (int i = 0; i < 9; i++) {
            // 情况1：FixedThreadPool线程池处理Runnable任务
            //Future<?> runnableSubmit = fixedThreadPool.submit(new TaskRunnable(i));
            //results.add(runnableSubmit);

            // 情况2：FixedThreadPool线程池处理Callable任务
            //Future<String> callableSubmit = fixedThreadPool.submit(new TaskCallable(i));
            //results.add(callableSubmit);

            // 情况3：ScheduledThreadPool线程池处理Callable任务
            //Future<String> callableSubmit = scheduledThreadPool.submit(new TaskCallable(i));
            //results.add(callableSubmit);
            // 任务提交之后，延迟5s执行。
            Future<String> callableSubmit = scheduledThreadPool.schedule(new TaskCallable(i), 5, TimeUnit.SECONDS);
            results.add(callableSubmit);
        }
        Thread.sleep(10 * 1000);
        // 打印执行结果
        System.out.println("开始逐个获取future结果：");
        int i = 1;
        for (Future f : results) {
            String done = f.isDone() ? "已完成" : "未完成";
            System.out.println("第" + i + "个线程" + done);
            // 从结果的打印顺序可以看到，即使未完成，也会阻塞等待
            System.out.println("第" + i + "个线程返回future结果： " + f.get());
            i++;
        }
        System.out.println("所有任务都执行完毕");
        // 阻止新来的任务提交，对已经提交了的任务不会产生任何影响。当已经提交的任务执行完后，它会将那些闲置的线程（idleWorks）进行中断。
        scheduledThreadPool.shutdown();
    }
}
