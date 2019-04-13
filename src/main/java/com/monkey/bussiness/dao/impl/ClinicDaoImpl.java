/********************************************************
 * <p>Description: 优悦口腔_诊所模块Dao实现层</p>
 * <p>Project: ClinicDaoImpl.java </p>
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

import com.monkey.bussiness.dao.ClinicDao;
import com.monkey.bussiness.dto.Clinic;
import com.monkey.bussiness.dto.MonkeyKingCode;
import com.monkey.bussiness.mapper.ClinicMapper;

@Repository
public class ClinicDaoImpl implements ClinicDao {

	@Autowired
	private ClinicMapper clinicMapper;

	/**
	  * 查询诊所信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public List<Clinic> searchClinicForList(Long clinicId, String clinicName, Integer effectiveFlag) {

		// 请求参数
		Map<String, Object> reqParam = new HashMap<String, Object>();

		if (clinicId != null) {
			reqParam.put("id", clinicId);
		}

		if (StringUtils.isNotEmpty(clinicName)) {
			reqParam.put("clinicName", clinicName);
		}

		if (effectiveFlag != null) {
			reqParam.put("effectiveFlag", effectiveFlag);
		} else {
			reqParam.put("effectiveFlag", MonkeyKingCode.EFFECTIVE_FLAG_TRUE);
		}

		
		// 查询用List
		List<Clinic> list = null;
		list = clinicMapper.searchClinic(reqParam);

		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

}
