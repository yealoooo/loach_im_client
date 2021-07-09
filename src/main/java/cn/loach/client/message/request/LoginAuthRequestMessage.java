package cn.loach.client.message.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginAuthRequestMessage extends RequestMessage implements Serializable {

    /**
     * 用户名
     */
    private String userName;
    public LoginAuthRequestMessage() {
        setMessageRequestTypeType(LOGIN_AUTH_MESSAGE_REQUEST_TYPE);
    }
}
