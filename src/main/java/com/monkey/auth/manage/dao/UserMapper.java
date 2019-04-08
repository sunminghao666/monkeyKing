package com.monkey.auth.manage.dao;

import com.monkey.auth.manage.pojo.User;
import com.monkey.auth.manage.pojo.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List < User > selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int countUser(@Param("userName") String userName, @Param("userCode") String userCode);

    List < User > queryByPage(@Param("userName") String userName, @Param("userCode") String userCode,
            @Param("size") int size, @Param("startIndex") int startIndex);

    List < User > findUserByCondition(@Param("userName") String userName, @Param("userCode") String userCode);

    List < User > findUserByCondition2(@Param("userName") String userName, @Param("userCode") String userCode);

}