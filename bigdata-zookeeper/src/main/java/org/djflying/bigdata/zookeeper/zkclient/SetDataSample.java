package org.djflying.bigdata.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.djflying.bigdata.zookeeper.Constants;

/**
 * 使用ZkClient更新节点数据
 *
 * @author dj4817
 * @version $Id: ZookeeperWatcher.java, v 0.1 2018/3/12 13:44 dj4817 Exp $$
 */
public class SetDataSample {

    /**
     * 测试使用ZkClient更新节点数据
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String path = "/zk-book";
        ZkClient zkClient = new ZkClient(Constants.connectString, Constants.connectionTimeout);
        zkClient.createEphemeral(path, new Integer(1));
        zkClient.writeData(path, new Integer(1));
    }
}