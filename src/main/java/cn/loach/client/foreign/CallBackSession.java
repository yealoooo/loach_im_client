package cn.loach.client.foreign;

import cn.loach.client.foreign.callback.LoachResponseCallback;
import cn.loach.client.message.response.LoginAuthResponseMessage;
import cn.loach.client.message.response.SingleChatResponseMessage;
import io.netty.channel.ChannelHandlerContext;

public class CallBackSession {
    private static LoachResponseCallback<SingleChatResponseMessage> singleResponseMessageCallback;

    private static LoachResponseCallback<LoginAuthResponseMessage> loginAuthCallback;

    public static void setSingleResponseMessageCallback(LoachResponseCallback<SingleChatResponseMessage> singleResponseMessageCallback) {
        CallBackSession.singleResponseMessageCallback = singleResponseMessageCallback;
    }

    public static void setLoginAuthCallback(LoachResponseCallback<LoginAuthResponseMessage> loginAuthCallback) {
        CallBackSession.loginAuthCallback = loginAuthCallback;
    }

    public static LoachResponseCallback<SingleChatResponseMessage> getSingleResponseMessageCallback() {
        return CallBackSession.singleResponseMessageCallback;
    }

    public static LoachResponseCallback<LoginAuthResponseMessage> getLoginAuthCallback() {
        return CallBackSession.loginAuthCallback;
    }

    public static void singleMessageOnNext(SingleChatResponseMessage responseMessage) {
        getSingleResponseMessageCallback().onNext(responseMessage);
    }

    public static void loginAuthOnNext(ChannelHandlerContext ctx, LoginAuthResponseMessage responseMessage) {
        SendMessageUtil.setCtx(ctx);
        getLoginAuthCallback().onNext(responseMessage);
    }
}
