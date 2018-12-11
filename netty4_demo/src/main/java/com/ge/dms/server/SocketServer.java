package com.ge.dms.server;

import com.ge.dms.handler.SocketMessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class SocketServer {

    private void start() throws Exception {
        final SocketMessageHandler messageHandler = new SocketMessageHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(34567))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                       public void initChannel(SocketChannel ch) throws Exception {
                           ch.pipeline().addLast(messageHandler);
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
