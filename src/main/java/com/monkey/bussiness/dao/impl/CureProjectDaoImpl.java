/********************************************************
 * <p>Description: 优悦口腔_治疗项目Dao实现层</p>
 * <p>Project: CureProjectDaoImpl.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monkey.bussiness.dao.CureProjectDao;
import com.monkey.bussiness.dto.CureProject;
import com.monkey.bussiness.dto.MonkeyKingCode;
import com.monkey.bussiness.mapper.CureProjectMapper;

@Repository
public class CureProjectDaoImpl implements CureProjectDao {

	@Autowired
	private CureProjectMapper cureProjectMapper;

	/**
	  *  查询治疗项目
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public CureProject searchCureProjectForDto(Long cureProjectId) {

		// 请求参数
		Map<String, Object> reqParam = new HashMap<String, Object>();
		reqParam.put("id", cureProjectId);

		// 查询用List
		List<CureProject> list = null;
		list = cureProjectMapper.searchCureProject(reqParam);

		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	/**
	  * 查询治疗项目
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public List<CureProject> searchCureProjectForList(Long cureProjectId, String cureProjectName, Integer effectiveFlag) {

		// 请求参数
		Map<String, Object> reqParam = new HashMap<String, Object>();

		if (cureProjectId != null) {
			reqParam.put("id", cureProjectId);
		}

		if (StringUtils.isNotEmpty(cureProjectName)) {
			reqParam.put("cureProjectName", cureProjectName);
		}

		if (effectiveFlag != null) {
			reqParam.put("effectiveFlag", effectiveFlag);
		} else {
			reqParam.put("effectiveFlag", MonkeyKingCode.EFFECTIVE_FLAG_TRUE);
		}

		
		// 查询用List
		List<CureProject> list = null;
		list = cureProjectMapper.searchCureProject(reqParam);

		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

}
