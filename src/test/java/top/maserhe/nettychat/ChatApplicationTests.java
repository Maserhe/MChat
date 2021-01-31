package top.maserhe;

import top.maserhe.service.UserService;
import top.maserhe.service.impl.ChatMsgServiceImpl;
import top.maserhe.utils.LogUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ChatApplicationTests {

    @Resource
    UserService userService;

    @Test
    void testBatchSignedMsg(){
        LogUtil.info("好友的信息:[{}]", userService.queryUsersByUsername("mon"));
    }
}
