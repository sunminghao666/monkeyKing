package com.monkey.taf.dataStructure;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Status;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.dataStructure
 * @类名称： BeanToMapUtil
 * @类描述：【类描述】 bean跟map之前的转换
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年7月31日下午5:11:02
 */
public class BeanToMapUtil {
    /**
     * 
     * @方法名：mapToObject
     * @方法描述【方法功能描述】使用Introspector进行转换map转换成实体
     * @param map 需要转换的map
     * @param beanClass 需要转换成实体类型
     * @return 转换后的实体
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月31日 下午5:15:12
     * @修改人：cc
     * @修改时间：2018年7月31日 下午5:15:12
     */
    public static Object mapToObject(Map < String, Object > map, Class < ? > beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }

    /**
     * 
     * @方法名：objectToMap
     * @方法描述【方法功能描述】将oject转换为map形式
     * @param obj 需要转换的object
     * @return 返回转换后的map
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月31日 下午5:22:24
     * @修改人：cc
     * @修改时间：2018年7月31日 下午5:22:24
     */
    public static Map < Object, Object > objectToMap(Object obj) throws Exception {
        if (obj == null)
            return null;

        Map < Object, Object > map = new HashMap < Object, Object >();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

    public static void main(String[] args) {
    }
}
