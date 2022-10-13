package org.djflying.bigdata.corejava.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue测试类
 *
 * @author dj4817
 * @version $Id: BlockingQueueTest.java, v 0.1 2017/11/30 11:31 dj4817 Exp $$
 */
public class BlockingQueueTest {

    /**
     * 主线程
     *
     * @param args
     */
    public static void main(String[] args) {

        // 创建ArrayBlockingQueue
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        // 创建LinkedBlockingQueue
        //BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);
        BlockingQueueProducer producer = new BlockingQueueProducer(queue);
        BlockingQueueConsumer consumer = new BlockingQueueConsumer(queue);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(producer, "product" + (i + 1));
            thread.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(consumer, "consumer" + (i + 1));
            thread.start();
        }
    }
}
