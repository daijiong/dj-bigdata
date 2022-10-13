package org.djflying.bigdata.corejava.jvm.oom;

/**
 * 线程导致内存溢出异常
 * VM Args：-Xss2M（这时候不妨设置大些）
 * 容易导致系统假死
 *
 * @author dj4817
 * @version $Id: JavaVMStackOOM.java, v 0.1 2017/12/13 15:38 dj4817 Exp $$
 */
public class JavaVMStackOOM {

    /**
     * 死循环
     */
    private void dontStop() {

        while (true) {
        }
    }

    /**
     * 栈溢出方法
     */
    public void stackLeakByThread() {

        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    /**
     * 主程序
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {

        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
