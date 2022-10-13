package org.djflying.bigdata.hadoop;

import java.net.URI;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * HDFS Client Tester
 *
 * @author dj4817
 * @version $Id: HdfsClientTest.java, v 0.1 2018/8/1 15:48 dj4817 Exp $$
 */
public class HdfsClientTest {

    private FileSystem    fileSystem;
    private Configuration configuration;

    @Before
    public void setUp() throws Exception {
        configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://centos0:9000");

        // 拿到一个文件系统操作的客户端实例对象
        //fileSystem = FileSystem.get(configuration);
        // 可以直接传入uri和用户身份，这样启动的时候就不会配置-DHADOOP_USER_NAME了
        fileSystem = FileSystem.get(new URI("hdfs://centos0:9000"), configuration, "hadoop"); //最后一个参数为用户名
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 测试Configuration配置文件，默认加载服务器配置文件
     *
     * @throws Exception
     */
    @Test
    public void testConf() throws Exception {
        Iterator<Map.Entry<String, String>> iterator = configuration.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            // 打印输出Configuration加载的内容
            System.out.println(next.getKey() + "|" + next.getValue());
        }
    }

    /**
     * 测试上传文件到HDFS中
     *
     * @throws Exception
     */
    @Test
    public void testUpload() throws Exception {
        Path src = new Path("/home/daijiong/.viminfo");
        Path dst = new Path("/aa");
        fileSystem.copyFromLocalFile(src, dst);
        fileSystem.close();
    }

    /**
     * 测试从HDFS中下载文件
     *
     * @throws Exception
     */
    @Test
    public void testDownload() throws Exception {
        Path src = new Path("/hadoop-2.6.4.tar.gz");
        Path dst = new Path("/home/daijiong/");
        fileSystem.copyToLocalFile(src, dst);
        fileSystem.close();
    }

    /**
     * 测试在HDFS上创建目录
     *
     * @throws Exception
     */
    @Test
    public void testMkdir() throws Exception {
        Path dst = new Path("/testmkdirs/aa/bb");
        boolean result = fileSystem.mkdirs(dst);
        Assert.assertTrue(result);
    }

    /**
     * 测试在HDFS上删除目录和文件
     * 
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        Path dst = new Path("/testmkdirs");
        // 级联删除testmkdirs目录和下面的所有内容
        boolean result = fileSystem.delete(dst, true);
        Assert.assertTrue(result);
    }

    /**
     * 递归列出指定目录下所有子文件夹中的文件信息
     *
     * @throws Exception
     */
    @Test
    public void testList() throws Exception {
        Path dst = new Path("/aa/bb/cc");
        // 递归查询根目录下所有文件
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(dst, true);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println("------------------");
            System.out.println("文件信息：");
            System.out.println("Path：" + fileStatus.getPath().getParent());
            System.out.println("Name：" + fileStatus.getPath().getName());
            System.out.println("owner：" + fileStatus.getOwner());
            System.out.println("Replication：" + fileStatus.getReplication());
            System.out.println("Permission：" + fileStatus.getPermission());
            System.out.println("块大小：" + fileStatus.getBlockSize());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println("块数量：" + blockLocations.length);
            for (int i = 0; i < blockLocations.length; i++) {
                BlockLocation b = blockLocations[i];
                System.out.println("第" + (i + 1) + "块起始偏移量: " + b.getOffset());
                System.out.println("第" + (i + 1) + "块长度:" + b.getLength());
                System.out.println("第" + (i + 1) + "块所在DataNode为：");
                // 获取块所在的datanode节点
                String[] datanodes = b.getHosts();
                for (String dn : datanodes) {
                    System.out.println(dn);
                }
            }
            System.out.println("------------------");
        }
    }

    /**
     * 判断是否为目录
     *
     * @throws Exception
     */
    @Test
    public void testList2() throws Exception {
        Path dst = new Path("/");
        FileStatus[] fileStatuses = fileSystem.listStatus(dst);
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(dst + "目录信息");
            System.out.println("Name: " + fileStatus.getPath().getName());
            System.out.println("isDirectory: " + fileStatus.isDirectory());
        }
    }
}
