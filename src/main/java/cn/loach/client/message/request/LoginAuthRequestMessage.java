package cn.loach.client.message.request;

import java.io.Serializable;

public class LoginAuthRequestMessage extends RequestMessage implements Serializable {

    /**
     * token
     */
    private String authToken;

    public LoginAuthRequestMessage() {
        setChatType(AUTH);
        setMessageType(MESSAGE_REQUEST_TYPE);
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
