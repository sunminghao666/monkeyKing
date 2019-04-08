/********************************************************
 * <p>Description: 优悦口腔_标签管理Service</p>
 * <p>Project: TagService.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.service;

import com.monkey.common.ResponseResult;

public interface TagService {

	/**
	  * 查询标签列表
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	ResponseResult searchTag(Integer tagType, Integer effectiveFlag);

}
