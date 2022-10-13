package org.djflying.bigdata.corejava.spring.springrunorder;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

/**
 * spring容器初始化顺序启动类
 *
 * @author dj4817
 * @version $Id: Start.java, v 0.1 2017/12/10 19:28 dj4817 Exp $$
 */
public class Start {

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {

        new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/spring.xml");
    }
}
