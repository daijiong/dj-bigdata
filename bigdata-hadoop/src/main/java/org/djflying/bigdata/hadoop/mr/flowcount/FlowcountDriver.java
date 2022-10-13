package org.djflying.bigdata.hadoop.mr.flowcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Flowcount Driver
 *
 * @author daijiong
 * @version $Id: FlowcountDriver.java, v 0.1 18-8-10 下午3:06 daijiong Exp $$
 */
public class FlowcountDriver {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        if (args == null || args.length == 0) {
            args = new String[2];
            args[0] = "hdfs://centos0:9000/flowcount/input";
            args[1] = "hdfs://centos0:9000/flowcount/output";
        }

        Configuration conf = new Configuration();
        //        conf.set("HADOOP_USER_NAME", "hadoop");
        //        conf.set("dfs.permissions.enabled", "false");
        // 设置YARN集群信息
        //        conf.set("mapreduce.framework.name", "yarn");
        //        conf.set("yarn.resoucemanager.hostname", "centos0");

        Job job = Job.getInstance(conf);
        /*job.setJar("/home/hadoop/wc.jar");*/
        //指定本程序的jar包所在的本地路径
        job.setJarByClass(FlowcountDriver.class);
        //指定本业务job要使用的mapper/Reducer业务类
        job.setMapperClass(FlowcountMapper.class);
        job.setReducerClass(FlowcountReducer.class);
        //指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //指定最终输出的数据的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        //指定job的输出结果所在目录
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //将job中配置的相关参数，以及job所用的java类所在的jar包，提交给yarn去运行
        /*job.submit();*/
        boolean res = job.waitForCompletion(true);
        System.exit(res ? 0 : 1);
    }
}
