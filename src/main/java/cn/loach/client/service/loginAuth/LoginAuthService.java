package cn.loach.client.service.loginAuth;

import cn.loach.client.message.request.LoginAuthRequestMessage;

public interface LoginAuthService {

    LoginAuthRequestMessage login(String token);
}
