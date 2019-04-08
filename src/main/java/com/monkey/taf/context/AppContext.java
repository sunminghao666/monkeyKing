package com.monkey.taf.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.context
 * @类名称： AppContext
 * @类描述：【类描述】持有Spring的beanFactory，提供获取Spring Context中bean的方法
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年9月19日上午9:57:11
 */
public class AppContext {

    private static BeanFactory beanFactory = null;

    public static void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AppContext.beanFactory = beanFactory;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 
     * @方法名：getBean
     * @方法描述【方法功能描述】根据bean名称获取bean
     * @param name bean名称
     * @param requireClass bean类型
     * @return bean
     * @throws ClassCastException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:57:33
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:57:33
     */
    @SuppressWarnings("unchecked")
    public static < T > T getBean(String name, Class < T > requireClass) throws ClassCastException {
        if (beanFactory == null)
            return null;
        return (T) AppContext.beanFactory.getBean(name, requireClass);
    }

    public static Object getBean(String name) {
        if (beanFactory == null)
            return null;
        return AppContext.beanFactory.getBean(name);
    }
}
