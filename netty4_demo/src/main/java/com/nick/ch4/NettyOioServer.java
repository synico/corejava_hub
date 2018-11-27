package com.nick.ch4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyOioServer {
    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi! This is from NettyOioServer", Charset.forName("UTF-8")));
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(createChannelHandler(buf));
            //Bind to Netty server to receive coming request
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
        NettyOioServer nettyOioServer = new NettyOioServer();
        try {
            nettyOioServer.server(10081);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
