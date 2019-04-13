package com.monkey.gzpt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monkey.gzpt.service.SubscribeInfoService;
import com.monkey.taf.web.util.Status;

@RestController
@RequestMapping("/subscribe")
@Api(value = "/subscribe", tags = { "用户绑定" })
public class SubscribeController {
	@Autowired
	SubscribeInfoService subscribeInfoService;

	 @ApiOperation(value = "获取验证码", notes = "获取验证码")
	 @ApiImplicitParam(name = "userName", value = "手机号", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "getCode", method = RequestMethod.GET)
	public Status getCode(@RequestParam String userName) {
		Status status = new Status();
		subscribeInfoService.getCode(userName, status);
		return status;
	}

	@ApiOperation(value = "绑定用户", notes = "绑定用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "手机号", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "openId", value = "openId", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "bind", method = RequestMethod.GET)
	public Status bind(@RequestParam String userName,
			@RequestParam String openId) {
		Status status = new Status();
		subscribeInfoService.bind(userName, openId, status);
		return status;
	}
}
