package com.monkey.auth.manage.service;

import java.util.Dictionary;
import java.util.List;

import com.monkey.auth.manage.pojo.User;
import com.monkey.taf.web.util.Pager;

public interface UserService {

    User selectByUserCode(String userCode);

    /**
     * 条件查询用户
     * 
     * @param user
     * @return
     * @throws Exception
     */
    List < User > findUserByCondition(String userName, String userCode) throws Exception;

    List < User > findUserByCondition2(String userName, String userCode) throws Exception;

    /**
     * 分页查询
     * 
     * @param userCode
     * @param userName
     * @param size
     * @param pageIndex
     * @return
     * @throws ServiceException
     */
    public Pager < User > queryByPage(String userCode, String userName, int size, int pageIndex);

    /**
     * 
     * @方法名：addUser
     * @方法描述【方法功能描述】新增用户管理
     * @param user
     * @return
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2019年2月27日 上午11:12:48
     * @修改人：cc
     * @修改时间：2019年2月27日 上午11:12:48
     */
    public User addUser(User user) throws Exception;

    /**
     * 根据Id查询用户
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public User findUserById(Long id) throws Exception;

    /**
     * 根据Id更新用户
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public int updateUser(User user) throws Exception;

    /**
     * 删除用户
     * 
     * @param userIdList
     * @return
     * @throws Exception
     */
    public void deleteUser(List < Long > userIdList) throws Exception;

}
