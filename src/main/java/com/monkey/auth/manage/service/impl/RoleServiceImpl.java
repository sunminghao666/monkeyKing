package com.monkey.auth.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monkey.auth.manage.dao.RoleMapper;
import com.monkey.auth.manage.dao.UserRoleMapper;
import com.monkey.auth.manage.pojo.Role;
import com.monkey.auth.manage.pojo.UserRoleExample;
import com.monkey.auth.manage.service.RoleService;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Pager;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.service.impl
 * @类名称： RoleServiceImpl
 * @类描述：【类描述】角色管理
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日下午1:55:56
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public Role addRole(Role role) throws Exception {
        // 添加新增时间
        role.setInsertTime(new Date());
        roleMapper.insertSelective(role);
        return role;
    }

    @Override
    public Role findRoleById(Long id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateRole(Role role) throws Exception {
        // 添加修改时间
        role.setUpdateTime(new Date());
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRoles(List < Long > listRolesId) throws Exception {
        for (int i = 0; i < listRolesId.size(); i++) {
            roleMapper.deleteByPrimaryKey(listRolesId.get(i));
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria cr = userRoleExample.createCriteria();
            cr.andRoleCodeEqualTo(listRolesId.get(i) + "");
            userRoleMapper.deleteByExample(userRoleExample);
        }
    }

    @Override
    public Pager < Role > queryByPage(String roleCode, String roleName, int size, int pageIndex) {
        try {
            Pager < Role > p = new Pager < Role >();
            int count = roleMapper.countRole(roleName, roleCode);
            int pageCount = count % size == 0 ? count / size : (1 + count / size);

            if (pageCount == 0) {
                pageIndex = 1;
            }
            else if (pageIndex > pageCount) {
                pageIndex = pageCount;
            }
            else if (pageIndex < 1) {
                pageIndex = 1;
            }
            int startIndex = (pageIndex - 1) * size;

            List < Role > roleList = roleMapper.queryByPage(roleName, roleCode, size, startIndex);

            p.setEntities(roleList);
            p.setPageSize(size);
            p.setPageNo(pageIndex);
            p.setEntityCount(count);
            p.setPageCount(pageCount);

            return p;
        }
        catch (Exception e) {
            TAFLog.error("查询异常", e);
            return null;

        }
    }

    @Override
    public List < Role > findRoleByCondition(Role role) throws Exception {
        return roleMapper.findRoleByCondition(role);
    }

    @Override
    public List < Role > findRoleByCondition2(Role role) throws Exception {
        return roleMapper.findRoleByCondition2(role);
    }

}
