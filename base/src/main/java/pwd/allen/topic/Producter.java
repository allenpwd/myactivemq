package pwd.allen.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 门那粒沙
 * @create 2019-05-03 20:25
 **/
public class Producter {

    public static String TOPIC_NAME = "my-topic";

    public static void main(String[] args) throws JMSException {
        //ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER
                ,ActiveMQConnectionFactory.DEFAULT_PASSWORD
                ,"tcp://127.0.0.1:61616");

        //Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = connectionFactory.createConnection();

        //Connection 启动
        connection.start();

        //Session： 一个发送或接收消息的线程
        //开启事务模式，签收模式
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);

        //MessageProducer：消息发送者
        MessageProducer producer = session.createProducer(null);

        //不设置持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        //
        sendMessage(session, producer, "topic 发布订阅!");
        session.commit();
        connection.close();
    }

    public static void sendMessage(Session session, MessageProducer messageProducer, String msg) throws JMSException {
        //创建一条文本消息
        TextMessage textMessage = session.createTextMessage("Hello ActiveM@ MSG = " + msg);

        Topic topic = session.createTopic(TOPIC_NAME);
        //通过消息生产者发出消息
        messageProducer.send(topic, textMessage);
    }
}
