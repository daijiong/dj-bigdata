package org.djflying.bigdata.rpc.sample.server.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动spring容器
 *
 * @author dj4817
 * @version $Id: Bootstrap.java, v 0.1 2018/3/27 14:20 dj4817 Exp $$
 */
public class Bootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    /**
     * 启动spring容器
     *
     * @param args
     */
    public static void main(String[] args) {

        LOGGER.info("启动spring容器");
        new ClassPathXmlApplicationContext("META-INF/spring.xml");
    }
}
