package org.djflying.bigdata.corejava.proxy.service;

/**
 * 人类接口
 *
 * @author dj4817
 * @version $Id: IPerson.java, v 0.1 2017/12/3 9:59 dj4817 Exp $$
 */
public interface IPerson {

    /**
     * 吃东西
     *
     * @param something
     */
    void eat(String something);

    /**
     * 说话
     *
     * @param something
     * @return
     */
    String say(String something);


}
