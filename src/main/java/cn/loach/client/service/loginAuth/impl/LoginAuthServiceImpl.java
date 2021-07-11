package cn.loach.client.service.loginAuth.impl;

import cn.loach.client.message.request.LoginAuthRequestMessage;
import cn.loach.client.service.loginAuth.LoginAuthService;

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
        return loginAuthRequestMessage;
    }
}
