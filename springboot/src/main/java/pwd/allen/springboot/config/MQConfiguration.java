package pwd.allen.springboot.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @author 门那粒沙
 * @create 2019-05-03 23:04
 **/
@Configuration
public class MQConfiguration {
    @Value("${queue}")
    private String queueName;

    @Bean
    public Queue myQueue() {
        return new ActiveMQQueue(queueName);
    }
}
