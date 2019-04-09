/********************************************************
 * <p>Description: 优悦口腔_预约管理Controller</p>
 * <p>Project: AppointmentController.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monkey.basic.common.ResponseResult;
import com.monkey.bussiness.bean.reqBean.SaveAppBean;
import com.monkey.bussiness.enums.MonkeyKingReturnEnum;
import com.monkey.bussiness.service.AppointmentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userApp")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	/**
	  * 查询可预约时间段
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@ApiOperation(value = "查询可预约时间段", notes = "查询可预约时间段", httpMethod = "GET")
	@GetMapping("/searchAppTime")
	public ResponseResult searchAppTime() {

		ResponseResult respResult = new ResponseResult();

		respResult = appointmentService.searchAppTime();

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
	@ApiOperation(value = "添加预约记录", notes = "根据用户id、就诊人id、诊所id、预约的项目id、预约时间枚举、", httpMethod = "POST")
	@ApiImplicitParam(name = "bean", value = "预约信息", required = true, dataType = "SaveAppBean")
	@PostMapping("/saveAppointment")
	public ResponseResult saveAppointment(@RequestBody SaveAppBean bean) {

		ResponseResult respResult = new ResponseResult();

		if (bean == null || bean.getUserId() == null || bean.getPatientId() == null || bean.getClinicId() == null
				|| bean.getAppDate() == null || bean.getCureProjectId() == null) {
			respResult.setRespCode(MonkeyKingReturnEnum.REQ_PARAM_IS_NULL.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.REQ_PARAM_IS_NULL.getMsg());
			return respResult;
		}

		respResult = appointmentService.saveAppointment(bean);

		return respResult;
	}

}
