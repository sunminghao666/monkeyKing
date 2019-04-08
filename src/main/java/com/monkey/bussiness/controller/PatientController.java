/********************************************************
 * <p>Description: 优悦口腔_就诊人管理Controller</p>
 * <p>Project: PatientController.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monkey.basic.common.ResponseResult;
import com.monkey.bussiness.bean.reqBean.SavePatientBean;
import com.monkey.bussiness.enums.MonkeyKingReturnEnum;
import com.monkey.bussiness.service.PatientService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userPatient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	/**
	 * 添加就诊人
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@ApiOperation(value="添加就诊人", notes = "根据用户id、就诊人姓名、就诊人性别、就诊人手机号、就诊人年龄添加就诊人信息", httpMethod = "POST")
	@ApiImplicitParam(name = "bean", value = "就诊人信息", required = true, dataType = "SavePatientBean")
	@PostMapping("/savePatient")
	public ResponseResult savePatient(@RequestBody SavePatientBean bean) {

		ResponseResult respResult = new ResponseResult();

		if (bean == null || bean.getUserId() == null || StringUtils.isEmpty(bean.getPatientName()) || StringUtils.isEmpty(bean.getPatientSex())
				|| StringUtils.isEmpty(bean.getPatientPhoneNo()) || bean.getPatientAge() == null) {
			respResult.setRespCode(MonkeyKingReturnEnum.REQ_PARAM_IS_NULL.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.REQ_PARAM_IS_NULL.getMsg());
			return respResult;
		}

		respResult = patientService.savePatient(bean);

        return respResult;
    }

	/**
	 * 查询就诊人信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@ApiOperation(value="查询就诊人信息", notes = "根据用户id查询用户账户下就诊人信息列表")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long")
	@GetMapping("/searchUserPatient/{userId}")
	public ResponseResult searchUserPatient(@PathVariable Long userId) {

		ResponseResult respResult = new ResponseResult();

		if (userId == null) {
			respResult.setRespCode(MonkeyKingReturnEnum.REQ_PARAM_IS_NULL.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.REQ_PARAM_IS_NULL.getMsg());
			return respResult;
		}

		respResult = patientService.searchUserPatient(userId);

      return respResult;
  }


}
