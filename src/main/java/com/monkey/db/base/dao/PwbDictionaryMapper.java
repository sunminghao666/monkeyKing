package com.monkey.db.base.dao;

import com.monkey.db.base.pojo.PwbDictionary;
import com.monkey.db.base.pojo.PwbDictionaryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PwbDictionaryMapper {
    int countByExample(PwbDictionaryExample example);

    int deleteByExample(PwbDictionaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PwbDictionary record);

    int insertSelective(PwbDictionary record);

    List<PwbDictionary> selectByExample(PwbDictionaryExample example);

    PwbDictionary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PwbDictionary record, @Param("example") PwbDictionaryExample example);

    int updateByExample(@Param("record") PwbDictionary record, @Param("example") PwbDictionaryExample example);

    int updateByPrimaryKeySelective(PwbDictionary record);

    int updateByPrimaryKey(PwbDictionary record);
    
    List<PwbDictionary>  selectByType(@Param("dicType") String dicType);
}