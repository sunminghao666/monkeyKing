package com.monkey.bussiness.mapper;

import com.monkey.bussiness.dto.CureProject;

import java.util.List;
import java.util.Map;


public interface CureProjectMapper {

	/**
	  *  查询治疗项目
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
   List<CureProject> searchCureProject(Map<String, Object> reqParam);

}