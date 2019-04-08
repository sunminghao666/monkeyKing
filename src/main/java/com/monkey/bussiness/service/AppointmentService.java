/********************************************************
 * <p>Description: 优悦口腔_预约管理Service</p>
 * <p>Project: AppointmentService.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.service;

import com.monkey.basic.common.ResponseResult;
import com.monkey.bussiness.bean.reqBean.SaveAppBean;

public interface AppointmentService {

	/**
	  * 查询可预约时间段
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	ResponseResult searchAppTime();

	/**
	  * 添加预约记录
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	ResponseResult saveAppointment(SaveAppBean bean);

}
