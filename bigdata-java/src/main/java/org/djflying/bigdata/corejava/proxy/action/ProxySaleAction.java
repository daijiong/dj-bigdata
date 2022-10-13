package org.djflying.bigdata.corejava.proxy.action;

import org.djflying.bigdata.corejava.proxy.service.IBoss;
import org.djflying.bigdata.corejava.proxy.service.impl.Boss;
import org.djflying.bigdata.corejava.proxy.util.ProxyUtil;

/**
 * 代理销售业务
 *
 * @author dj4817
 * @version $Id: ProxySaleAction.java, v 0.1 2017/12/3 9:38 dj4817 Exp $$
 */
public class ProxySaleAction {

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {

        // 将代理的方法实例化成接口
        IBoss boss = ProxyUtil.getProxyBoss(10, Boss.class);
        System.out.println("代理经营！");
        // 调用接口的方法，实际上调用方式没有变
        int money = boss.yifu("xxl");
        System.out.println("衣服成交价：" + money);
    }
}
