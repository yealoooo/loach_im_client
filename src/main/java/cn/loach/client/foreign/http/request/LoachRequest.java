package cn.loach.client.foreign.http.request;

import cn.loach.client.exceptions.LoachException;
import cn.loach.client.foreign.http.response.AuthResponse;
import cn.loach.client.foreign.http.response.ConversationInfo;
import cn.loach.client.foreign.http.response.ConversationMessage;
import cn.loach.client.foreign.http.response.UploadResponse;
import cn.loach.client.util.LoachHttpUtil;
import cn.loach.client.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.loach.client.foreign.http.response.ResponseSerializable.*;

public class LoachRequest {

    /**
     * 认证获取token
     */
    public static AuthResponse authGetToken(String appKey, String appSecret, String uid, String avatar, String nickname) {
        Map<String, String> parameters = new HashMap<String, String>(6){{
            put("appKey", appKey);
            put("appSecret", appSecret);
            put("uid", uid);
            put("nickName", nickname);
            put("avatar", avatar);
            put("terminalPlatform", "android");
        }};
        String result = LoachHttpUtil.sendGet( "/im-server/auth/getToken", parameters, null);
        return StringUtil.isEmpty(result) ? null : serializable(result, AuthResponse.class);
    }


    /**
     * 获取会话列表
     */
    public static List<ConversationInfo> getConversationList(String token) {
        Map<String, String> headers = new HashMap<String, String>(1){{
            put("Authorization", token);
        }};

        String result = LoachHttpUtil.sendGet("/conversation/list", null, headers);

        return StringUtil.isEmpty(result) ? null : serializableArray(result, ConversationInfo.class);
    }

    /**
     * 获取会话详情
     */
    public static ConversationInfo getConversationInfo(String token, String conversationId) {
        Map<String, String> parameters = new HashMap<String, String>(1){{
            put("conversationId", conversationId);
        }};
        String result = LoachHttpUtil.sendGet("/conversation/info-conversationId", parameters, getAuthorizationMap(token));

        return StringUtil.isEmpty(result) ? null :  serializable(result, ConversationInfo.class);
    }

    /**
     * 创建单聊会话
     * @return 会话ID
     */
    public static String createSingleConversation(String token, String toUid) {
        Map<String, String> parameters = new HashMap<String, String>(1){{
            put("toUid", toUid);
        }};
        String result = LoachHttpUtil.sendGet("/conversation/single-conversation", parameters, getAuthorizationMap(token));

        return StringUtil.isEmpty(result) ? null : serializableString(result);
    }

    /**
     * 根据会话 获取聊天记录
     * @param conversationId 会话ID
     */
    public static List<ConversationMessage> getConversationMessageList(String token, String conversationId){

        Map<String, String> parameters = new HashMap<String, String>(1){{
            put("conversationId", conversationId);
        }};
        String result = LoachHttpUtil.sendGet("/message/list", parameters, getAuthorizationMap(token));

        return StringUtil.isEmpty(result) ? null : serializableArray(result, ConversationMessage.class);
    }

    /**
     * 上传文件
     */
    public static UploadResponse uploadFile(String token, File file) {
        Map<String, File> fileMap = new HashMap<>();
        fileMap.put("file", file);
        try {
            String result = LoachHttpUtil.postFormData("/upload/upload-file", fileMap, null, getAuthorizationMap(token));
            return StringUtil.isEmpty(result) ? null : serializable(result, UploadResponse.class);
        } catch (IOException e) {
            throw new LoachException("调用异常" + e.getMessage());
        }
    }

    private static Map<String, String> getAuthorizationMap(String token) {
        return new HashMap<String, String>(1){{
            put("Authorization", token);
        }};
    }



}
