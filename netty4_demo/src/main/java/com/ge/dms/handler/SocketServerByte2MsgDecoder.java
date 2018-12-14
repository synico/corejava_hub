package com.ge.dms.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class SocketServerByte2MsgDecoder extends ByteToMessageDecoder {

    private static final Logger log = Logger.getLogger(SocketServerByte2MsgDecoder.class);

    public SocketServerByte2MsgDecoder() {
        log.info("Current thread id: " + Thread.currentThread().getId());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive is invoked");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("channelRead is invoked");
        ReferenceCountUtil.retain(msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("decode: " + in.toString(CharsetUtil.UTF_8));
    }
}
