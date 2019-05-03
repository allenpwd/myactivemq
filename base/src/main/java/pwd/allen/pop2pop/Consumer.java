package pwd.allen.pop2pop;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 门那粒沙
 * @create 2019-05-03 21:02
 **/
public class Consumer {
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
        Session session = connection.createSession(Boolean.TRUE,Session.CLIENT_ACKNOWLEDGE);

        //Queue ：消息的目的地;消息发送给谁.
        Destination destination = session.createQueue(Producter.QUEUE_NAME);

        //MessageProducer：消息接受者
        MessageConsumer consumer = session.createConsumer(destination);

        try {
            while (true) {
                TextMessage textMessage = (TextMessage) consumer.receive();
                if (textMessage != null) {
                    System.out.println("收到消息：" + textMessage.getText());
                    textMessage.acknowledge();
                    session.commit();
                } else {
                    break;
                }
                System.out.println(textMessage);
            }
        } finally {
            session.close();
            connection.close();
        }
    }
}
