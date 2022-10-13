package org.djflying.bigdata.corejava.spring.userdefinedannotation.service.impl;

import org.djflying.bigdata.corejava.spring.userdefinedannotation.service.HelloService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * HelloServiceImpl Tester. 
 *
 * @author dj4817
 * @version $Id: $testClass.java, v 0.1 12/10/2017 dj4817 Exp $$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/spring2.xml")
public class HelloServiceImplTest {

    @Autowired
    private HelloService helloService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /** 
    * 
    * Method: hello(String name) 
    * 
    */
    @Test
    public void testHello() throws Exception {

        String hello = helloService.hello("david");
        System.out.println(hello);
    }

}
