package com.ge.dms.server;

import com.ge.dms.handler.MqttMsgInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

public class MqttServer {

    private static final Logger log = Logger.getLogger(MqttServer.class);

    private void start() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        final MqttMsgInboundHandler mqttMsgInboundHandler = new MqttMsgInboundHandler();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(34568))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        MqttDecoder mqttDecoder = new MqttDecoder();

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("mqttMsgInboundHandler", mqttMsgInboundHandler);
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public static void main(String args[]) {
        MqttServer mqttServer = new MqttServer();
        mqttServer.start();
    }
}
