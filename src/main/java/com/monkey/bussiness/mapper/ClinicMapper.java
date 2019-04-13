package com.monkey.bussiness.mapper;

import com.monkey.bussiness.dto.Clinic;

import java.util.List;
import java.util.Map;

public interface ClinicMapper {

	/**
	  * 查询诊所信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
   List<Clinic> searchClinic(Map<String, Object> reqParam);
}