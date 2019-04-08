/********************************************************
 * <p>Description: 优悦口腔_标签管理Dao实现层</p>
 * <p>Project: TagDaoImpl.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monkey.dao.TagDao;
import com.monkey.dto.Tag;
import com.monkey.mapper.TagMapper;

@Repository
public class TagDaoImpl implements TagDao {

	@Autowired
	private TagMapper tagMapper;

	/**
	 * 查询标签信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public List<Tag> searchTagList(Integer tagType, Integer effectiveFlag) {

		// 请求参数
		Map<String, Object> reqParam = new HashMap<String, Object>();

		if (tagType != null) {
			reqParam.put("tagType", tagType);
		}

		if (effectiveFlag != null) {
			reqParam.put("effectiveFlag", effectiveFlag);
		}

		// 查询用List
		List<Tag> list = null;
		list = tagMapper.searchTag(reqParam);

		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

}
