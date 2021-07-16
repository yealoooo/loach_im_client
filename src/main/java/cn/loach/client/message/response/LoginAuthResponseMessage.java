package cn.loach.client.message.response;

import cn.loach.client.enums.MessageContentTypeEnum;
import cn.loach.util.MessageIdGenerator;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static cn.loach.client.message.Message.AUTH;
import static cn.loach.client.message.Message.MESSAGE_RESPONSE_TYPE;

@Getter
@Setter
public class LoginAuthResponseMessage extends ResponseMessage implements Serializable {

    private String content;

    public LoginAuthResponseMessage() {
        setMessageId(MessageIdGenerator.getMessageId());
        setContentType(MessageContentTypeEnum.TEXT);
        setTimeStamp(System.currentTimeMillis());
        setChatType(AUTH);
        setMessageType(MESSAGE_RESPONSE_TYPE);
    }
}
