package com.ge.dms.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

@ChannelHandler.Sharable
public class SocketServerMsgInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(SocketServerMsgInboundHandler.class);

    public SocketServerMsgInboundHandler() {
        log.info("Current thread id: " + Thread.currentThread().getId());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive is invoked");
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        log.info("SocketServerMsgInboundHandler is reading msg: " + in.toString(CharsetUtil.UTF_8));
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        log.info("channelReadComplete is invoked");
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        ctx.fireChannelReadComplete();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("exceptionCaught is invoked");
        cause.printStackTrace();
        ctx.close();
    }

}
