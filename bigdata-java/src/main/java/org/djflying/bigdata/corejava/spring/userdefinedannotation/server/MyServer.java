package org.djflying.bigdata.corejava.spring.userdefinedannotation.server;

import java.lang.reflect.Method;
import java.util.Map;

import org.djflying.bigdata.corejava.spring.userdefinedannotation.annotation.RpcService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 测试自定义注解获取
 *
 * @author dj4817
 * @version $Id: MyServer.java, v 0.1 2017/12/10 20:37 dj4817 Exp $$
 */
@Component
public class MyServer implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        // 从spring上下文中获取RpcService注解的bean
        Map<String, Object> serviceBeans = applicationContext.getBeansWithAnnotation(RpcService.class);
        for (Object serviceBean : serviceBeans.values()) {
            try {
                // 获取自定义注解上的value
                String value = serviceBean.getClass().getAnnotation(RpcService.class).value();
                System.out.println("注解上的value: " + value);

                // 反射被注解类，并调用指定方法
                Method method = serviceBean.getClass().getMethod("hello", String.class);
                Object invoke = method.invoke(serviceBean, "david");
                System.out.println(invoke);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
