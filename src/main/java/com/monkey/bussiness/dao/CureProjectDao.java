/********************************************************
 * <p>Description: 优悦口腔_标签管理Dao</p>
 * <p>Project: TagDao.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.dao;

import java.util.List;

import com.monkey.bussiness.dto.CureProject;

public interface CureProjectDao {

	/**
	  * 查询治疗项目
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	CureProject searchCureProjectForDto(Long cureProjectId);

	/**
	  * 查询治疗项目
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	List<CureProject> searchCureProjectForList(Long cureProjectId, String cureProjectName, Integer effectiveFlag);
}
