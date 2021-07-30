package cn.loach.client.util;

import java.util.UUID;

public class MessageIdGenerator {

    public static String getMessageId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
