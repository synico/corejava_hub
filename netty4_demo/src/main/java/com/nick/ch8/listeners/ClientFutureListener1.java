package com.nick.ch8.listeners;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class ClientFutureListener1 implements ChannelFutureListener {

    @Override
    public void operationComplete(ChannelFuture channelFuture) {
        if(channelFuture.isSuccess()) {
            System.out.println("Connection established");
        } else {
            System.out.println("Connection attempt failed");
            channelFuture.cause().printStackTrace();
        }
    }
}
