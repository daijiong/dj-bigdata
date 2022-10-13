package org.djflying.bigdata.corejava.spring.userdefinedannotation.service.impl;

import org.djflying.bigdata.corejava.spring.userdefinedannotation.annotation.RpcService;
import org.djflying.bigdata.corejava.spring.userdefinedannotation.service.HelloService;

/**
 * 业务实现类
 *
 * @author dj4817
 * @version $Id: HelloServiceImpl.java, v 0.1 2017/12/10 20:34 dj4817 Exp $$
 */
@RpcService("helloService")
public class HelloServiceImpl implements HelloService {

    /**
     * hello方法
     *
     * @param name
     * @return
     */
    @Override
    public String hello(String name) {

        return "hello:" + name;
    }
}
