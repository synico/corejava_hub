package com.ge.dms.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

@ChannelHandler.Sharable
public class SocketServerMsgOutboundHandler extends ChannelOutboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(SocketServerMsgOutboundHandler.class);

    public SocketServerMsgOutboundHandler() {
        log.info("Current thread id: " + Thread.currentThread().getId());
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception{
        log.info("read message in outbound handler");
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        log.info("write msg back to client in outbound handler");
        ctx.writeAndFlush(Unpooled.copiedBuffer("message in outbound handler", CharsetUtil.UTF_8));
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        log.info("ready to close connection");
        ctx.close(promise);
    }


}
