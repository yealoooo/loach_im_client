package cn.loach.client.message.response;

import cn.loach.client.enums.MessageContentTypeEnum;
import cn.loach.client.util.MessageIdGenerator;

import java.io.Serializable;


public class LoginAuthResponseMessage extends ResponseMessage implements Serializable {

    private String content;

    public LoginAuthResponseMessage() {
        setMessageId(MessageIdGenerator.getMessageId());
        setContentType(MessageContentTypeEnum.TEXT);
        setTimeStamp(System.currentTimeMillis());
        setChatType(AUTH);
        setMessageType(MESSAGE_RESPONSE_TYPE);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
