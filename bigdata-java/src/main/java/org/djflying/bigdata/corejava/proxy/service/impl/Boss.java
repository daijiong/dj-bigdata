package org.djflying.bigdata.corejava.proxy.service.impl;

import org.djflying.bigdata.corejava.proxy.service.IBoss;

/**
 * 衣服销售业务实现类
 *
 * @author dj4817
 * @version $Id: Boss.java, v 0.1 2017/12/3 9:34 dj4817 Exp $$
 */
public class Boss implements IBoss {

    /**
     * 衣服的价格
     *
     * @param size
     * @return
     */
    @Override
    public int yifu(String size) {

        System.out.println("天猫小强旗舰店，老板给客户发快递----衣服型号：" + size);
        // 这件衣服的价钱，从数据库读取
        return 50;
    }
}
