package com.nick.ch8.server;

import com.nick.ch8.handler.ServerNioInboundHandler1;
import com.nick.ch8.listeners.ServerFutureListener1;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NioSocketServer {

    public void serve() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerNioInboundHandler1());
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(10095));
        future.addListener(new ServerFutureListener1());
    }

    public static void main(String[] args) {
        NioSocketServer server = new NioSocketServer();
        server.serve();
    }
}
