package com.monkey.auth.manage.service;

import java.util.List;

import com.monkey.auth.manage.pojo.UserRole;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.service
 * @类名称： UserRoleService
 * @类描述：【类描述】用户角色管理
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日上午11:08:19
 */
public interface UserRoleService {

    /**
     * 查看当前用户下的所有角色
     * 
     * @param userCode
     * @return
     * @throws Exception
     */
    public List < UserRole > findUserRolesByUserCode(String userCode) throws Exception;

    /**
     * 维护用户角色关系
     * 
     * @param userId
     * @param checked
     * @param unChecked
     * @throws Exception
     */
    public void updateUserRole(String userId, String checked, String unChecked) throws Exception;
}
