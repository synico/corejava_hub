package com.nick.ch1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

public class SetupConnection {

    private static final Logger log = Logger.getLogger(SetupConnection.class);

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(34567))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        log.info("initChannel");
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                log.info("will close pipeline");
                                ctx.channel().pipeline().close().sync();//force thread to exit
                            }
                        });
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect().sync();
        channelFuture.addListener(listener -> {
            if (channelFuture.isSuccess()) {
                log.info("operationComplete");
            } else {
                Throwable cause = channelFuture.cause();
                cause.printStackTrace();
            }
        });
        channelFuture.channel().closeFuture().sync();
        eventLoopGroup.shutdownGracefully();
    }
}
