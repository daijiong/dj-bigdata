package org.djflying.bigdata.zookeeper.javaclient;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.djflying.bigdata.zookeeper.Constants;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Zookeeper的java客户端api测试
 *
 * @author dj4817
 * @version $Id: JavaClientTest.java, v 0.1 2017/12/14 17:45 dj4817 Exp $$
 */
public class JavaClientTest {

    private ZooKeeper           zooKeeper;

    @Before
    public void setUp() throws Exception {

        zooKeeper = new ZooKeeper(Constants.connectString, Constants.sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数（应该是我们自己的事件处理逻辑）
                System.out.println(event.getType() + "---" + event.getPath());
                // 继续监听根节点下的子节点变化事件
                try {
                    zooKeeper.getChildren("/", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * create方法测试
     *
     * create方法参数讲解：
     * 1. path：要创建的节点的路径
     * 2. data：节点的数据，注意是byte类型
     * 3. acl：节点的权限，Ids枚举
     * 4. createMode：节点的类型，CreateMode枚举
     *
     * create方法返回值：节点的路径
     *
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {

        String nodeCreated = zooKeeper.create("/zktest", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Assert.assertEquals("/zktest", nodeCreated);
    }

    /**
     * exists方法测试
     *
     * exists方法参数讲解：
     * 1. path：节点路径
     * 2. watch：是否监听，如果监听的话，当这个节点被删除的时候回收到回调通知
     *
     * exists方法返回值：节点
     *
     * @throws Exception
     */
    @Test
    public void testExists() throws Exception {

        Stat stat = zooKeeper.exists("/", false);
        Assert.assertNotNull(stat);
    }

    /**
     * getChildren方法测试
     *
     * getChildren方法参数讲解：
     * 1. patch：要获取子节点的列表的节点路径
     * 2. watch：是否监听这个节点
     *
     * getChildren方法返回值：所有子节点列表
     *
     * @throws Exception
     */
    @Test
    public void testGetChildren() throws Exception {

        List<String> childrens = zooKeeper.getChildren("/", false);
        for (String children : childrens) {
            System.out.println(children);
        }
    }

    /**
     * getData方法测试
     *
     * getDate方法参数讲解：
     * 1. path：要查看的节点路径
     * 2. watch：是否要监听这个节点
     * 3. stat：描述节点版本
     *
     * getData方法返回值：节点数据，byte数组
     *
     * @throws Exception
     */
    @Test
    public void testGetData() throws Exception {

        byte[] data = zooKeeper.getData("/zktest", false, null);
        Assert.assertEquals("hello", new String(data));
    }

    /**
     * setData方法测试
     *
     * setData方法参数讲解：
     * 1. path：要修改数据的节点路径
     * 2. data：数据
     * 3. version：版本
     *
     * setData方法返回值：节点
     *
     * @throws Exception
     */
    @Test
    public void testSetData() throws Exception {

        Stat stat = zooKeeper.setData("/", "helloworld".getBytes(), -1);
        byte[] bytes = zooKeeper.getData("/", false, null);
        Assert.assertEquals("helloworld", new String(bytes));
    }

    /**
     * delete方法测试
     *
     * delete方法参数详解：
     * 1. path：要删除的节点路径
     * 2. version：要删除的节点的版本，-1表示删除所有版本
     *
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {

        zooKeeper.delete("/zktest", -1);
    }
}
