package org.djflying.bigdata.hadoop.rpc.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.djflying.bigdata.hadoop.rpc.protocol.ClientNamenodeProtocol;

/**
 * 服务启动帮助类
 *
 * @author daijiong
 * @version $Id: StartServiceUtil.java, v 0.1 18-8-6 下午5:41 daijiong Exp $$
 */
public class StartServiceUtil {

    /**
     * 启动服务
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("localhost").setPort(8888).setProtocol(ClientNamenodeProtocol.class).setInstance(new MyNamenode());
        RPC.Server server = builder.build();
        server.start();
    }
}
