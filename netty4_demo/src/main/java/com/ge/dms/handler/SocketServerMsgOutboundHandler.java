package com.ge.dms.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

public class SocketServerMsgOutboundHandler extends ChannelOutboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(SocketServerMsgOutboundHandler.class);

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        log.info("read message in outbound handler");
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("write msg back to client in outbound handler");
        ctx.writeAndFlush(Unpooled.copiedBuffer("message in outbound handler", CharsetUtil.UTF_8));
    }


}
