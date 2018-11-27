package com.nick.ch4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyNioServer {
    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.copiedBuffer("Hi! The response is from NettyNioServer\r\n", Charset.forName("UTF-8"));
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(createChannelHandler(buf));

            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    /**
     * Specify ChannelInitializer to handle every connection
     * @param byteBuf
     * @return
     */
    private ChannelHandler createChannelHandler(ByteBuf byteBuf) {
        ChannelHandler handler = new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelHandler adapter = createInboundHandlerAdapter(byteBuf);
                ch.pipeline().addLast(adapter);
            }
        };
        return handler;
    }

    /**
     * Create an ChannelInboundHandlerAdapter to intercept and handle event
     * @param byteBuf
     * @return
     */
    private ChannelHandler createInboundHandlerAdapter(ByteBuf byteBuf) {
        ChannelHandler adapter = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                ctx.writeAndFlush(byteBuf.duplicate()).addListener(ChannelFutureListener.CLOSE);
            }
        };
        return adapter;
    }

    public static void main(String[] args) {
        NettyNioServer nettyNioServer = new NettyNioServer();
        try {
            nettyNioServer.server(10091);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
