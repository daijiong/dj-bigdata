package org.djflying.bigdata.rpc.registry.server;

/**
 * 连接Zookeeper服务器常量
 *
 * @author dj4817
 * @version $Id: Constant.java, v 0.1 2018/3/27 15:54 dj4817 Exp $$
 */
public class Constant {

    /** 会话的超时时间，单位ms */
    public static final int sessionTimeout    = 2000;
    /** 连接创建超时时间，单位ms */
    public static final int connectionTimeout = 5000;
    /** 初始sleep时间 */
    public static final int BASESLEEPTIMEMS   = 1000;
    /** 最大重试次数 */
    public static final int MAXRETRIES        = 3;
}
