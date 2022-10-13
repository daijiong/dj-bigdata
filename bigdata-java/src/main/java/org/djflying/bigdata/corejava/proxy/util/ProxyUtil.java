package org.djflying.bigdata.corejava.proxy.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理获取
 *
 * @author dj4817
 * @version $Id: ProxyUtil.java, v 0.1 2017/12/3 9:39 dj4817 Exp $$
 */
public class ProxyUtil {

    /**
     * 获取Boss的代理对象
     *
     * @param discountCoupon 优惠金额
     * @param implementClass 被代理类
     * @param <T>
     * @return
     */
    public static <T> T getProxyBoss(int discountCoupon, Class<?> implementClass) {

        // 被代理类的类加载器
        ClassLoader classLoader = implementClass.getClassLoader();
        // 被代理类的所有实现接口
        Class<?>[] interfaces = implementClass.getInterfaces();
        Object object = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Proxy invoke begin");
                System.out.println("proxy: " + proxy.getClass().getName());
                System.out.println("method: " + method.getName());
                for (Object o : args) {
                    System.out.println("arg: "+ o);
                }
                // 被代理类的实例
                Object implement = implementClass.newInstance();
                Integer result = (Integer) method.invoke(implement, args);
                System.out.println("Proxy invoke end");
                return result - discountCoupon;
            }
        });
        return (T) object;
    }
}