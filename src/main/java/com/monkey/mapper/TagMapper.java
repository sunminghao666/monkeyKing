package com.monkey.mapper;

import com.monkey.dto.Tag;

import java.util.List;
import java.util.Map;

public interface TagMapper {

	/**
	  * 查询标签信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
    List<Tag> searchTag(Map<String, Object> reqParam);
}