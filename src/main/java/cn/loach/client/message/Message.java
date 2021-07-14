package cn.loach.client.message;

import cn.loach.client.message.request.LoginAuthRequestMessage;
import cn.loach.client.message.request.SingleChatRequestMessage;
import cn.loach.client.message.response.LoginAuthResponseMessage;
import cn.loach.client.message.response.SingleChatResponseMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public abstract class Message implements Serializable {

    /**
     * 消息Id  唯一标识
     */
    private String messageId;

    /**
     * 消息发生时间
     */
    private long timeStamp;

    /**
     * 普通  、  撤回   、 已读
     */
    private int messageType;

    /**
     * 消息类型   群聊  单聊  校验
     */
    private int chatType;


    /**
     * chatType
     */
    public static final int SINGLE = 1;
    public static final int GROUP = 2;
    public static final int AUTH = 3;
    /**
     * messageType  所有消息类型
     */
    public static final int MESSAGE_REQUEST_TYPE = 1;  // 发送
    public static final int MESSAGE_RESPONSE_TYPE = 2; // 接收
    public static final int MESSAGE_RETRACT_TYPE = 3; // 撤回
    public static final int MESSAGE_READ_TYPE = 4; // 已读


    /**
     * SINGLE + MESSAGE_REQUEST_TYPE  = (1 + 1) = 2   一对一发送
     * SINGLE + MESSAGE_RESPONSE_TYPE  = (1 + 2) = 3   一对一发送
     *
     * GROUP + MESSAGE_REQUEST_TYPE  = (2 + 1) = 3   群组发送
     * GROUP + MESSAGE_RESPONSE_TYPE  = (2 + 2) = 4   群组接收
     *
     * SINGLE + MESSAGE_RETRACT_TYPE  = (1 + 3) = 4   单聊撤回
     * SINGLE + MESSAGE_READ_TYPE  = (1 + 4) = 5   单聊已读
     *
     * GROUP + MESSAGE_READ_TYPE  = (2 + 3) = 5   群组撤回
     * GROUP + MESSAGE_READ_TYPE  = (2 + 4) = 6   群组接收
     */


    public static final Map<Integer, Class<? extends Message>> messageClassMap = new HashMap<>();

    static {
        messageClassMap.put((SINGLE * 10) + MESSAGE_REQUEST_TYPE, SingleChatRequestMessage.class);
        messageClassMap.put((SINGLE * 10) + MESSAGE_RESPONSE_TYPE, SingleChatResponseMessage.class);
    }
}
