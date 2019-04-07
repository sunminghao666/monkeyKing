/********************************************************
 * <p>Description: 优悦口腔_就诊人管理Service</p>
 * <p>Project: PatientService.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.service;

import com.monkey.bean.reqBean.SavePatientBean;
import com.monkey.common.ResponseResult;

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
