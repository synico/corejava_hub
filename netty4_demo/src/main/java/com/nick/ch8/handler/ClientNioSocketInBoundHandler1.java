package com.nick.ch8.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class ClientNioSocketInBoundHandler1 extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        System.out.println("Received data from server! " + byteBuf.toString(CharsetUtil.UTF_8));
    }
}
