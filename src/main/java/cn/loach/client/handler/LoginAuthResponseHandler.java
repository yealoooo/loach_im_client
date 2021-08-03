package cn.loach.client.handler;

import cn.loach.client.foreign.callback.CallBackContainer;
import cn.loach.client.message.response.LoginAuthResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginAuthResponseHandler extends SimpleChannelInboundHandler<LoginAuthResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginAuthResponseMessage msg) {
        CallBackContainer.onNextLoginAuthResponse(ctx, msg);
    }
}
