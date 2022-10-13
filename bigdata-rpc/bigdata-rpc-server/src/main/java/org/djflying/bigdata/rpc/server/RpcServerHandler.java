package org.djflying.bigdata.rpc.server;

import java.lang.reflect.Method;
import java.util.Map;

import org.djflying.bigdata.rpc.common.RpcRequest;
import org.djflying.bigdata.rpc.common.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理具体的业务调用
 *
 * 通过构造时传入的“业务接口及实现”handlerMap，来调用客户端所请求的业务方法
 * 并将业务方法返回值封装成response对象写入下一个handler（即编码handler——RpcEncoder）
 *
 * @author dj4817
 * @version $Id: RpcServerHandler.java, v 0.1 2018/3/27 16:45 dj4817 Exp $$
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServerHandler.class);

    private Map<String, Object> handlerMap;

    public RpcServerHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest rpcRequest) throws Exception {

        RpcResponse rpcResponse = new RpcResponse();
        try {
            rpcResponse.setRequestId(rpcRequest.getRequestId());
            Object result = handle(rpcRequest);
            rpcResponse.setResult(result);
        } catch (Throwable e) {
            rpcResponse.setError(e);
        }
        //写入 outbundle（即RpcEncoder）进行下一步处理（即编码）后发送到channel中给客户端
        ctx.writeAndFlush(rpcResponse).addListener(ChannelFutureListener.CLOSE);

    }

    /**
     * 业务处理
     * 调用是通过反射的方式来完成
     *
     * @param rpcRequest
     * @return
     * @throws Throwable
     */
    private Object handle(RpcRequest rpcRequest) throws Throwable {

        // 获取要调用的接口
        String interfaceName = rpcRequest.getInterfaceName();
        // 获取要调用的方法名称
        String methodName = rpcRequest.getMethodName();
        // 获取要调用的方法参数
        Object[] parameters = rpcRequest.getParameters();
        // 获取要调用的方法参数类型
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        // 拿到实现类对象
        Object serviceBean = handlerMap.get(interfaceName);
        // 反射获取接口类
        Class<?> forName = Class.forName(interfaceName);
        // 反射获取方法
        Method method = forName.getMethod(methodName, parameterTypes);
        // 调用目标方法
        Object invoke = method.invoke(serviceBean, parameters);
        // 返回结果
        return invoke;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
