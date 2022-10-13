package org.djflying.bigdata.rpc.sample.server.spi;

import org.djflying.bigdata.rpc.sample.server.spi.beans.Person;

/**
 * 业务接口
 *
 * @author dj4817
 * @version $Id: HelloService.java, v 0.1 2018/3/27 14:05 dj4817 Exp $$
 */
public interface HelloService {

    /**
     * 业务接口方法hello
     *
     * @param name
     * @return
     */
    String hello(String name);

    /**
     * 业务接口方法hello
     *
     * @param person
     * @return
     */
    String hello(Person person);
}
