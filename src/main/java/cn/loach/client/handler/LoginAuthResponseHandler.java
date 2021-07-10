package cn.loach.client.handler;

import cn.loach.client.message.request.SingleChatRequestMessage;
import cn.loach.client.message.response.LoginAuthResponseMessage;
import cn.loach.client.service.singleMessage.impl.SingleMessageServiceIMpl;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginAuthResponseHandler extends SimpleChannelInboundHandler<LoginAuthResponseMessage> {
    Scanner scanner = new Scanner(System.in);

    public static final Map<String, Object> userDataMap = new HashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginAuthResponseMessage msg) {
        if (msg.getCode() == 200) {
            System.out.println("登录成功--------------------");

            System.out.println("返回信息: " + JSON.toJSONString(msg));

            new Thread(() -> {
                while (true) {
                    System.out.println("请输入需要发送的消息和目标逗号分割");
                    String next = scanner.next();

                    String[] inData = next.split(",");
                    SingleMessageServiceIMpl singleMessageServiceIMpl = SingleMessageServiceIMpl.getInstance();
                    SingleChatRequestMessage sendMessage = singleMessageServiceIMpl.getSendMessageModel(inData[1]);
                    sendMessage.setFromId(userDataMap.get("userId").toString());
                    sendMessage.setToId(inData[0]);

                    ctx.writeAndFlush(sendMessage);
                }
            }, "client handler thread").start();
        }
    }
}
