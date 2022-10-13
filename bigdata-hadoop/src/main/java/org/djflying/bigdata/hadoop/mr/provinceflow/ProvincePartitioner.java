package org.djflying.bigdata.hadoop.mr.provinceflow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.djflying.bigdata.hadoop.mr.flowcount.FlowBean;

import java.util.HashMap;

/**
 * 自定义Partitioner
 *
 * @author daijiong
 * @version $Id: ProvincePartitioner.java, v 0.1 18-8-16 上午10:28 daijiong Exp $$
 */
public class ProvincePartitioner extends Partitioner<Text, FlowBean> {

    private static HashMap<String, Integer> provinceDict = new HashMap<>();
    static {
        provinceDict.put("136", 0);
        provinceDict.put("137", 1);
        provinceDict.put("138", 2);
        provinceDict.put("139", 3);
    }

    /**
     * Get the partition number for a given key (hence record) given the total
     * number of partitions i.e. number of reduce-tasks for the job.
     * <p>
     * <p>Typically a hash function on a all or a subset of the key.</p>
     *
     * @param text          the key to be partioned.
     * @param flowBean      the entry value.
     * @param numPartitions the total number of partitions.
     * @return the partition number for the <code>key</code>.
     */
    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        // 获取手机号前三位
        String phonePrefix = text.toString().substring(0, 3);
        // 根据手机号获取归属地省份
        Integer provinceId = provinceDict.get(phonePrefix);
        // 返回分区号，即省份id
        return provinceId != null ? provinceId : 4;
    }
}
