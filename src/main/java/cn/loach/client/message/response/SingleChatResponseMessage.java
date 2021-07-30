package cn.loach.client.message.response;

import cn.loach.client.message.Message;
import cn.loach.client.util.MessageIdGenerator;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }
}
