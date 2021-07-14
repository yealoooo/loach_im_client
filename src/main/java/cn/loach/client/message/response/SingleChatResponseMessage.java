package cn.loach.client.message.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleChatResponseMessage extends ResponseMessage{
    /**
     * 数据体
     */
    private String content;

    public SingleChatResponseMessage() {
    }
}
