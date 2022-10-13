package org.djflying.bigdata.hadoop.rpc.protocol;

/**
 * 客户端访问NameNode的协议
 *
 * @author daijiong
 * @version $Id: ClientNamenodeProtocol.java, v 0.1 18-8-6 下午5:33 daijiong Exp $$
 */
public interface ClientNamenodeProtocol {

    public static final long versionID = 1L;

    /**
     * 客户端从NameNode获取文件的metaData信息
     *
     * @param path
     * @return
     */
    String getMetaData(String path);
}
