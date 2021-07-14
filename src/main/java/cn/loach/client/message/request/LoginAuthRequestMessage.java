package cn.loach.client.message.request;

import cn.loach.client.enums.MessageContentTypeEnum;
import cn.loach.util.MessageIdGenerator;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginAuthRequestMessage extends RequestMessage implements Serializable {

    /**
     * 用户名
     */
    private String userName;
    public LoginAuthRequestMessage() {
        setTimeStamp(System.currentTimeMillis());
        setContentType(MessageContentTypeEnum.TEXT);
        setMessageId(MessageIdGenerator.getMessageId());
    }
}
