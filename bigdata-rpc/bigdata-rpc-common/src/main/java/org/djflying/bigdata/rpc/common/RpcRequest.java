package org.djflying.bigdata.rpc.common;

/**
 * rpc调用请求参数
 *
 * @author dj4817
 * @version $Id: RpcRequest.java, v 0.1 2018/3/27 16:16 dj4817 Exp $$
 */
public class RpcRequest {

    /** 请求id */
    private String     requestId;
    /** 接口名称 */
    private String     interfaceName;
    /** 方法名称 */
    private String     methodName;
    /** 方法参数类型 */
    private Class<?>[] parameterTypes;
    /** 方法参数 */
    private Object[]   parameters;

    public RpcRequest() {
    }

    public RpcRequest(String requestId, String interfaceName, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        this.requestId = requestId;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
    }

    /**
     * Getter method for property <tt>requestId</tt>.
     *
     * @return property value of requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Setter method for property <tt>requestId</tt>.
     *
     * @param requestId value to be assigned to property requestId
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Getter method for property <tt>interfaceName</tt>.
     *
     * @return property value of interfaceName
     */
    public String getInterfaceName() {
        return interfaceName;
    }

    /**
     * Setter method for property <tt>interfaceName</tt>.
     *
     * @param interfaceName value to be assigned to property interfaceName
     */
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    /**
     * Getter method for property <tt>methodName</tt>.
     *
     * @return property value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter method for property <tt>methodName</tt>.
     *
     * @param methodName value to be assigned to property methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Getter method for property <tt>parameterTypes</tt>.
     *
     * @return property value of parameterTypes
     */
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    /**
     * Setter method for property <tt>parameterTypes</tt>.
     *
     * @param parameterTypes value to be assigned to property parameterTypes
     */
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    /**
     * Getter method for property <tt>parameters</tt>.
     *
     * @return property value of parameters
     */
    public Object[] getParameters() {
        return parameters;
    }

    /**
     * Setter method for property <tt>parameters</tt>.
     *
     * @param parameters value to be assigned to property parameters
     */
    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
