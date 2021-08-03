package cn.loach.client.handler;

import cn.loach.client.foreign.callback.CallBackContainer;
import cn.loach.client.message.response.SingleChatResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SingleMessageResponseHandler extends SimpleChannelInboundHandler<SingleChatResponseMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SingleChatResponseMessage msg) {
        CallBackContainer.onNextSingleResponseMessage(msg);
    }
}
