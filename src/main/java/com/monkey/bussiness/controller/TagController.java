/********************************************************
 * <p>Description: 优悦口腔_标签管理Controller</p>
 * <p>Project: TagController.java </p>
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
import com.monkey.bussiness.service.TagService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private TagService tagService;

	/**
	  * 查询标签列表
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@ApiOperation(value="查询标签列表", notes = "根据标签类型、有效性查询标签列表", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tagType", value = "标签类型(0:症状标签)", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "effectiveFlag", value = "有效性(0:无效;1:有效)", required = true, dataType = "Integer")})
	@GetMapping("/searchTag/{tagType}/{effectiveFlag}")
	public ResponseResult searchTag(@PathVariable Integer tagType, @PathVariable Integer effectiveFlag) {

		ResponseResult respResult = new ResponseResult();

		respResult = tagService.searchTag(tagType, effectiveFlag);

        return respResult;
    }

}
