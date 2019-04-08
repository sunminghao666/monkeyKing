package com.monkey.auth.manage.dao;

import com.monkey.auth.manage.pojo.Role;
import com.monkey.auth.manage.pojo.RoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    List < Role > selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List < Role > selectByUserName(@Param("userName") String userName);

    int countRole(@Param("roleName") String roleName, @Param("roleCode") String roleCode);

    List < Role > queryByPage(@Param("roleName") String roleName, @Param("roleCode") String roleCode,
            @Param("size") int size, @Param("startIndex") int startIndex);

    List < Role > findRoleByCondition(Role role);
    List < Role > findRoleByCondition2(Role role);
}