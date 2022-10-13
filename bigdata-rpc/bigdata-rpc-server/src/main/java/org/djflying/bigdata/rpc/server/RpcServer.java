package org.djflying.bigdata.rpc.server;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.djflying.bigdata.rpc.common.RpcDecoder;
import org.djflying.bigdata.rpc.common.RpcEncoder;
import org.djflying.bigdata.rpc.common.RpcRequest;
import org.djflying.bigdata.rpc.common.RpcResponse;
import org.djflying.bigdata.rpc.registry.server.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 框架的RPC服务器
 * <p>
 * 用于将用户系统的业务类发布为RPC服务
 * 使用时可由用户通过spring-bean的方式注入到用户的业务系统中
 * 由于本类实现了ApplicationContextAware InitializingBean
 * spring构造本对象时会调用setApplicationContext()方法，从而可以在方法中通过自定义注解获得用户的业务接口和实现
 * 还会调用afterPropertiesSet()方法，在方法中启动netty服务器
 *
 * @author dj4817
 * @version $Id: RpcServer.java, v 0.1 2018/3/26 17:51 dj4817 Exp $$
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {

    private static final Logger LOGGER     = LoggerFactory.getLogger(RpcServer.class);

    /** zookeeper服务器地址 */
    private String              serverAddress;
    /** zookeeper服务注册器 */
    private ServiceRegistry     serviceRegistry;

    /** 用于存储业务接口和实现类的实例对象(由spring所构造) */
    private Map<String, Object> handlerMap = new HashMap();

    /**
     * 构造函数
     *
     * @param serverAddress
     */
    public RpcServer(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    /**
     * 构造函数
     *
     * @param serverAddress
     * @param serviceRegistry
     */
    public RpcServer(String serverAddress, ServiceRegistry serviceRegistry) {
        this.serverAddress = serverAddress;
        this.serviceRegistry = serviceRegistry;
    }

    /**
     * 通过注解，获取标注了rpc服务注解的业务类的----接口及impl对象，将它放到handlerMap中
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        // 获取使用RpcService注解的spring bean
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            LOGGER.info("使用RpcService注解的spring bean个数为：" + serviceBeanMap.size());
            for (Object serviceBean : serviceBeanMap.values()) {
                // 从业务实现类上的自定义注解中获取到value，用来获取到业务接口的全名
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }
    }

    /**
     *
     * 在此启动netty服务，绑定handle流水线：
     * 1、接收请求数据进行反序列化得到request对象
     * 2、根据request中的参数，让RpcHandler从handlerMap中找到对应的业务imple，调用指定方法，获取返回结果
     * 3、将业务调用结果封装到response并序列化后发往客户端
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        LOGGER.info("启动netty服务器");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline()
                            // 注册解码 IN-1
                            .addLast(new RpcDecoder(RpcRequest.class))
                            // 注册编码 OUT
                            .addLast(new RpcEncoder(RpcResponse.class))
                            //注册RpcHandler IN-2
                            .addLast(new RpcServerHandler(handlerMap));
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            // 获取服务器地址和端口
            String[] array = getServerAddress().split(":");
            String host = array[0];
            int port = Integer.parseInt(array[1]);
            // 向zookeeper服务器注册
            if (serviceRegistry != null) {
                serviceRegistry.register(getServerAddress());
            }
            ChannelFuture future = bootstrap.bind(host, port).sync();
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * Getter method for property <tt>serverAddress</tt>.
     *
     * @return property value of serverAddress
     */
    public String getServerAddress() {
        return serverAddress;
    }

    /**
     * Setter method for property <tt>serverAddress</tt>.
     *
     * @param serverAddress value to be assigned to property serverAddress
     */
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    /**
     * Getter method for property <tt>serviceRegistry</tt>.
     *
     * @return property value of serviceRegistry
     */
    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    /**
     * Setter method for property <tt>serviceRegistry</tt>.
     *
     * @param serviceRegistry value to be assigned to property serviceRegistry
     */
    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }
}
