package com.monkey.taf.io.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * 
 * @模块名：taf
 * @包名：com.tit.taf.io.properties
 * @类名称： PropertiesUtil
 * @类描述：【类描述】 Properties类型加载类
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月27日下午3:16:15
 */
public class PropertiesUtil {

    /**
     * 
     * @方法名：loadResource
     * @方法描述【方法功能描述】 加载Properties文件
     * @param path 文件路径(项目内)
     * @return Properties
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 下午3:16:45
     * @修改人：cc
     * @修改时间：2018年8月27日 下午3:16:45
     */
    public static Properties loadResource(String path) {

        Properties p = null;
        InputStream inp = PropertiesUtil.class.getResourceAsStream(path);
        if (inp != null) {
            p = new Properties();
            try {
                p.load(inp);
            }
            catch (IOException e) {
                p = null;
            }
        }
        return p;
    }

    /**
     * 
     * @方法名：loadProperties
     * @方法描述【方法功能描述】 加载Properties文件
     * @param path 绝对文件路径
     * @return Properties
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 下午3:17:08
     * @修改人：cc
     * @修改时间：2018年8月27日 下午3:17:08
     */
    public static Properties loadProperties(String path) {
        Properties p = null;
        try {
            InputStream inp = new FileInputStream(path);
            if (inp != null) {
                p = new Properties();
                p.load(inp);
            }
        }
        catch (Exception e) {
            p = null;
        }
        return p;
    }

}
