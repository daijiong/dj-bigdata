package org.djflying.bigdata.hadoop.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Wordcount Reducer
 * 
 * 1. KEYIN, VALUEIN: 对应mapper输出的KEYOUT,VALUEOUT类型对应.
 * 2. KEYOUT, VALUEOUT: 是自定义reduce逻辑处理结果的输出数据类型: KEYOUT是单词, VLAUEOUT是总次数.
 *
 * @author daijiong
 * @version $Id: WordcountReducer.java, v 0.1 18-8-8 下午5:37 daijiong Exp $$
 */
public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    /**
     * <angelababy,1><angelababy,1><angelababy,1><angelababy,1><angelababy,1>
     * <hello,1><hello,1><hello,1><hello,1><hello,1><hello,1>
     * <banana,1><banana,1><banana,1><banana,1><banana,1><banana,1>
     * 入参key，是一组相同单词kv对的key
     *
     * @param key
     * @param values
     * @param context
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        /*Iterator<IntWritable> iterator = values.iterator();
        while(iterator.hasNext()){
        	count += iterator.next().get();
        }*/
        for (IntWritable value : values) {

            count += value.get();
        }
        context.write(key, new IntWritable(count));
    }
}
