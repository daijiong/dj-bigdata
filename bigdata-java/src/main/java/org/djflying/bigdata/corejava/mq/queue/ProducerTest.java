package org.djflying.bigdata.corejava.mq.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * activemq生产者测试类（queue方式）
 *
 * @author dj4817
 * @version $Id: ProducerTest.java, v 0.1 2017/12/1 9:40 dj4817 Exp $$
 */
public class ProducerTest implements Runnable {


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
        MessageProducer producer = null;
        try {
            // 初始化连接
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 发送消息
            String message = "hello";
            TextMessage msg = session.createTextMessage(message);
            connection.start();
            System.out.println("Producer:->Sending message: " + message);
            producer.send(msg);
            System.out.println("Producer:->Message sent complete!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                System.out.println("Producer:->Closing connection");
                if (producer != null) {
                    producer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
