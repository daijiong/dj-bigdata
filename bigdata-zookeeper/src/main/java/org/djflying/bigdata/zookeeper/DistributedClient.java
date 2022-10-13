package org.djflying.bigdata.zookeeper;

import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * 分布式程序客户端
 *
 * @author dj4817
 * @version $Id: DistributedClient.java, v 0.1 2017/12/15 21:46 dj4817 Exp $$
 */
public class DistributedClient {


    private static final String parentNode     = "/servers";

    private ZooKeeper           zooKeeper;

    /**
     * 创建zookeeper连接
     *
     * @throws Exception
     */
    public void getConnect() throws Exception {

        zooKeeper = new ZooKeeper(Constants.connectString, Constants.sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数（应该是我们自己的事件处理逻辑）
                System.out.println(event.getType() + "---" + event.getPath());
                if (Event.EventType.None != event.getType()) {
                    try {
                        // 获取服务器信息
                        getServersInfo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 获取服务器信息
     */
    public void getServersInfo() throws Exception {

        List<String> childrens = zooKeeper.getChildren(parentNode, true);
        System.out.println("服务器信息：");
        for (String children : childrens) {
            byte[] data = zooKeeper.getData(parentNode + "/" + children, false, null);
            System.out.println("主机名：" + new String(data));
        }
    }

    /**
     * 执行业务
     *
     * @throws InterruptedException
     */
    public void handleBussiness() throws InterruptedException {

        System.out.println("client start working.....");
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // 1.创建zookeeper连接
        DistributedClient client = new DistributedClient();
        client.getConnect();
        // 2.获取服务器信息
        client.getServersInfo();
        // 3.处理业务
        client.handleBussiness();
    }
}
