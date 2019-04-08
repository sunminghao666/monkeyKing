package com.monkey.basic.base.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.monkey.db.base.dao.PwbConfigMapper;
import com.monkey.db.base.pojo.PwbConfig;
import com.monkey.db.base.pojo.PwbConfigExample;
import com.monkey.taf.common.Tools;
import com.monkey.taf.log.TAFLog;

/**
 * 
 * @模块名：module_basic
 * @包名：com.tit.basic.base.component
 * @类名称： ConfigInfo
 * @类描述：【类描述】公共参数配置中心
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年7月26日下午4:58:02
 */
@Component("configInfo")
public class ConfigInfo {
    @Autowired
    PwbConfigMapper pwbConfigMapper;

    public static Map < String, String > map = new HashMap < String, String >();

    /**
     * 
     * @方法名：getByProperties
     * @方法描述【方法功能描述】获取参数
     * @param name 键
     * @return 值
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月26日 下午4:58:48
     * @修改人：cc
     * @修改时间：2018年7月26日 下午4:58:48
     */
    public static String getByProperties(String name) {
        return map.get(name);
    }

    /**
     * 
     * @方法名：loadResource
     * @方法描述【方法功能描述】同步参数
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月26日 下午4:59:45
     * @修改人：cc
     * @修改时间：2018年7月26日 下午4:59:45
     */
    @Scheduled(initialDelay = 1000, fixedRate = 60*1000)
    public void loadResource() {
        TAFLog.info("加载系统配置+333");
        PwbConfigExample pwbConfigExample = new PwbConfigExample();
        pwbConfigExample.createCriteria().andStatusEqualTo("1");
        List < PwbConfig > pwbConfigs = pwbConfigMapper.selectByExample(pwbConfigExample);
        map.clear();
        for (int i = 0; i < pwbConfigs.size(); i++) {
            map.put(pwbConfigs.get(i).getConfigCode(), pwbConfigs.get(i).getConfigValue());
        }
        TAFLog.info(map.toString());
    }

    public final static String creatOrderNo() {
        String no = Tools.seveningDate();
        return no.substring(2, no.length()) + Tools.createRandomCode(5);
    }

}
