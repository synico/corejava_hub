package com.ge.dms.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

public class ServerMsgSaveInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(ServerMsgSaveInboundHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String input = null;
        if(msg instanceof ByteBuf) {
            input = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
        }
        log.info("ServerMsgSaveInboundHandler is reading msg    : " + input);
        ctx.writeAndFlush(Unpooled.copiedBuffer("message from client has been read", CharsetUtil.UTF_8));
//        ctx.fireChannelRead(msg);//stop to perform event propagation
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        log.info("channelReadComplete is invoked");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("exceptionCaught is invoked");
        cause.printStackTrace();
        ctx.close();
    }

}
