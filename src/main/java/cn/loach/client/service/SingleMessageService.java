package cn.loach.client.service;

import cn.loach.client.message.request.SingleChatRequestMessage;

public interface SingleMessageService {

    SingleChatRequestMessage getSendMessageModel(String message);
}
