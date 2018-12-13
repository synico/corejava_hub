package com.ge.dms.client;

import com.ge.dms.handler.SocketCilentMsgHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class SocketClient {

    private static final Logger log = Logger.getLogger(SocketClient.class);

    private String readData() {
        File dataFile = new File("/home/nick/data/router1");
        BufferedReader reader = null;
        StringBuilder msg = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile)));
            reader.lines().forEach(line -> msg.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        String result = msg.toString();
        log.info("msg length: " + result.length());
        return result;
    }

    public void sendData(String data) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(34567))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new SocketCilentMsgHandler(data));
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.addListener(listener -> log.info("send message is completed"));
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        SocketClient client = new SocketClient();
        String data = client.readData();
        client.sendData(data);
    }
}
