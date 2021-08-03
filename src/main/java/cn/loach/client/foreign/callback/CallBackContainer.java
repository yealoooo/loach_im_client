package cn.loach.client.foreign.callback;

import cn.loach.client.exceptions.LoachException;
import cn.loach.client.foreign.SendMessageUtil;
import cn.loach.client.message.response.LoginAuthResponseMessage;
import cn.loach.client.message.response.SingleChatResponseMessage;
import io.netty.channel.ChannelHandlerContext;

public class CallBackContainer {

    private static LoachClientCallback loachClientCallback;


    public static void setLoachClientCallback(LoachClientCallback loachClientCallback) {
        CallBackContainer.loachClientCallback = loachClientCallback;
    }

    private static LoachClientCallback getLoachClientCallback() {
        return CallBackContainer.loachClientCallback;
    }


    public static void onNextLoginAuthResponse(ChannelHandlerContext ctx, LoginAuthResponseMessage loginAuthResponseMessage){
        SendMessageUtil.setCtx(ctx);
        getLoachClientCallback().onNextLoginAuthResponse(loginAuthResponseMessage);
    }

    public static void onNextSingleResponseMessage(SingleChatResponseMessage singleChatResponseMessage){
        getLoachClientCallback().onNextSingleResponseMessage(singleChatResponseMessage);
    }

    public static void onException(LoachException exception){
        getLoachClientCallback().onException(exception);
    }

    public static void onClose(String message) {
        getLoachClientCallback().onClose(message);
    }
}
