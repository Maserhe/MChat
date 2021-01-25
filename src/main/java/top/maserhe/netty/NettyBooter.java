package top.maserhe.netty;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import top.maserhe.netty.WSServer;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-26 1:38
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            WSServer.getInstance().start();
            System.out.println("Maserhe的 netty已经启动了 --");
        }
    }
}
