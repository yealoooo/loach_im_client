package cn.loach.client.handler;

import cn.loach.client.message.request.LoginAuthRequestMessage;
import cn.loach.client.message.request.SingleChatRequestMessage;
import cn.loach.client.service.loginAuth.LoginAuthService;
import cn.loach.client.service.loginAuth.impl.LoginAuthServiceImpl;
import cn.loach.client.service.singleMessage.impl.SingleMessageServiceIMpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class ClientMenuHandler extends ChannelInboundHandlerAdapter {

    private LoginAuthService loginAuthService = LoginAuthServiceImpl.getInstance();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        LoginAuthRequestMessage loginAuthRequestMessage = loginAuthService.login("token2");
        ctx.writeAndFlush(loginAuthRequestMessage);
    }
}
