package org.djflying.bigdata.corejava.jvm.oom;

/**
 * 虚拟机栈和本地方法栈OOM测试
 * VM Args：-Xss128k
 *
 * @author dj4817
 * @version $Id: JavaVMStackSOF.java, v 0.1 2017/12/13 15:40 dj4817 Exp $$
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    /**
     * 栈溢出方法
     */
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * 主程序
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {

        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length：" + oom.stackLength);
            throw e;
        }
    }
}
