package com.zhzx.uip.api.utils;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/27.
 */
public class PropertyUtil {
    private final static Logger log= Logger.getLogger(PropertyUtil.class);
    private static Properties props;
    static{
        loadProps();
    }
    synchronized static private void loadProps(){
        log.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
//　　　　　　　<!--第一种，通过类加载器进行获取properties文件流-->
                    in = PropertyUtil.class.getClassLoader().getResourceAsStream("api-conf.properties");
//　　　　　　  <!--第二种，通过类进行获取properties文件流-->
                    //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
                    props.load(in);
        } catch (FileNotFoundException e) {
            log.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            log.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("jdbc.properties文件流关闭出现异常");
            }
        }
        log.info("加载properties文件内容完成");
        log.info("properties文件内容：" + props);
    }
    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}
