package org.djflying.bigdata.corejava.proxy.action;

import org.djflying.bigdata.corejava.proxy.service.IBoss;
import org.djflying.bigdata.corejava.proxy.service.impl.Boss;

/**
 * 销售业务
 *
 * @author dj4817
 * @version $Id: SaleAction.java, v 0.1 2017/12/3 9:36 dj4817 Exp $$
 */
public class SaleAction {

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {

        IBoss boss = new Boss();
        System.out.println("老板自营！");
        // 老板自己卖衣服，不需要客服，结果就是没有聊天记录
        int money = boss.yifu("xxl");
        System.out.println("衣服成交价：" + money);
    }
}
