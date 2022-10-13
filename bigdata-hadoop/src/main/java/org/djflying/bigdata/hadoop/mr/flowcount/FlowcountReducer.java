package org.djflying.bigdata.hadoop.mr.flowcount;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Flowcount Reducer
 *
 * @author daijiong
 * @version $Id: FlowcountReducer.java, v 0.1 18-8-9 下午5:04 daijiong Exp $$
 */
public class FlowcountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    /**
     * This method is called once for each key. Most applications will define
     * their reduce class by overriding this method. The default implementation
     * is an identity function.
     *
     * <183323,bean1><183323,bean2><183323,bean3><183323,bean4>.......
     *
     * @param key
     * @param values
     * @param context
     */
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long sumUpFlow = 0;
        long sumDownFlow = 0;
        //遍历所有bean，将其中的上行流量，下行流量分别累加
        for (FlowBean flowBean : values) {
            sumUpFlow += flowBean.getUpFlow() == null ? 0 : flowBean.getUpFlow();
            sumDownFlow += flowBean.getDownFlow() == null ? 0 : flowBean.getDownFlow();
        }
        context.write(key, new FlowBean(sumUpFlow, sumDownFlow));
    }
}
