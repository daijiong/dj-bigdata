package org.djflying.bigdata.corejava.reflection;

import java.io.Serializable;

/**
 * 测试类
 *
 * @author dj4817
 * @version $Id: Person.java, v 0.1 2017/12/1 13:06 dj4817 Exp $$
 */
public class Person implements Serializable, TestInterface {

    private String name;
    public String address;

    /**
     * 私有构造器
     *
     * @param name
     */
    private Person(String name) {
        this.name = name;
    }

    /**
     * 无参构造器
     */
    public Person() {
    }

    /**
     * 全参构造器
     *
     * @param name
     * @param address
     */
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * 私有方法
     */
    private void testPrivate() {
        System.out.println("this is a private method");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /**
     * 接口方法
     *
     * @param s
     * @return
     */
    @Override
    public String say(String s) {
        return s;
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
     * Getter method for property <tt>address</tt>.
     *
     * @return property value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for property <tt>address</tt>.
     *
     * @param address value to be assigned to property address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
