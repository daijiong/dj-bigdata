package org.djflying.bigdata.corejava.spring.springrunorder;

import org.springframework.stereotype.Component;

/**
 * spring容器对象
 *
 * @author dj4817
 * @version $Id: Three.java, v 0.1 2017/12/10 19:20 dj4817 Exp $$
 */
@Component
public class Three {

    /**
     * 无参构造器
     */
    public Three() {

        System.out.println("constructor of Three");
    }

    /**
     * 带参构造器
     * 
     * @param param
     */
    public Three(String param) {

        System.out.println("constructor of Three with " + param);
    }
}
