package cn.loach.client.service.loginAuth.impl;

import cn.loach.client.enums.MessageContentTypeEnum;
import cn.loach.client.message.request.LoginAuthRequestMessage;
import cn.loach.client.service.loginAuth.LoginAuthService;
import cn.loach.util.MessageIdGenerator;

public class LoginAuthServiceImpl implements LoginAuthService {

    private static volatile LoginAuthServiceImpl loginAuthService;

    public static LoginAuthServiceImpl getInstance() {
        if (null == loginAuthService) {
            loginAuthService = new LoginAuthServiceImpl();
            synchronized (LoginAuthServiceImpl.class) {
                if (null == loginAuthService) {
                    loginAuthService = new LoginAuthServiceImpl();
                }
            }
        }
        return loginAuthService;
    }

    @Override
    public LoginAuthRequestMessage login(String userName) {
        LoginAuthRequestMessage loginAuthRequestMessage = new LoginAuthRequestMessage();
        loginAuthRequestMessage.setUserName(userName);
        loginAuthRequestMessage.setMessageId(MessageIdGenerator.getMessageId());
        loginAuthRequestMessage.setContentType(MessageContentTypeEnum.TEXT);
        loginAuthRequestMessage.setTimeStamp(System.currentTimeMillis());
        return loginAuthRequestMessage;
    }
}
