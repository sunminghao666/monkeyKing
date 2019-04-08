package com.monkey.convert;

import com.monkey.bean.respBean.SearchTagBean;
import com.monkey.dto.Tag;

public class TagConvert {

	/**
	 * Tag转换为SearchTagBean返回给前端
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	public static SearchTagBean tagConvert(Tag dto) {

		// 标签用Bean
		SearchTagBean bean = new SearchTagBean();

		if (dto != null) {

			// 标签id
			bean.setTagId(dto.getId());
			// 标签类型
			bean.setTagType(dto.getTagType());
			// 标签名称
			bean.setTagName(dto.getTagName());
			// 有效性
			bean.setEffectiveFlag(dto.getEffectiveFlag());
		}
		return bean;
	}

}
