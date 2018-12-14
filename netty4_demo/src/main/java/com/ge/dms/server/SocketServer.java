package com.ge.dms.server;

import com.ge.dms.handler.SocketServerMsgInboundHandler;
import com.ge.dms.handler.SocketServerMsgOutboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

public class SocketServer {

    private static final Logger log = Logger.getLogger(SocketServer.class);

    private void start() throws Exception {
        final SocketServerMsgInboundHandler messageHandler = new SocketServerMsgInboundHandler();
        final SocketServerMsgOutboundHandler outboundHandler = new SocketServerMsgOutboundHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(34567))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                       public void initChannel(SocketChannel ch) throws Exception {
                           log.info("Thread " + Thread.currentThread().getId() + " create new SocketChannel");
                           ch.pipeline().addLast(messageHandler);
                           ch.pipeline().addLast(outboundHandler);
                       }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        try {
            server.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
