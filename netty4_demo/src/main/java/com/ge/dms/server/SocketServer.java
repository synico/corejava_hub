package com.ge.dms.server;

import com.ge.dms.handler.ServerMsgSaveInboundHandler;
import com.ge.dms.handler.SocketServerByte2MsgDecoder;
import com.ge.dms.handler.SocketServerMsgInboundHandler;
import com.ge.dms.handler.SocketServerMsgOutboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

public class SocketServer {

    private static final Logger log = Logger.getLogger(SocketServer.class);

    private void start() {
        final SocketServerMsgInboundHandler msgInboundHandler = new SocketServerMsgInboundHandler();
        final SocketServerMsgOutboundHandler msgOutboundHandler = new SocketServerMsgOutboundHandler();
        final ChannelInboundHandlerAdapter msgSaveHandler = new ServerMsgSaveInboundHandler();
        final ByteToMessageDecoder byte2MsgDecoder = new SocketServerByte2MsgDecoder();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .localAddress(new InetSocketAddress(34567))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                       public void initChannel(SocketChannel ch) throws Exception {
                           log.info("Thread " + Thread.currentThread().getId() + " create new SocketChannel");
                           ch.config().setReceiveBufferSize(Integer.MAX_VALUE);
                           ch.config().setKeepAlive(true);
                           ch.config().setReceiveBufferSize(Integer.MAX_VALUE);
                           ch.pipeline().addLast("byte2MsgDecoder", byte2MsgDecoder);
                           ch.pipeline().addLast("msgInboundHandler", msgInboundHandler);
                           ch.pipeline().addLast("msgSaveInboundHandler", msgSaveHandler);
                           ch.pipeline().addLast("msgOutboundHandler", msgOutboundHandler);
                       }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            log.info("shutdownGracefully");
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
