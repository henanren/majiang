package game.flash843;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

/**
 * flash 843服务
 *
 * @author zuoge85 on 14-4-4.
 */
public class Flash843 {

    private final static Logger log = LoggerFactory.getLogger(Flash843.class);
    private static final int DEFAULT_BOSS_THREAD_NUMS = 4;
    private static final int DEFAULT_WORKER_THREAD_NUMS = 8;


    public static final Flash843 createServer(int port) throws UnsupportedEncodingException {
        final String text = "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"4999-9999\"/></cross-domain-policy>";
//        String requestText = "<policy-file-request/>\0";
//        byte[] requestBytes = requestText.getBytes("utf8");

//        final ByteBuf firstMessage = Unpooled.wrappedBuffer(text.getBytes("utf8"));
//        firstMessage.retain();
        return new Flash843(port, new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline p = ch.pipeline();
                p.addLast("handler", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {

                    }

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                        firstMessage.retain();
//                        firstMessage.readerIndex(0);
//                        firstMessage.retain();
                        ctx.writeAndFlush(Unpooled.wrappedBuffer(text.getBytes("utf8")));
                        ctx.close();
                    }

                    @Override
                    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                        ctx.flush();
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                        // Close the connection when an exception is raised.
//                        log.error("Unexpected exception from downstream.", cause);
                        ctx.close();
                    }
                });
            }
        }, DEFAULT_BOSS_THREAD_NUMS, DEFAULT_WORKER_THREAD_NUMS);
    }


    private final int port;
    private final ChannelInitializer<SocketChannel> initializer;
    private final int bossThreadNums;
    private final int workerThreadNums;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private Flash843(int port,
                     ChannelInitializer<SocketChannel> initializer, int bossThreadNums,
                     int workerThreadNums) {
        this.port = port;
        this.initializer = initializer;
        this.bossThreadNums = bossThreadNums;
        this.workerThreadNums = workerThreadNums;
    }

    public void start() throws Exception {
        if (bossGroup != null) {
            throw new ConnectException("不能重复启动监听");
        }
        log.info("启动端口监听！{}", port);
        bossGroup = new NioEventLoopGroup(bossThreadNums);
        workerGroup = new NioEventLoopGroup(workerThreadNums);
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(initializer);
        ChannelFuture f = b.bind(port);
        f.get();
    }

    // public void broadcast(Object obj){
    // for (Channel channel : channelList) {
    // channel.write(obj);
    // }
    // }

    public void close() throws InterruptedException, ExecutionException {
        bossGroup.shutdownGracefully().get();
        workerGroup.shutdownGracefully().get();
    }

    public int getPort() {
        return port;
    }
}
