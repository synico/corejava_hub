package com.ge.dms.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.Logger;

public class SocketClientMsgHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(SocketClientMsgHandler.class);

    private String data;

    public SocketClientMsgHandler(String data) {
        this.data = data;
    }

    //This is a callback method which is invoked by {@link}
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Client: channel is active");
        ByteBuf encoded = ctx.alloc().buffer(4 * this.data.length());
        encoded.writeBytes(this.data.getBytes("UTF-8"));
        ctx.writeAndFlush(encoded);
        log.info("Client: data has been sent: " + encoded.toString(CharsetUtil.UTF_8));
        encoded.release();
//        ReferenceCountUtil.release(encoded);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(msg instanceof ByteBuf) {
            log.info("Client received: " + ((ByteBuf)msg).toString(CharsetUtil.UTF_8));
        } else {
            log.info("Client received: " + msg.toString());
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info(cause.getMessage());
    }

}
