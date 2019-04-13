package com.monkey.auth.base.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.monkey.auth.base.service.MyShiroRealm;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.config
 * @类名称： ShiroConfiguration
 * @类描述：【类描述】shiro配置
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年11月19日上午10:48:26
 */
@Configuration
public class ShiroConfiguration {
    /**
     * 
     * @方法名：myShiroRealm
     * @方法描述【方法功能描述】将自己的验证方式加入容器
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年11月20日 上午9:28:24
     * @修改人：cc
     * @修改时间：2018年11月20日 上午9:28:24
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    /**
     * 
     * @方法名：securityManager
     * @方法描述【方法功能描述】权限管理，配置主要是Realm的管理认证
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年11月20日 上午9:28:38
     * @修改人：cc
     * @修改时间：2018年11月20日 上午9:28:38
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 
     * @方法名：shiroFilterFactoryBean
     * @方法描述【方法功能描述】Filter工厂，设置对应的过滤条件和跳转条件
     * @param securityManager
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年11月20日 上午9:28:50
     * @修改人：cc
     * @修改时间：2018年11月20日 上午9:28:50
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器.
        Map < String, String > filterChainDefinitionMap = new LinkedHashMap < String, String >();
        // 配置不会被拦截的链接 顺序判断，因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**", "anon")来配置匿名访问，必须配置到每个静态目录
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/html/**", "anon");
        filterChainDefinitionMap.put("/component/**", "anon");
        // 登录页及登录接口
        filterChainDefinitionMap.put("/login.html", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "anon");
        //filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error1");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 
     * @方法名：authorizationAttributeSourceAdvisor
     * @方法描述【方法功能描述】加入注解的使用，不加入这个注解不生效
     * @param securityManager
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年11月20日 上午9:29:01
     * @修改人：cc
     * @修改时间：2018年11月20日 上午9:29:01
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 
     * @方法名：defaultAdvisorAutoProxyCreator
     * @方法描述【方法功能描述】此处配置也是为了注解生效
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年11月20日 上午9:29:19
     * @修改人：cc
     * @修改时间：2018年11月20日 上午9:29:19
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator app = new DefaultAdvisorAutoProxyCreator();
        app.setProxyTargetClass(true);
        return app;

    }
}
