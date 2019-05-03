package pwd.allen.springboot.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author 门那粒沙
 * @create 2019-05-03 23:13
 **/
@Component
public class MQListener {

    @JmsListener(destination = "${queue}")
    public void receive(String msg) {
        System.out.println("监听器收到消息：" + msg);
    }
}
