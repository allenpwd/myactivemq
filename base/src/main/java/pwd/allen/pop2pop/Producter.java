package pwd.allen.pop2pop;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 门那粒沙
 * @create 2019-05-03 20:25
 **/
public class Producter {

    public static String QUEUE_NAME = "my-queue";

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
        //参数1：是否开启事务模式，为true的话需要session.commit()操作才会生效
        //参数2：设置签收模式，有以下几种
        //AUTO_ACKNOWLEDGE自动签收
        //CLIENT_ACKNOWLEDGE手动签收：需手动调用Message.acknowledge()方法签收
        //SESSION_TRANSACTED：需调用session.commit()确认签收，必须开启事务模式
        Session session = connection.createSession(Boolean.TRUE,Session.CLIENT_ACKNOWLEDGE);

        //Queue ：消息的目的地;消息发送给谁.
        Destination destination = session.createQueue(QUEUE_NAME);

        //MessageProducer：消息发送者
        MessageProducer producer = session.createProducer(destination);

        //不设置持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        sendMessage(session, producer, "i am pwd!");
        session.commit();
        session.close();
        connection.close();
    }

    public static void sendMessage(Session session, MessageProducer messageProducer, String msg) throws JMSException {
        //创建一条文本消息
        TextMessage textMessage = session.createTextMessage("Hello ActiveM@ MSG = " + msg);
        //通过消息生产者发出消息
        messageProducer.send(textMessage);
    }
}
