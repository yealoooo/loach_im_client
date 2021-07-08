package cn.loach.client.message.response;

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


    private ResponseMessage(int code, String responseMessage, boolean requestFlag) {
        this.code = code;
        this.responseMessage = responseMessage;
        this.requestFlag = requestFlag;
    }

    private static ResponseMessage success(int code, String responseMessage, Message responseContent) {
        return new ResponseMessage(code, responseMessage, true);
    }

    public static ResponseMessage success(int code, String responseMessage) {
        return success(code, responseMessage, null);
    }

    public static ResponseMessage success(Message responseContent) {
        return success(200, "request success", responseContent);
    }

    public static ResponseMessage error(int code, String responseMessage) {
        return new ResponseMessage(code, responseMessage, false);
    }

    public static ResponseMessage error() {
        return error(500, "request error");
    }


}
