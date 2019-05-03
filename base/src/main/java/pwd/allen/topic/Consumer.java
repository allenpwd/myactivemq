package pwd.allen.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 历史发布的订阅消息是接受不到的
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
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);

        //Queue ：消息的目的地;消息发送给谁.
        Topic topic = session.createTopic(Producter.TOPIC_NAME);

        //MessageProducer：消息接受者
        MessageConsumer consumer = session.createConsumer(topic);

        while (true) {
            TextMessage textMessage = (TextMessage) consumer.receive();
            if (textMessage != null) {
                System.out.println("收到订阅消息：" + textMessage.getText());
                session.commit();
            } else {
                break;
            }
            System.out.println(textMessage);
        }
        session.close();
        connection.close();
    }
}
