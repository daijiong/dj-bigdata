package org.djflying.bigdata.rpc.registry.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务发现
 *
 * @author daijiong
 * @version $Id: ServiceDiscovery.java, v 0.1 18-10-16 下午4:13 daijiong Exp $$
 */
public class ServiceDiscovery {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDiscovery.class);

    /** zookeeper address */
    private String              registryAddress;

    public ServiceDiscovery() {
    }

    public ServiceDiscovery(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    /**
     * 发现服务
     *
     * @return
     */
    public String discover() {
        //TODO
        return "";
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }
}
