package org.djflying.bigdata.corejava.spring.springrunorder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring容器对象
 *
 * @author dj4817
 * @version $Id: One.java, v 0.1 2017/12/10 19:15 dj4817 Exp $$
 */
public class One implements ApplicationContextAware, InitializingBean {

    /**
     * 无参构造器
     */
    public One() {

        System.out.println("constructor of One");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("setApplicationContext");
    }
}
