package com.monkey.db.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.monkey.db.base.pojo.PwbConfig;
import com.monkey.db.base.pojo.PwbConfigExample;

@Repository
public interface PwbConfigMapper {

	List<PwbConfig> selectStatus();

	List<Map<String, Object>> testPage(
			@Param("params") Map<String, Object> params);

	List<Map<String, Object>> testLiatPage(
			@Param("params") Map<String, Object> params);

	int countPage(@Param("params") Map<String, Object> params);

	int countByExample(PwbConfigExample example);

	int deleteByExample(PwbConfigExample example);

	int deleteByPrimaryKey(Long id);

	int insert(PwbConfig record);

	int insertSelective(PwbConfig record);

	List<PwbConfig> selectByExample(PwbConfigExample example);

	PwbConfig selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") PwbConfig record,
			@Param("example") PwbConfigExample example);

	int updateByExample(@Param("record") PwbConfig record,
			@Param("example") PwbConfigExample example);

	int updateByPrimaryKeySelective(PwbConfig record);

	int updateByPrimaryKey(PwbConfig record);

}