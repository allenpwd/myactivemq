package pwd.allen.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

/**
 * @author 门那粒沙
 * @create 2019-05-03 23:03
 **/
@Service
public class MQService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void send(String message) {
        jmsMessagingTemplate.convertAndSend(queue, "发送消息：" + message);
    }
}
