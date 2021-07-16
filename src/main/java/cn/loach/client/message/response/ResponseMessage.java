package cn.loach.client.message.response;

import cn.loach.client.enums.MessageContentTypeEnum;
import cn.loach.client.message.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage extends Message {
    /**
     * 返回请求结果码
     */
    private int code;

    /**
     * 请求结果说明
     */
    private String responseMessage;

    /**
     * 请求成功与否表示
     */
    private boolean requestFlag;

    /**
     * 数据类型
     */
    private int contentType;


    public void setContentType(MessageContentTypeEnum messageContentTypeEnum) {
        this.contentType = messageContentTypeEnum.ordinal();
    }
}
