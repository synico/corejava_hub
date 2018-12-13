package com.ge.dms.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

public class SocketCilentMsgHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(SocketCilentMsgHandler.class);

    private String data;

    public SocketCilentMsgHandler(String data) {
        this.data = data;
    }

    //This is a callback method which is invoked by {@link}
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Client: channel actived");
        ByteBuf encoded = ctx.alloc().buffer(4 * this.data.length());
        encoded.writeBytes(this.data.getBytes("UTF-8"));
        ctx.writeAndFlush(encoded);
        log.info("Client: date has been sent");
        encoded.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info(cause.getMessage());
    }

}
