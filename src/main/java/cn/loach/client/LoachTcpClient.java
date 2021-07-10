package cn.loach.client;

import cn.loach.client.handler.ClientMenuHandler;
import cn.loach.client.handler.LengthFieldFrameProtocolHandler;
import cn.loach.client.handler.LoginAuthResponseHandler;
import cn.loach.client.handler.SingleMessageResponseHandler;
import cn.loach.client.protocol.MessageDecoder;
import cn.loach.client.protocol.MessageEcoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class LoachTcpClient implements LoachTcpClientInterface {

    @Override
    public void init(String host, int port) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LengthFieldFrameProtocolHandler());
                    ch.pipeline().addLast(new MessageDecoder());
                    ch.pipeline().addLast(new MessageEcoder());
                    ch.pipeline().addLast(new LoginAuthResponseHandler());
                    ch.pipeline().addLast(new SingleMessageResponseHandler());
                    ch.pipeline().addLast("client handler", new ClientMenuHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(host, port));
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            log.error("client error: {}", ex.getMessage());
            ex.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }

}
