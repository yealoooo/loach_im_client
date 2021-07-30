package cn.loach.client.foreign.http.response;

import cn.loach.client.exceptions.LoachException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class ResponseSerializable {

    public static<T> T serializable(String data, Class<T> tClass) {
        JSONObject resultJsonData = verify(data);
        return resultJsonData.getObject("data", tClass);
    }

    public static String serializableString(String data) {
        JSONObject resultJsonData = verify(data);
        return resultJsonData.getString("data");
    }

    public static<T> List<T> serializableArray(String data, Class<T> tClass) {
        JSONObject resultJsonData = verify(data);

        return JSON.parseArray(resultJsonData.getJSONObject("data").getString("data"), tClass);
    }

    private static JSONObject verify(String data) {
        JSONObject resultJsonData = JSON.parseObject(data);
        if (!resultJsonData.getInteger("code").equals(200)) {
            throw new LoachException(resultJsonData.getString("message"));
        }
        return resultJsonData;
    }
}
