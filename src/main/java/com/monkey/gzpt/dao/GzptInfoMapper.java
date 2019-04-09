package com.monkey.gzpt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.monkey.gzpt.pojo.GzptInfo;

public interface GzptInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GzptInfo record);

    int insertSelective(GzptInfo record);

    GzptInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzptInfo record);

    int updateByPrimaryKey(GzptInfo record);
    List < GzptInfo > selectByUserName(@Param("userName") String userName);
}