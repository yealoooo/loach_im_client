package cn.loach.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class ClientCache {

    private static final String dataFileSrc = "/cache";
    private static final String userDataFileName = "/userData.txt";

    static {
        File file = new File(dataFileSrc);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public static void setUserData(String userId, String token) throws IOException {
        String fileSrc = dataFileSrc + userDataFileName;
        File file = new File(fileSrc);
        if (!file.exists()) {
            file.createNewFile();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("token", token);

        String dataJsonStr = jsonObject.toJSONString();

        writeByFileOutputStream(fileSrc, dataJsonStr);
    }

    public static String getUserId() throws IOException {
        StringBuffer data = getUserDataStr();
        if (data == null) return null;

        return JSON.parseObject(data.toString()).getString("userId");
    }

    public static String getToken() throws IOException {
        StringBuffer data = getUserDataStr();
        if (data == null) return null;

        return JSON.parseObject(data.toString()).getString("token");
    }

    private static StringBuffer getUserDataStr() throws IOException {
        RandomAccessFile r = new RandomAccessFile(dataFileSrc + userDataFileName, "r");
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        FileChannel channel = r.getChannel();

        StringBuffer data = new StringBuffer();
        while (channel.read(byteBuffer) != -1) {
            data.append(StandardCharsets.UTF_8.newDecoder().decode(byteBuffer));
        }

        if (StringUtil.isEmpty(data.toString())) {
            return null;
        }
        return data;
    }

    public static void writeByFileOutputStream(String _sDestFile,
                                               String _sContent) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(_sDestFile);
            fos.write(_sContent.getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
                fos = null;
            }
        }
    }
}
