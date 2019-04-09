/********************************************************
 * <p>Description: 优悦口腔_诊所模块Controller</p>
 * <p>Project: ClinicController.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monkey.basic.common.ResponseResult;
import com.monkey.bussiness.service.ClinicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="/clinic", tags="诊所模块")
@RestController
@RequestMapping("/clinic")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;

	/**
	  * 查询诊所列表
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@ApiOperation(value="查询诊所列表", notes = "根据诊所id、有效性查询诊所信息", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "clinicId", value = "诊所id", required = false, dataType = "Long"),
			@ApiImplicitParam(name = "clinicName", value = "诊所名称", required = false, dataType = "String"),
			@ApiImplicitParam(name = "effectiveFlag", value = "有效性(0:无效;1:有效)", required = false, dataType = "Integer")})
	@GetMapping("/searchClinic/{clinicId}/{clinicName}/{effectiveFlag}")
	public ResponseResult searchClinic(@PathVariable Long clinicId, @PathVariable String clinicName, @PathVariable Integer effectiveFlag) {

		ResponseResult respResult = new ResponseResult();

		respResult = clinicService.searchClinic(clinicId, clinicName, effectiveFlag);

        return respResult;
    }

	/**
	  * 查询治疗项目
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@ApiOperation(value="查询治疗项目", notes = "根据诊所id、有效性查询治疗项目", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cureProjectId", value = "治疗项目id", required = false, dataType = "Long"),
			@ApiImplicitParam(name = "cureProjectName", value = "治疗项目名称", required = false, dataType = "String"),
			@ApiImplicitParam(name = "effectiveFlag", value = "有效性(0:无效;1:有效)", required = false, dataType = "Integer")})
	@GetMapping("/searchCureProject/{cureProjectId}/{cureProjectName}/{effectiveFlag}")
	public ResponseResult searchCureProject(@PathVariable Long cureProjectId, @PathVariable String cureProjectName, @PathVariable Integer effectiveFlag) {

		ResponseResult respResult = new ResponseResult();

		respResult = clinicService.searchCureProject(cureProjectId, cureProjectName, effectiveFlag);

       return respResult;
   }

}
