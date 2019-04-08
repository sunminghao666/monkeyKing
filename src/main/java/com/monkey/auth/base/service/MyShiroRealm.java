package com.monkey.auth.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.monkey.auth.manage.dao.ResourcesMapper;
import com.monkey.auth.manage.dao.RoleMapper;
import com.monkey.auth.manage.dao.UserMapper;
import com.monkey.auth.manage.pojo.Resources;
import com.monkey.auth.manage.pojo.Role;
import com.monkey.auth.manage.pojo.User;
import com.monkey.auth.manage.pojo.UserExample;
import com.monkey.auth.manage.pojo.UserRoleExample;
import com.monkey.taf.common.Tools;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.service
 * @类名称： MyShiroRealm
 * @类描述：【类描述】权限认证接口实现
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年11月19日上午10:40:32
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper RoleMapper;

    @Autowired
    ResourcesMapper resourcesMapper;

    /**
     * 权限认证 (non-Javadoc)
     * 
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     * @param arg0
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // simpleAuthorizationInfo.addRole("admin");
        // return simpleAuthorizationInfo;
        // 获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        List < Role > roles = this.RoleMapper.selectByUserName(name);
        List < Resources > resources = this.resourcesMapper.findCurrRes(name);
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : roles) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
        }
        for (Resources resource : resources) {
            // 添加权限
            if(Tools.isNotBlank(resource.getUrl())){
                simpleAuthorizationInfo.addStringPermission(resource.getUrl());
            }
        }
        return simpleAuthorizationInfo;

    }

    /**
     * 登录认证 (non-Javadoc)
     * 
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     * @param arg0
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("cc", "123456".toString(),
        // getName());
        // return simpleAuthenticationInfo;
        // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        // 获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsercodeEqualTo(name);
        List < User > user = userMapper.selectByExample(userExample);
        if (user.size() == 0) {
            // 这里返回后会报出对应异常
            return null;
        }
        else {
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.get(0)
                    .getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
