package org.djflying.bigdata.hadoop.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * 生成日志文件程序
 *
 * @author daijiong
 * @version $Id: GenerateLog.java, v 0.1 18-8-7 下午5:21 daijiong Exp $$
 */
public class GenerateLog {

    public static void main(String[] args) throws Exception {
        Logger logger = LogManager.getLogger("testlog");
        int i = 0;
        while (true) {
            logger.info(new Date().toString() + "-----------------------------");
            i++;
            Thread.sleep(500);
            if (i > 1000000) {
                break;
            }
        }
    }
}
