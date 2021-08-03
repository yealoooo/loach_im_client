package cn.loach.client.foreign;

import cn.loach.client.exceptions.LoachException;
import cn.loach.client.foreign.callback.*;
import cn.loach.client.handler.LengthFieldFrameProtocolHandler;
import cn.loach.client.handler.LoginAuthResponseHandler;
import cn.loach.client.handler.SingleMessageResponseHandler;
import cn.loach.client.message.request.LoginAuthRequestMessage;
import cn.loach.client.message.response.LoginAuthResponseMessage;
import cn.loach.client.message.response.SingleChatResponseMessage;
import cn.loach.client.protocol.MessageDecoder;
import cn.loach.client.protocol.MessageEcoder;
import cn.loach.client.service.loginAuth.LoginAuthService;
import cn.loach.client.service.loginAuth.impl.LoginAuthServiceImpl;
import cn.loach.client.util.StringUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.net.InetSocketAddress;

public class LoachTcpClient implements Runnable {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(LoachTcpClient.class);

    private final LoginAuthService loginAuthService = LoginAuthServiceImpl.getInstance();

    private String host;

    private int port;

    private String token;

    public static CreateConnection createConnection() {
        return new CreateConnection();
    }

    private LoachTcpClient(String host,
                           int port,
                           String token,
                           LoachClientCallback loachClientCallback) {
        this.host = host;
        this.port = port;
        this.token = token;

        CallBackContainer.setLoachClientCallback(loachClientCallback);
    }

    @Override
    public void run() {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Channel channel = null;
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new LengthFieldFrameProtocolHandler());
                    ch.pipeline().addLast(new MessageDecoder());
                    ch.pipeline().addLast(new MessageEcoder());
                    ch.pipeline().addLast("exceptionHandler", new ChannelDuplexHandler () {
                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                            ctx.close();
                            logger.error("连接时异常: ", cause.getMessage(), cause.getCause());
                            CallBackContainer.onException(new LoachException("链接异常，异常信息：" + cause.getMessage()));
                        }
                    });
                    ch.pipeline().addLast(new LoginAuthResponseHandler());
                    ch.pipeline().addLast(new SingleMessageResponseHandler());
                    ch.pipeline().addLast("client handler", new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) {
                            LoginAuthRequestMessage loginAuthRequestMessage = loginAuthService.login(token);
                            ctx.writeAndFlush(loginAuthRequestMessage);
                        }

                        @Override
                        public void channelUnregistered(ChannelHandlerContext ctx) {
                            ctx.fireChannelUnregistered();
                            CallBackContainer.onClose("断开连接");
                        }
                        @Override
                        public void handlerRemoved(ChannelHandlerContext ctx) {
                            try {
                                super.handlerRemoved(ctx);
                            } catch (Exception e) {
                                CallBackContainer.onException(new LoachException("初始化连接失败：" + e.getMessage()));
                                CallBackContainer.onClose("初始化连接失败：" + e.getMessage() + "----断开连接");
                            }
                        }
                    });

                }
            });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(host, port));

            channel = channelFuture.channel();
            channel.closeFuture().sync();
        } catch (Exception ex) {
            throw new LoachException("初始化链接异常");
        } finally {
            worker.shutdownGracefully();
            if (null != channel) {
                channel.closeFuture();
            }
        }
    }


    public static class CreateConnection {
        private String host;

        private int port;

        private String token;

        private LoachClientCallback loachClientCallback;

        private CreateConnection() {
        }

        public CreateConnection host(String host) {
            this.host = host;
            return this;
        }

        public CreateConnection port(int port) {
            this.port = port;
            return this;
        }

        public CreateConnection token(String token) {
            this.token = token;
            return this;
        }

        public CreateConnection loachClientCallBack(LoachClientCallback loachClientCallback) {
            this.loachClientCallback = loachClientCallback;
            return this;
        }

        public void init() {
            if (StringUtil.isEmpty(this.host)) {
                throw new LoachException("host is null");
            }else if (StringUtil.isEmpty(this.token)) {
                throw new LoachException("token is null");
            }else if (null == this.loachClientCallback) {
                throw new LoachException("loachClientCallback is null");
            }
            LoachTcpClient loachTcpClient = new LoachTcpClient(this.host, this.port, this.token, this.loachClientCallback);
            new Thread(loachTcpClient).start();
        }
    }
}
