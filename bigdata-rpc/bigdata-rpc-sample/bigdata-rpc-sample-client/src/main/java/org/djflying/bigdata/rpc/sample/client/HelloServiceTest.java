//package org.djflying.bigdata.rpc.sample.client;
//
//import org.djflying.bigdata.rpc.client.RpcProxy;
//import org.djflying.bigdata.rpc.registry.client.ServiceDiscovery;
//import org.djflying.bigdata.rpc.sample.server.spi.HelloService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * 客户端业务类
// *
// * @author daijiong
// * @version $Id: HelloServiceTest.java, v 0.1 18-10-16 下午4:05 daijiong Exp $$
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:META-INF/spring.xml")
//public class HelloServiceTest {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceTest.class);
//
//    @Autowired
//    private RpcProxy            rpcProxy;
//
//    @Test
//    public void helloTest() throws Exception {
//
//        HelloService helloService = rpcProxy.create(HelloService.class);
//
//    }
//}
