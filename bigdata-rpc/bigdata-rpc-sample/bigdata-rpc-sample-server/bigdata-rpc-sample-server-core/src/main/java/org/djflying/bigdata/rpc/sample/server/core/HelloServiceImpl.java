package org.djflying.bigdata.rpc.sample.server.core;

import org.djflying.bigdata.rpc.sample.server.spi.HelloService;
import org.djflying.bigdata.rpc.sample.server.spi.beans.Person;
import org.djflying.bigdata.rpc.server.RpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务接口实现类
 *
 * @author dj4817
 * @version $Id: HelloServiceImpl.java, v 0.1 2018/3/27 14:11 dj4817 Exp $$
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceImpl.class);

    /**
     * 业务接口方法hello
     *
     * @param name
     * @return
     */
    @Override
    public String hello(String name) {
        return null;
    }

    /**
     * 业务接口方法hello
     *
     * @param person
     * @return
     */
    @Override
    public String hello(Person person) {
        return null;
    }
}
