/********************************************************
 * <p>Description: 优悦口腔_预约管理Service实现层</p>
 * <p>Project: AppointmentServiceImpl.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.basic.common.ResponseResult;
import com.monkey.bussiness.bean.reqBean.SaveAppBean;
import com.monkey.bussiness.bean.respBean.AppTimeBean;
import com.monkey.bussiness.convert.AppConvert;
import com.monkey.bussiness.dao.AppointmentDao;
import com.monkey.bussiness.dao.CureProjectDao;
import com.monkey.bussiness.dao.UserPatientDao;
import com.monkey.bussiness.dto.AppointmentTime;
import com.monkey.bussiness.dto.CureProject;
import com.monkey.bussiness.dto.UserPatient;
import com.monkey.bussiness.enums.MonkeyKingReturnEnum;
import com.monkey.bussiness.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	/** log */
	private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	@Autowired
	private UserPatientDao userPatientDao;

	@Autowired
	private AppointmentDao appointmentDao;

	@Autowired
	private CureProjectDao cureProjectDao;

	/**
	  * 查询可预约时间段
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public ResponseResult searchAppTime() {

		ResponseResult respResult = new ResponseResult();

		// 查询可预约时间段用map
		Map<Object, Object> returnsMap = new HashMap<Object, Object>();
		List<AppointmentTime> list = null;

		// 返回标签用List
		List<AppTimeBean> returnList = new ArrayList<AppTimeBean>();

		try {

			// 保存就诊人信息
			list = appointmentDao.searchAppTime();

			if (list != null) {

				for (AppointmentTime dto : list) {

					AppTimeBean bean = null;
					bean = AppConvert.appTimeConvert(dto);
					returnList.add(bean);
				}
			}

			returnsMap.put("tag", returnList);
			respResult.setReturns(returnsMap);

		} catch (Exception e) {
			logger.error("查询可预约时间段异常", "AppointmentServiceImpl.searchAppTime()", e);
			respResult.setRespCode(MonkeyKingReturnEnum.FAILED.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.FAILED.getMsg());
		}

		return respResult;
	}

	/**
	  * 添加预约记录
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public ResponseResult saveAppointment(SaveAppBean bean) {

		ResponseResult respResult = new ResponseResult();

		// 就诊人信息Dto
		UserPatient userPatient = null;
		// 治疗项目信息

		try {

			// 查询就诊人信息
			userPatient = userPatientDao.searchUserPatientForDto(bean.getPatientId());

			if (userPatient == null) {
				respResult.setRespCode(MonkeyKingReturnEnum.ADD_PATIENT_INFO.getCode());
				respResult.setRespMsg(MonkeyKingReturnEnum.ADD_PATIENT_INFO.getMsg());
				return respResult;
			}

			// 查询治疗项目
			CureProject cureProject = null;
			cureProject = cureProjectDao.searchCureProjectForDto(bean.getCureProjectId());

			if (cureProject == null) {
				respResult.setRespCode(MonkeyKingReturnEnum.ADD_CURE_PROJECT_INFO.getCode());
				respResult.setRespMsg(MonkeyKingReturnEnum.ADD_CURE_PROJECT_INFO.getMsg());
				return respResult;
			}

			// 保存预约记录数
			int saveCount = 0;
			saveCount = appointmentDao.saveAppOrder(bean);

			if (saveCount > 0) {

				//TODO 发送微信消息模板
			} else {
				respResult.setRespCode(MonkeyKingReturnEnum.SAVE_APP_FAILED.getCode());
				respResult.setRespMsg(MonkeyKingReturnEnum.SAVE_APP_FAILED.getMsg());
			}
		} catch (Exception e) {
			logger.error("添加预约记录异常", "AppointmentServiceImpl.saveAppointment()", e);
			respResult.setRespCode(MonkeyKingReturnEnum.FAILED.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.FAILED.getMsg());
		}
		return respResult;
	}
}

