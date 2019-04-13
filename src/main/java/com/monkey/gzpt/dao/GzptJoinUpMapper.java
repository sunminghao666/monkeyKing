package com.monkey.gzpt.dao;

import com.monkey.gzpt.pojo.GzptJoinUp;
import com.monkey.gzpt.pojo.GzptJoinUpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GzptJoinUpMapper {
    int countByExample(GzptJoinUpExample example);

    int deleteByExample(GzptJoinUpExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GzptJoinUp record);

    int insertSelective(GzptJoinUp record);

    List<GzptJoinUp> selectByExample(GzptJoinUpExample example);

    GzptJoinUp selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GzptJoinUp record, @Param("example") GzptJoinUpExample example);

    int updateByExample(@Param("record") GzptJoinUp record, @Param("example") GzptJoinUpExample example);

    int updateByPrimaryKeySelective(GzptJoinUp record);

    int updateByPrimaryKey(GzptJoinUp record);
}