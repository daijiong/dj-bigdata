package org.djflying.bigdata.corejava.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue的消费者
 *
 * @author dj4817
 * @version $Id: BlockingQueueConsumer.java, v 0.1 2017/11/30 11:23 dj4817 Exp $$
 */
public class BlockingQueueConsumer implements Runnable {

    BlockingQueue<String> queue;

    /**
     * 无参构造器
     */
    public BlockingQueueConsumer() {
    }

    /**
     * 全参构造器
     *
     * @param queue
     */
    public BlockingQueueConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
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
        System.out.println("消费者线程：" + currentThread.getName() + "开始消费");
        String temp = null;
        try {
            Thread.sleep(5 * 1000);
            // 如果队列为空，会阻塞当前线程
            temp = queue.take();
            System.out.println("消费者线程：" + currentThread.getName() + " get a product:" + temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
