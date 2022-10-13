package org.djflying.bigdata.zookeeper;

import org.apache.zookeeper.*;

/**
 * 分布式程序服务端
 *
 * @author dj4817
 * @version $Id: DistributedServer.java, v 0.1 2017/12/15 21:46 dj4817 Exp $$
 */
public class DistributedServer {

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
                try {
                    zooKeeper.getChildren("/", true);
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * 向zookeeper中注册服务器信息
     * 
     * @param hostName
     * @throws Exception
     */
    public void registerServer(String hostName) throws Exception {

        if (zooKeeper.exists(parentNode, false) == null) {
            zooKeeper.create(parentNode, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        }
        String create = zooKeeper.create(parentNode + "/server", hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostName + " is online.." + create);
    }

    /**
     * 执行业务
     *
     * @throws InterruptedException
     */
    public void handleBussiness() throws InterruptedException {
        System.out.println("server start working.....");
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // 1.创建zookeeper连接
        DistributedServer server = new DistributedServer();
        server.getConnect();
        // 2.向zookeeper中注册服务器信息
        server.registerServer("centos0");
        // 3.执行业务
        server.handleBussiness();
    }
}
