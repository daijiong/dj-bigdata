package org.djflying.bigdata.corejava.netty.sendobject.bean;

import java.io.Serializable;

/**
 * Person实体
 *
 * @author dj4817
 * @version $Id: Person.java, v 0.1 2018/3/28 11:17 dj4817 Exp $$
 */
public class Person implements Serializable {

    private String name;
    private String password;

    public Person() {
    }

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
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
     * Getter method for property <tt>password</tt>.
     *
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     *
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", password='" + password + '\'' + '}';
    }
}
