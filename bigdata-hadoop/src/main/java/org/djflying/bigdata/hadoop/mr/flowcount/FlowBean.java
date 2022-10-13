package org.djflying.bigdata.hadoop.mr.flowcount;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 流量实体
 *
 * @author daijiong
 * @version $Id: FlowBean.java, v 0.1 18-8-9 下午4:43 daijiong Exp $$
 */
public class FlowBean implements Writable {

    /** 上行流量 */
    private Long upFlow;
    /** 下行流量 */
    private Long downFlow;
    /** 总流量 */
    private Long sumFlow;

    /**
     * 无参构造器
     * 反序列化时，需要反射调用空参构造函数，所以要显示定义一个
     */
    public FlowBean() {
    }

    /**
     * 自定义构造器
     *
     * @param upFlow
     * @param downFlow
     */
    public FlowBean(Long upFlow, Long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    public Long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }

    /**
     * Serialize the fields of this object to <code>out</code>.
     *
     * @param out <code>DataOuput</code> to serialize this object into.
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {

        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    /**
     * Deserialize the fields of this object from <code>in</code>.
     * <p>
     * <p>For efficiency, implementations should attempt to re-use storage in the
     * existing object where possible.</p>
     *
     * @param in <code>DataInput</code> to deseriablize this object from.
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {

        long upFlow = in.readLong();
        long downFlow = in.readLong();
        long sumFlow = in.readLong();
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }
}
