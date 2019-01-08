package com.ge.dms.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

public class MqttMsgInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(MqttMsgInboundHandler.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("inbound msg: " + msg.toString());
        ctx.writeAndFlush(Unpooled.copiedBuffer("message from client has been read", CharsetUtil.UTF_8));
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("mqttMsgInboundHandler is active");
        ctx.fireChannelActive();
    }
}
