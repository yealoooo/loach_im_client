package cn.loach.client.message.request;

import cn.loach.client.enums.MessageContentTypeEnum;
import cn.loach.client.message.Message;

import java.io.Serializable;

public class RequestMessage extends Message implements Serializable {

    /**
     * 发送方
     */
    private String fromUid;

    /**
     * 目标
     */
    private String toUid;

    /**
     * 数据类型
     */
    private int contentType;

    public void setContentType(MessageContentTypeEnum messageContentTypeEnum) {
        this.contentType = messageContentTypeEnum.ordinal();
    }

    public RequestMessage() {
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

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }
}
