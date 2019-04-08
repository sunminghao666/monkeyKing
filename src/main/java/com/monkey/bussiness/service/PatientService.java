/********************************************************
 * <p>Description: 优悦口腔_就诊人管理Service</p>
 * <p>Project: PatientService.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.service;

import com.monkey.basic.common.ResponseResult;
import com.monkey.bussiness.bean.reqBean.SavePatientBean;

public interface PatientService {

	/**
	  * 添加就诊人
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	ResponseResult savePatient(SavePatientBean bean);

	/**
	  * 查询用户就诊人
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	ResponseResult searchUserPatient(Long userId);
}
