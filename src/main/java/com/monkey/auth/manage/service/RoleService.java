package com.monkey.auth.manage.service;

import java.util.List;

import com.monkey.auth.manage.pojo.Role;
import com.monkey.taf.web.util.Pager;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.service
 * @类名称： RoleService
 * @类描述：【类描述】角色管理
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日上午11:08:55
 */
public interface RoleService {

    /**
     * 
     * @方法名：addRole
     * @方法描述【方法功能描述】新增角色
     * @param role
     * @return
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2019年2月27日 上午11:12:29
     * @修改人：cc
     * @修改时间：2019年2月27日 上午11:12:29
     */
    public Role addRole(Role role) throws Exception;

    /**
     * 根据编码，名称查询角色
     * 
     * @param role
     * @return
     * @throws Exception
     */
    public List < Role > findRoleByCondition(Role role) throws Exception;
    public List < Role > findRoleByCondition2(Role role) throws Exception;
    /**
     * 分页查询角色
     * 
     * @param roleCode
     * @param roleName
     * @param size
     * @param pageIndex
     * @return
     * @throws ServiceException
     */
    public Pager < Role > queryByPage(String roleCode, String roleName, int size, int pageIndex);

    /**
     * 根据Id查询角色
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Role findRoleById(Long id) throws Exception;

    /**
     * 更新角色
     * 
     * @param Role
     * @return
     * @throws Exception
     */
    public int updateRole(Role Role) throws Exception;

    /**
     * 角色删除
     * 
     * @param listRolesId
     * @return
     * @throws Exception
     */
    public void deleteRoles(List < Long > listRolesId) throws Exception;
}
