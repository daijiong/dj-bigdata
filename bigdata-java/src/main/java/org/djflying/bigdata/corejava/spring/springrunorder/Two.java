package org.djflying.bigdata.corejava.spring.springrunorder;

/**
 * spring容器对象
 *
 * @author dj4817
 * @version $Id: Two.java, v 0.1 2017/12/10 19:19 dj4817 Exp $$
 */
public class Two {

    /**
     * 无参构造器
     */
    public Two() {

        System.out.println("constructor of Two");
    }

    /**
     * 带参构造器
     * 
     * @param param
     */
    public Two(String param) {

        System.out.println("constructor of Two with " + param);
    }
}
