package cn.loach.client.message.response;

import cn.loach.client.message.Message;
import cn.loach.util.MessageIdGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleChatResponseMessage extends ResponseMessage {
    /**
     * 数据体
     */
    private String content;

    /**
     * 发送者
     */
    private String fromUid;

    /**
     * 接收者
     */
    private String toUid;

    public SingleChatResponseMessage() {
        setTimeStamp(System.currentTimeMillis());
        setMessageId(MessageIdGenerator.getMessageId());
        setChatType(Message.SINGLE);
        setMessageType(Message.MESSAGE_RESPONSE_TYPE);
    }
}
