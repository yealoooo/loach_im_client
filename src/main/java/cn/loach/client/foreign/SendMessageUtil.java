package cn.loach.client.foreign;

import cn.loach.client.message.request.LoginAuthRequestMessage;
import cn.loach.client.message.request.SingleChatRequestMessage;
import io.netty.channel.ChannelHandlerContext;

public class SendMessageUtil {

    private static ChannelHandlerContext ctx;

    protected static void setCtx(ChannelHandlerContext ctx) {
        SendMessageUtil.ctx = ctx;
    }

    public static void send(SingleChatRequestMessage singleChatRequestMessage){
        ctx.writeAndFlush(singleChatRequestMessage);
    }

    public static void send(LoginAuthRequestMessage loginAuthRequestMessage) {
        ctx.writeAndFlush(loginAuthRequestMessage);
    }
}
