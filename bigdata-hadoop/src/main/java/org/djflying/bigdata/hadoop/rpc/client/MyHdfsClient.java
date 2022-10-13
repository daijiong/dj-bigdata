package org.djflying.bigdata.hadoop.rpc.client;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.djflying.bigdata.hadoop.rpc.protocol.ClientNamenodeProtocol;

/**
 * HDFS客户端
 *
 * @author daijiong
 * @version $Id: MyHdfsClient.java, v 0.1 18-8-6 下午5:47 daijiong Exp $$
 */
public class MyHdfsClient {

    public static final long versionID = 1L;

    public static void main(String[] args) throws Exception {
        ClientNamenodeProtocol namenodeProtocol = RPC.getProxy(ClientNamenodeProtocol.class, versionID, new InetSocketAddress("localhost", 8888), new Configuration());
        String metaData = namenodeProtocol.getMetaData("/test.txt");
        System.out.println(metaData);
    }
}
