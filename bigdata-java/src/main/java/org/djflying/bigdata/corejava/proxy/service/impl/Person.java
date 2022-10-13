package org.djflying.bigdata.corejava.proxy.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.djflying.bigdata.corejava.proxy.service.IPerson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 人类实现类
 *
 * @author dj4817
 * @version $Id: Person.java, v 0.1 2017/12/3 10:01 dj4817 Exp $$
 */
public class Person implements IPerson {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 吃东西
     *
     * @param something
     */
    @Override
    public void eat(String something) {

        System.out.println(getName() + "在吃：" + something);
    }

    /**
     * 说话
     *
     * @param something
     * @return
     */
    @Override
    public String say(String something) {

        System.out.println(getName() + "在说：" + something);
        return something;
    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {

        IPerson person = new Person("daijiong");
        IPerson personProxy = (IPerson) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("proxy is coming....");
                if (StringUtils.equalsIgnoreCase(method.getName(), "eat")) {
                    System.out.println("eat some thing is special handled.....");
                    person.eat("rice");
                } else {
                    method.invoke(person, args);
                    System.out.println("proxy is leaving....");

                }
                return null;
            }
        });
        person.eat("meat");
        person.say("hello");
        personProxy.eat("meat");
        personProxy.say("hello");

    }
}
