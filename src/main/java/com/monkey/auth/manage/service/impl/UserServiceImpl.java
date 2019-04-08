package com.monkey.auth.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.auth.manage.dao.UserMapper;
import com.monkey.auth.manage.dao.UserRoleMapper;
import com.monkey.auth.manage.pojo.User;
import com.monkey.auth.manage.pojo.UserExample;
import com.monkey.auth.manage.pojo.UserRoleExample;
import com.monkey.auth.manage.service.UserService;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Pager;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.service.impl
 * @类名称： UserServiceImpl
 * @类描述：【类描述】用户管理模块
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月26日下午4:20:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User selectByUserCode(String userCode) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsercodeEqualTo(userCode);
        List < User > user = userMapper.selectByExample(userExample);
        if (user.size() > 0) {
            return user.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public Pager < User > queryByPage(String userCode, String userName, int size, int pageIndex) {
        try {
            Pager < User > p = new Pager < User >();
            int count = userMapper.countUser(userName, userCode);
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

            List < User > userList = userMapper.queryByPage(userName, userCode, size, startIndex);

            p.setEntities(userList);
            p.setPageSize(size);
            p.setPageNo(pageIndex);
            p.setEntityCount(count);
            p.setPageCount(pageCount);

            return p;
        }
        catch (Exception e) {
            TAFLog.error("分页查询异常", e);
            return null;
        }
    }

    @Override
    public User addUser(User user) throws Exception {
        user.setCreatedate(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    public User findUserById(Long id) throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public int updateUser(User user) throws Exception {
        user.setUpdatedate(new Date());
        int result = userMapper.updateByPrimaryKeySelective(user);
        return result;
    }

    @Override
    public void deleteUser(List < Long > userIdList) throws Exception {
        for (int i = 0; i < userIdList.size(); i++) {
            userMapper.deleteByPrimaryKey(userIdList.get(i));
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
            criteria.andUserCodeEqualTo(userIdList.get(i) + "");
            userRoleMapper.deleteByExample(userRoleExample);
        }
    }

    @Override
    public List < User > findUserByCondition(String userName, String userCode) throws Exception {
        return userMapper.findUserByCondition(userName, userCode);
    }

    @Override
    public List < User > findUserByCondition2(String userName, String userCode) throws Exception {
        return userMapper.findUserByCondition2(userName, userCode);
    }

}
