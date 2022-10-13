package org.djflying.bigdata.hadoop.mr.flowcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Flowcount Mapper
 *
 * @author daijiong
 * @version $Id: FlowcountMapper.java, v 0.1 18-8-9 下午4:49 daijiong Exp $$
 */
public class FlowcountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    /**
     * Called once for each key/value pair in the input split. Most applications
     * should override this, but the default is the identity function.
     *
     * @param key
     * @param value
     * @param context
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 读取一行数据
        String line = value.toString();
        // 切分字段
        String[] split = line.split("|^|");
        if (split.length > 0) {
            // 获取手机号
            String phoneNumber = split[1];
            // 获取上行流量
            Long upFlow = Long.parseLong(split[split.length - 3]);
            // 获取下行流量w
            Long downFlow = Long.parseLong(split[split.length - 2]);
            // 数据返回
            context.write(new Text(phoneNumber), new FlowBean(upFlow, downFlow));
        }
    }
}
