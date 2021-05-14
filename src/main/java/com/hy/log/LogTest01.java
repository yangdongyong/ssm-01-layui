package com.hy.log;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @Author shy Boy
 * @Date 2021/5/13 - 05 - 13 - 11:38
 * @Description: com.hy.log
 * @version: 1.0
 */
public class LogTest01 {
//    private static org.apache.log4j.Logger logger1=org.apache.log4j.Logger.getLogger("log4j.properties");
//    自定义输出路径
     static Logger logger = LogManager.getLogger(LogTest01.class);

    public static void main(String[] args) {
        new LogTest01().testLog();
    }

    public void testLog(){

        for (int i = 0; i <10 ; i++) {
            logger.info("自定义输出路径");
        }

    }
}
