/********************************************************
 * <p>Description: 优悦口腔_预约管理Controller</p>
 * <p>Project: AppointmentController.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monkey.basic.common.ResponseResult;
import com.monkey.bussiness.bean.reqBean.SavePatientBean;
import com.monkey.bussiness.enums.MonkeyKingReturnEnum;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userApp")
public class AppointmentController {

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
	@PostMapping("/savePatient")
	public ResponseResult savePatient(@RequestBody SavePatientBean bean) {

		ResponseResult respResult = new ResponseResult();

		if (bean == null || bean.getUserId() == null
				|| StringUtils.isEmpty(bean.getPatientName())
				|| StringUtils.isEmpty(bean.getPatientSex())
				|| StringUtils.isEmpty(bean.getPatientPhoneNo())
				|| bean.getPatientAge() == null) {
			respResult.setRespCode(MonkeyKingReturnEnum.REQ_PARAM_IS_NULL
					.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.REQ_PARAM_IS_NULL
					.getMsg());
			return respResult;
		}

		// respResult = patientService.savePatient(bean);

		return respResult;
	}

}
