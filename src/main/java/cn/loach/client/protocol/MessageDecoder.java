package cn.loach.client.protocol;

import cn.loach.client.message.Message;
import cn.loach.client.serializable.LoachSerializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class MessageDecoder extends ByteToMessageDecoder {

    /**
     * 序列化类型
     */
    private static final int serializableType = LoachSerializable.JSON_SERIALIZABLE_TYPE;


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws UnsupportedEncodingException {
        int magicNum = in.readInt();

        int version = in.readInt();

        int serializerType = in.readInt();

        int messageRequestTypeType = in.readInt();

        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);

        Message message = LoachSerializable
                .getSerializable(serializableType)
                .deserialize(Message.messageClassMap.get(messageRequestTypeType), bytes);


        log.debug("{}, {}, {}, {}, {}", magicNum, version, serializerType, messageRequestTypeType, length);
        log.debug("requestMessage: {}", message);
        list.add(message);
    }
}
