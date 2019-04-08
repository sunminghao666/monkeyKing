package com.monkey.auth.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.auth.manage.dao.UserRoleMapper;
import com.monkey.auth.manage.pojo.UserRole;
import com.monkey.auth.manage.pojo.UserRoleExample;
import com.monkey.auth.manage.service.UserRoleService;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.service.impl
 * @类名称： UserRoleServiceImpl
 * @类描述：【类描述】用户角色管理
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日下午2:10:59
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List < UserRole > findUserRolesByUserCode(String userCode) throws Exception {
        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria cr = userRoleExample.createCriteria();
        cr.andUserCodeEqualTo(userCode);
        return userRoleMapper.selectByExample(userRoleExample);
    }

    @Override
    public void updateUserRole(String userId, String checked, String unChecked) throws Exception {

        String[] checkedArray = checked.split(",");
        String[] unCheckedArray = unChecked.split(",");
        UserRole userRole = new UserRole();
        // 选中处理
        for (int i = 0; i < checkedArray.length; i++) {
            // 查询id是否存在
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria cr = userRoleExample.createCriteria();
            cr.andUserCodeEqualTo(userId);
            cr.andRoleCodeEqualTo(checkedArray[i]);
            List < UserRole > u = userRoleMapper.selectByExample(userRoleExample);
            if (u.size() == 0) {
                userRole.setUserCode(userId);
                userRole.setRoleCode(checkedArray[i]);
                userRoleMapper.insertSelective(userRole);
            }
        }
        // 未选中处理
        for (int i = 0; i < unCheckedArray.length; i++) {
            // 查询id是否存在,存在的id直接删除表记录，不存在的id不管
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria cr = userRoleExample.createCriteria();
            cr.andUserCodeEqualTo(userId);
            cr.andRoleCodeEqualTo(unCheckedArray[i]);
            this.userRoleMapper.deleteByExample(userRoleExample);
        }
    }
}