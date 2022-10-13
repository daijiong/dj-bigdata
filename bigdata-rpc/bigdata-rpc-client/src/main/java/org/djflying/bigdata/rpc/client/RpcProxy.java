//package org.djflying.bigdata.rpc.client;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.UUID;
//
//import org.djflying.bigdata.rpc.common.RpcRequest;
//import org.djflying.bigdata.rpc.common.RpcResponse;
//import org.djflying.bigdata.rpc.registry.client.ServiceDiscovery;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * rpc代理
// *
// * @author daijiong
// * @version $Id: RpcProxy.java, v 0.1 18-10-16 下午4:23 daijiong Exp $$
// */
//public class RpcProxy {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RpcProxy.class);
//
//    private String              serviceAddress;
//
//    /** 服务发现类 */
//    private ServiceDiscovery    serviceDiscovery;
//
//    public RpcProxy() {
//    }
//
//    public RpcProxy(ServiceDiscovery serviceDiscovery) {
//        this.serviceDiscovery = serviceDiscovery;
//    }
//
//    /**
//     * 创建代理对象
//     *
//     * @param interfaceClass
//     * @param <T>
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public <T> T create(Class<?> interfaceClass) {
//
//        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), interfaceClass.getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//                // 1.创建RpcRequest，封装被代理类的属性
//                RpcRequest request = new RpcRequest();
//                request.setRequestId(UUID.randomUUID().toString());
//                request.setInterfaceName(method.getDeclaringClass().getName());
//                request.setParameterTypes(method.getParameterTypes());
//                request.setParameters(args);
//                // 2.查找服务
//                if (serviceDiscovery != null) {
//                    serviceAddress = serviceDiscovery.discover();
//                }
//                // 3.随机获取服务的地址
//                String[] array = serviceAddress.split(":");
//                String hostName = array[0];
//                int port = Integer.parseInt(array[1]);
//                // 4.创建Netty实现的RpcClient，连接服务端
//                RpcClient rpcClient = new RpcClient(hostName, port);
//                // 5.通过netty向服务端发送请求
//                RpcResponse response = rpcClient.send(request);
//                // 6.处理返回信息
//                if (response.isError()) {
//                    throw response.getError();
//                } else {
//                    return response.getResult();
//                }
//            }
//        });
//    }
//
//    public ServiceDiscovery getServiceDiscovery() {
//        return serviceDiscovery;
//    }
//
//    public void setServiceDiscovery(ServiceDiscovery serviceDiscovery) {
//        this.serviceDiscovery = serviceDiscovery;
//    }
//
//    public String getServiceAddress() {
//        return serviceAddress;
//    }
//
//    public void setServiceAddress(String serviceAddress) {
//        this.serviceAddress = serviceAddress;
//    }
//}
