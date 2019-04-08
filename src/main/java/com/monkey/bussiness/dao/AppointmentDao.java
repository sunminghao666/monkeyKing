/********************************************************
 * <p>Description: 优悦口腔_预约管理Dao</p>
 * <p>Project: AppointmentDao.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.dao;

import java.util.List;

import com.monkey.bussiness.bean.reqBean.SaveAppBean;
import com.monkey.bussiness.dto.AppointmentTime;

public interface AppointmentDao {

	/**
	  * 查询可预约时间段
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	List<AppointmentTime> searchAppTime();

	/**
	  * 保存预约信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	int saveAppOrder(SaveAppBean bean);

}
