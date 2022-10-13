package org.djflying.bigdata.mongodb.babymonogodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 验舱性能埋点数据
 */
@Document(collection = "BookValidatePerformances")
public class BookValidatePerformance {

    @Id
    private int    id;

    /** 是否虚舱 */
    private String priceChangeFlag;

    /** 价格是否变动 */
    private String invalidCabinFlag;

    /** unitKey */
    private String unitKey;

    /** 耗时 */
    private long   spendTime;

    /** 供应商id */
    private String supplierId;

    /** 调用时间 */
    private String callTime;

    /** traceId */
    private String traceId;

    /** 运行环境 */
    private String env;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriceChangeFlag() {
        return priceChangeFlag;
    }

    public void setPriceChangeFlag(String priceChangeFlag) {
        this.priceChangeFlag = priceChangeFlag;
    }

    public String getInvalidCabinFlag() {
        return invalidCabinFlag;
    }

    public void setInvalidCabinFlag(String invalidCabinFlag) {
        this.invalidCabinFlag = invalidCabinFlag;
    }

    public String getUnitKey() {
        return unitKey;
    }

    public void setUnitKey(String unitKey) {
        this.unitKey = unitKey;
    }

    public long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(long spendTime) {
        this.spendTime = spendTime;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
