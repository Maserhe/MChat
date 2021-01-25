package top.maserhe.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2020-11-26 1:26
 */
@Component
public class WSServer {
    // 单例
    private static class SingletonWSServer {
        static final WSServer instance = new WSServer();

    }

    public static WSServer getInstance() {
        return SingletonWSServer.instance;
    }


    // 主从线程
    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WSServer() {

        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }

    // 开启 netty
    public void start() {
        server.bind(8088);
    }

    // 关闭 netty
    public void stop() {

    }
}
