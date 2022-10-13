package org.djflying.bigdata.hadoop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * HDFS stream access tester
 *
 * 用流的方式来操作HDFS上的文件
 * 可以实现读取指定偏移量范围的数据
 *
 * @author daijiong
 * @version $Id: HdfsStreamAccessTess.java, v 0.1 18-8-6 下午2:36 daijiong Exp $$
 */
public class HdfsStreamAccessTess {

    private FileSystem    fileSystem;
    private Configuration configuration;

    @Before
    public void setUp() throws Exception {
        configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://centos0:9000");
        // 拿到一个文件系统操作的客户端实例对象
        //f ileSystem = FileSystem.get(configuration);
        // 可以直接传入uri和用户身份，这样启动的时候就不会配置-DHADOOP_USER_NAME了
        fileSystem = FileSystem.get(new URI("hdfs://centos0:9000"), configuration, "hadoop"); //最后一个参数为用户名
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 通过流的方式上传文件
     *
     * @throws Exception
     */
    @Test
    public void testUpload() throws Exception {
        Path dst = new Path("/imsettings.log1");
        FSDataOutputStream outputStream = fileSystem.create(dst, true);
        InputStream inputStream = new FileInputStream("/home/daijiong/.imsettings.log");
        int copy = IOUtils.copy(inputStream, outputStream);
        Assert.assertNotEquals(-1, copy);
    }

    /**
     * 通过流的方式下载文件
     *
     * @throws Exception
     */
    @Test
    public void testDownload() throws Exception {
        Path src = new Path("/imsettings.log1");
        FSDataInputStream inputStream = fileSystem.open(src);
        OutputStream outputStream = new FileOutputStream("/home/daijiong/imsettings.log1");
        // 从12个字符之后开始读
        inputStream.seek(12);
        int copy = IOUtils.copy(inputStream, outputStream);
        Assert.assertNotEquals(-1, copy);
    }

    /**
     * 通过流的方式查看文件
     *
     * @throws Exception
     */
    @Test
    public void testCat() throws Exception {
        Path src = new Path("/imsettings.log1");
        FSDataInputStream inputStream = fileSystem.open(src);
        int copy = IOUtils.copy(inputStream, System.out);
        Assert.assertNotEquals(-1, copy);
    }
}
