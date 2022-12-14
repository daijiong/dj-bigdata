package org.djflying.bigdata.zookeeper.curator.recipes;

import java.io.IOException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用CyclicBarrier模拟一个赛跑比赛
 *
 * @author dj4817
 * @version $Id: ZookeeperWatcher.java, v 0.1 2018/3/12 13:44 dj4817 Exp $$
 */
public class RecipesCyclicBarrierSample {

    public static CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Thread(new Runner("1号选手")));
        executor.submit(new Thread(new Runner("2号选手")));
        executor.submit(new Thread(new Runner("3号选手")));
        executor.shutdown();
    }
}

class Runner implements Runnable {

    private String name;

    public Runner(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " 准备好了.");
        try {
            RecipesCyclicBarrierSample.barrier.await();
        } catch (Exception e) {
        }
        System.out.println(name + " 起跑!");
    }
}