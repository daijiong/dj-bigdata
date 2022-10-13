package org.djflying.bigdata.corejava.spring.userdefinedannotation;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

/**
 * Description
 *
 * @author dj4817
 * @version $Id: Start.java, v 0.1 2017/12/10 20:37 dj4817 Exp $$
 */
public class Start {

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {

        new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/spring2.xml");
    }
}
