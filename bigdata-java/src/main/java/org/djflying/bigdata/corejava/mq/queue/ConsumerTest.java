package org.djflying.bigdata.corejava.mq.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * activemq消费者测试类（queue方式）
 *
 * @author dj4817
 * @version $Id: ConsumerTest.java, v 0.1 2017/12/1 10:20 dj4817 Exp $$
 */
public class ConsumerTest implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {

        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
        try {
            // 初始化连接
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            consumer = session.createConsumer(destination);
            // 接收消息
            connection.start();
            //consumer.setMessageListener(this);
            //connection.setExceptionListener();
            System.out.println("Consumer:->Begin listening...");
            Message message = consumer.receive();
            System.out.println(message.getJMSMessageID());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
