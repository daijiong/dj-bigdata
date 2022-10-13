package org.djflying.bigdata.corejava.proxy.service;

/**
 * 这是一个业务的接口，这个接口中的业务就是返回衣服的价格
 *
 * @author dj4817
 * @version $Id: IBoss.java, v 0.1 2017/12/3 9:32 dj4817 Exp $$
 */
public interface IBoss {

    /**
     * 衣服的价格
     *
     * @param size
     * @return
     */
    int yifu(String size);
}
