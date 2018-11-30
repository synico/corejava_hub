package com.nick.ch8.client;

import com.nick.ch8.handler.ClientNioSocketInBoundHandler1;
import com.nick.ch8.listeners.ClientFutureListener1;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NioSocketClient {

    public void connectTo() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientNioSocketInBoundHandler1());

        // For regular TCP connections, please use the provided {@link #connect()} methods.
        // For UTP, please use bind()
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 10095));
        future.addListener(new ClientFutureListener1());
    }

    public static void main(String[] args) {
        NioSocketClient client = new NioSocketClient();
        client.connectTo();
    }

}
