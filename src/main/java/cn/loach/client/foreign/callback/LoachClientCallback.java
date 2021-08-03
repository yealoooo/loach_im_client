package cn.loach.client.foreign.callback;

import cn.loach.client.exceptions.LoachException;
import cn.loach.client.message.response.LoginAuthResponseMessage;
import cn.loach.client.message.response.SingleChatResponseMessage;

public abstract class LoachClientCallback {
    public abstract void onNextLoginAuthResponse(LoginAuthResponseMessage loginAuthResponseMessage);

    public void onNextSingleResponseMessage(SingleChatResponseMessage singleChatResponseMessage){

    }

    public abstract void onException(LoachException exception);

    public abstract void onClose(String message);
}
