package org.djflying.bigdata.hadoop.rpc.service;

import org.djflying.bigdata.hadoop.rpc.protocol.ClientNamenodeProtocol;

/**
 * NameNode服务器
 *
 * @author daijiong
 * @version $Id: MyNamenode.java, v 0.1 18-8-6 下午5:37 daijiong Exp $$
 */
public class MyNamenode implements ClientNamenodeProtocol {


    /**
     * 客户端从NameNode获取文件的metaData信息
     *
     * @param path
     * @return
     */
    @Override
    public String getMetaData(String path) {

        return path + ": 3 - {BLK_1,BLK_2} ....";
    }
}
