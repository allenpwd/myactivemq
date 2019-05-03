package pwd.allen.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.allen.springboot.service.MQService;

/**
 * @author 门那粒沙
 * @create 2019-05-03 23:03
 **/
@RestController
@RequestMapping("/mq")
public class MyController {

    @Autowired
    MQService mqService;

    @RequestMapping("test")
    public Object test() {

        try {
            //为了提高用户体验，不怎么重要的、花费时间较长的逻辑可以抽出来给消息中间件异步去执行
            mqService.send("测试spring boot整合activemq");
        } catch (Exception e) {
        }
        return "";
    }
}
