package cn.loach.client.message.request;


import cn.loach.client.message.Message;
import cn.loach.client.util.MessageIdGenerator;

import java.io.Serializable;

public class SingleChatRequestMessage extends RequestMessage implements Serializable {
    /**
     * 数据体
     */
    private String content;

    /**
     * 昵称
     */
    private String fromNickName;

    /**
     * 头像
     */
    private String fromAvatar;

    /**
     * 会话Id
     */
    private String conversationId;

    private String ext;



    @Override
    public String toString() {

        return "SingleChatMessage{" +
                "fromId='" + super.getFromUid() + '\'' +
                ", toId='" + super.getToUid() + '\'' +
                ", content='" + content + '\'' +
                '}' + super.toString();
    }

    public SingleChatRequestMessage() {
        setChatType(Message.SINGLE);
        setMessageType(Message.MESSAGE_REQUEST_TYPE);
        setTimeStamp(System.currentTimeMillis());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromNickName() {
        return fromNickName;
    }

    public void setFromNickName(String fromNickName) {
        this.fromNickName = fromNickName;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
