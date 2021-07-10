package cn.loach.client.handler;

import cn.loach.client.message.response.SingleChatResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleMessageResponseHandler extends SimpleChannelInboundHandler<SingleChatResponseMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SingleChatResponseMessage msg) {
        log.info("服务端读取到SingleMessageRequest： {}", msg);
    }
}
