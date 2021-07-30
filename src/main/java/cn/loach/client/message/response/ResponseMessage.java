package cn.loach.client.message.response;

import cn.loach.client.enums.MessageContentTypeEnum;
import cn.loach.client.message.Message;

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

    public ResponseMessage() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public boolean isRequestFlag() {
        return requestFlag;
    }

    public void setRequestFlag(boolean requestFlag) {
        this.requestFlag = requestFlag;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }
}
