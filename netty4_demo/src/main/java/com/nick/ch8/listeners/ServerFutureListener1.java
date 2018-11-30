package com.nick.ch8.listeners;

import io.netty.channel.ChannelFuture;

public class ServerFutureListener1 extends ClientFutureListener1 {
    @Override
    public void operationComplete(ChannelFuture channelFuture) {
        if(channelFuture.isSuccess()) {
            System.out.println("Server bound");
        } else {
            System.err.println("Bound attempt failed");
            channelFuture.cause().printStackTrace();
        }

    }
}
