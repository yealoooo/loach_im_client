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

    Scanner scanner = new Scanner(System.in);

    private LoginAuthService loginAuthService = LoginAuthServiceImpl.getInstance();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

//        System.out.println("请输入您的登录用户名");
//        String userName = scanner.next();
//
//        LoginAuthResponseHandler.userDataMap.put("userId", userName);
//
//        LoginAuthRequestMessage loginAuthRequestMessage = loginAuthService.login(userName);
//        ctx.writeAndFlush(loginAuthRequestMessage);

        new Thread(() -> {
            while (true) {
                System.out.println("请输入需要发送的消息和目标逗号分割");
                String next = scanner.next();

                String[] inData = next.split(",");
                SingleMessageServiceIMpl singleMessageServiceIMpl = SingleMessageServiceIMpl.getInstance();
                SingleChatRequestMessage sendMessage = singleMessageServiceIMpl.getSendMessageModel(inData[1]);
                sendMessage.setToId(inData[0]);
                ctx.writeAndFlush(sendMessage);
            }
        }, "client handler thread").start();
    }
}
