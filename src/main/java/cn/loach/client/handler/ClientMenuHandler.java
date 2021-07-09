package cn.loach.client.handler;

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

        System.out.println("请输入您的登录用户名");
        String userName = scanner.next();

        loginAuthService.login(userName);

        new Thread(() -> {
            while (true) {
                System.out.println("请输入需要发送的消息");
                String next = scanner.next();

                SingleMessageServiceIMpl singleMessageServiceIMpl = SingleMessageServiceIMpl.getInstance();
                SingleChatRequestMessage sendMessage = singleMessageServiceIMpl.getSendMessageModel(next);
                sendMessage.setFromId("123");
                sendMessage.setToId("456");

                ctx.writeAndFlush(sendMessage);
            }
        }, "client handler thread").start();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("客户端读取到的数据:{}", msg.toString());
    }
}
