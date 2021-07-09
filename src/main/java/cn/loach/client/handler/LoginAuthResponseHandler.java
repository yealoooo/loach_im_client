package cn.loach.client.handler;

import cn.loach.client.message.request.SingleChatRequestMessage;
import cn.loach.client.message.response.LoginAuthResponseMessage;
import cn.loach.client.service.singleMessage.impl.SingleMessageServiceIMpl;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

public class LoginAuthResponseHandler extends SimpleChannelInboundHandler<LoginAuthResponseMessage> {
    Scanner scanner = new Scanner(System.in);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginAuthResponseMessage msg) {
        if (msg.getCode() == 200) {
            System.out.println("登录成功--------------------");

            System.out.println("返回信息: " + JSON.toJSONString(msg));

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
    }
}
