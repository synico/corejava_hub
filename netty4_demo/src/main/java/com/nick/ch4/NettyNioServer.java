package com.nick.ch4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyNioServer {
    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.copiedBuffer("Hi! The response is from NettyNioServer\r\n", Charset.forName("UTF-8"));
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b = b.group(group);
            b = b.channel(NioServerSocketChannel.class);
            b = b.localAddress(new InetSocketAddress(port));
            b = b.childHandler(createChannelHandler(buf));
//            b.group(group).channel(NioServerSocketChannel.class)
//                    .localAddress(new InetSocketAddress(port))
//                    .childHandler(createChannelHandler(buf));
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    /**
     * Specify ChannelInitializer to handle channel.
     * To avoid race condition, each channel should have exclusive ChannelHandler.
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
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf in = (ByteBuf) msg;
                System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
                ctx.write(in);
            }
            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) {
                ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                        .addListener(ChannelFutureListener.CLOSE);
            }
            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                cause.printStackTrace();
                ctx.close();
            }
        };
        return adapter;
    }

    public static void main(String[] args) {
        NettyNioServer nettyNioServer = new NettyNioServer();
        try {
            System.out.println("NettyNioServer is up");
            nettyNioServer.server(10091);
            System.out.println("NettyNioServer is running");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
