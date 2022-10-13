package org.djflying.bigdata.rpc.common;

/**
 * rpc调用返回参数
 *
 * @author daijiong
 * @version $Id: RpcResponse.java, v 0.1 18-10-12 下午4:27 daijiong Exp $$
 */
public class RpcResponse {

    /** 请求id */
    private String    requestId;
    /** 异常信息 */
    private Throwable error;
    /** 结果对象 */
    private Object    result;

    public RpcResponse() {
    }

    public boolean isError() {
        return error != null;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
