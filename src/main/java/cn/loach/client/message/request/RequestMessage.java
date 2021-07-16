package cn.loach.client.message.request;

import cn.loach.client.enums.MessageContentTypeEnum;
import cn.loach.client.message.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
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

}
