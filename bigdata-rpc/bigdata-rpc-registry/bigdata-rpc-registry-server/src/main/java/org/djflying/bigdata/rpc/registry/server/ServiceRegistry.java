package org.djflying.bigdata.rpc.registry.server;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zookeeper服务注册器
 *
 * @author dj4817
 * @version $Id: ServiceRegistry.java, v 0.1 2018/3/27 14:44 dj4817 Exp $$
 */
public class ServiceRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistry.class);

    /** zookeeper服务器地址 */
    private String              registryAddress;
    /** zookeeper客户端 */
    private CuratorFramework    client;

    public ServiceRegistry(String registryAddress) {

        this.registryAddress = registryAddress;
        // 连接zookeeper服务器
        client = CuratorFrameworkFactory.builder().connectString(registryAddress).sessionTimeoutMs(Constant.sessionTimeout)
            .retryPolicy(new ExponentialBackoffRetry(Constant.BASESLEEPTIMEMS, Constant.MAXRETRIES)).namespace("registry").build();
        client.start();
    }

    /**
     * zookeeper服务注册
     *
     * @param address
     */
    public void register(String address) throws Exception {

        LOGGER.info("zookeeper服务注册");
        if (StringUtils.isNotBlank(address)) {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/data", address.getBytes());
        }
    }

    /**
     * Getter method for property <tt>registryAddress</tt>.
     *
     * @return property value of registryAddress
     */
    public String getRegistryAddress() {
        return registryAddress;
    }

    /**
     * Setter method for property <tt>registryAddress</tt>.
     *
     * @param registryAddress value to be assigned to property registryAddress
     */
    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }
}
