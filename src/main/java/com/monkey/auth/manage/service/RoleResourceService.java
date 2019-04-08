package com.monkey.auth.manage.service;

import java.util.List;

import com.monkey.auth.manage.pojo.RoleResource;
import com.monkey.auth.manage.pojo.ZtreeDTO;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.service
 * @类名称： RoleResourceService
 * @类描述：【类描述】角色资源管理
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日上午11:12:08
 */
public interface RoleResourceService {

    /**
     * 查看当前角色下的所有资源
     * 
     * @param userCode
     * @return
     * @throws Exception
     */
    public List < RoleResource > findRoleResourceByRoleCode(String roleCode) throws Exception;

    List < ZtreeDTO > findResourcesByRoleCode(String roleCode) throws Exception;

    /**
     * 维护角色资源关系
     * 
     * @param roleId
     * @param checked
     * @param unChecked
     * @throws Exception
     */
    public void updateRoleResource(String roleId, String checked, String unChecked) throws Exception;
}
