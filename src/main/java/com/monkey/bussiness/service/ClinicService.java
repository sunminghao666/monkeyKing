/********************************************************
 * <p>Description: 优悦口腔_诊所模块Service</p>
 * <p>Project: ClinicService.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.service;

import com.monkey.basic.common.ResponseResult;

public interface ClinicService {

	/**
	  * 查询诊所列表
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	ResponseResult searchClinic(Long clinicId, String clinicName, Integer effectiveFlag);

	/**
	  * 查询治疗项目
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	ResponseResult searchCureProject(Long cureProjectId, String cureProjectName, Integer effectiveFlag);

}
