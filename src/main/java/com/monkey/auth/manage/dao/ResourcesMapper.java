package com.monkey.auth.manage.dao;

import com.monkey.auth.manage.pojo.Resources;
import com.monkey.auth.manage.pojo.ResourcesExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ResourcesMapper {
    int countByExample(ResourcesExample example);

    int deleteByExample(ResourcesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Resources record);

    int insertSelective(Resources record);

    List < Resources > selectByExample(ResourcesExample example);

    Resources selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Resources record, @Param("example") ResourcesExample example);

    int updateByExample(@Param("record") Resources record, @Param("example") ResourcesExample example);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);

    public List < Resources > queryByPage(@Param("resourceCode") String resourceCode,
            @Param("resourceName") String resourceName, @Param("size") int size, @Param("startIndex") int startIndex);

    int countResource(@Param("resourceCode") String resourceCode, @Param("resourceName") String resourceName);

    List < Resources > findResourcesByCondition(Resources resource);
    List < Resources > findResourcesByCondition2(Resources resource);
    List < Resources > findCurrUserAuthRes(Long id);

    List < Resources > findCurrRes(@Param("userName") String userName);
}