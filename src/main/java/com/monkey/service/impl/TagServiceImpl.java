/********************************************************
 * <p>Description: 优悦口腔_标签管理Service实现层</p>
 * <p>Project: TagServiceImpl.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.bean.respBean.SearchTagBean;
import com.monkey.common.ResponseResult;
import com.monkey.convert.TagConvert;
import com.monkey.dao.TagDao;
import com.monkey.dto.Tag;
import com.monkey.enums.MonkeyKingReturnEnum;
import com.monkey.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	/** log */
	private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

	@Autowired
	private TagDao tagDao;

	/**
	  * 查询标签列表
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public ResponseResult searchTag(Integer tagType, Integer effectiveFlag) {

		ResponseResult respResult = new ResponseResult();

		// 查询标签用map
		Map<Object, Object> returnsMap = new HashMap<Object, Object>();
		List<Tag> list = null;

		// 返回标签用List
		List<SearchTagBean> returnList = new ArrayList<SearchTagBean>();

		try {

			// 保存就诊人信息
			list = tagDao.searchTagList(tagType, effectiveFlag);

			if (list != null) {

				for (Tag dto : list) {

					SearchTagBean bean = null;
					bean = TagConvert.tagConvert(dto);
					returnList.add(bean);
				}
			}

			returnsMap.put("tag", returnList);
			respResult.setReturns(returnsMap);

		}  catch (Exception e) {
			logger.error("查询标签列表异常", "TagServiceImpl.searchTag()", e);
			respResult.setRespCode(MonkeyKingReturnEnum.FAILED.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.FAILED.getMsg());
		}

		return respResult;
	}
}

