package org.djflying.bigdata.corejava.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue的生产者
 *
 * @author dj4817
 * @version $Id: BlockingQueueProducer.java, v 0.1 2017/11/30 11:05 dj4817 Exp $$
 */
public class BlockingQueueProducer implements Runnable {

    BlockingQueue<String> queue;

    /**
     * 无参构造器
     */
    public BlockingQueueProducer() {
    }

    /**
     * 全参构造器
     *
     * @param queue
     */
    public BlockingQueueProducer(BlockingQueue<String> queue) {
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
        System.out.println("生产者线程：" + currentThread.getName() + "开始生产");
        String temp = currentThread.getName();
        try {
            Thread.sleep(3 * 1000);
            // 如果队列是满的话，会阻塞当前线程
            queue.put(temp);
            System.out.println("生产者线程：" + currentThread.getName() + "放入队列");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
