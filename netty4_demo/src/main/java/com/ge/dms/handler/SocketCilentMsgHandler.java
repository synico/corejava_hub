package com.ge.dms.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SocketCilentMsgHandler extends ChannelInboundHandlerAdapter {

    private String data;

    public SocketCilentMsgHandler(String data) {
        this.data = data;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf encoded = ctx.alloc().buffer(4 * this.data.length());
        encoded.writeBytes(this.data.getBytes("UTF-8"));
        ctx.writeAndFlush(encoded);
        encoded.release();
    }

}
