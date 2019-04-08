/********************************************************
 * <p>Description: 优悦口腔_预约管理Dao实现层</p>
 * <p>Project: AppointmentDaoImpl.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monkey.bussiness.bean.reqBean.SaveAppBean;
import com.monkey.bussiness.dao.AppointmentDao;
import com.monkey.bussiness.dto.AppointmentOrder;
import com.monkey.bussiness.dto.AppointmentTime;
import com.monkey.bussiness.dto.MonkeyKingCode;
import com.monkey.bussiness.mapper.AppointmentOrderMapper;
import com.monkey.bussiness.mapper.AppointmentTimeMapper;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {

	@Autowired
	private AppointmentTimeMapper appointmentTimeMapper;

	@Autowired
	private AppointmentOrderMapper appointmentOrderMapper;

	/**
	  * 查询可预约时间段
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public List<AppointmentTime> searchAppTime() {

		// 查询用List
		List<AppointmentTime> list = null;
		list = appointmentTimeMapper.searchAppTime();

		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

	/**
	  * 保存预约信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public int saveAppOrder(SaveAppBean bean) {

		// 当前时间
		Date date = new Date();

		// 预约记录保存
		AppointmentOrder order = new AppointmentOrder();
		// 创建时间
		order.setGmtCreate(date);
		// 修改时间
		order.setGmtModify(date);
		// 用户id
		order.setUserId(bean.getUserId());
		// 就诊人id
		order.setPatientId(bean.getPatientId());
		// 诊所id
		order.setClinicId(bean.getClinicId());
		// 预约状态
		order.setAppStatus(MonkeyKingCode.APP_STATUS_TO_BE_CONFIRM);
		// 预约时间
		order.setAppDate(bean.getAppDate());
		// 治疗项目id
		order.setCureProjectId(bean.getCureProjectId());
		// 症状标签
		order.setSymptomTag(bean.getSymptomTag());
		// 症状描述
		order.setSymptomDescribe(bean.getSymptomDescribe());

		return appointmentOrderMapper.saveAppOrder(order);
	}

}
