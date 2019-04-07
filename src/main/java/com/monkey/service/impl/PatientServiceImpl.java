/********************************************************
 * <p>Description: 优悦口腔_就诊人管理Service实现层</p>
 * <p>Project: PatientServiceImpl.java </p>
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

import com.monkey.bean.reqBean.SavePatientBean;
import com.monkey.bean.respBean.SearchPatientBean;
import com.monkey.common.ResponseResult;
import com.monkey.convert.PatientConvert;
import com.monkey.dao.UserPatientDao;
import com.monkey.dto.UserPatient;
import com.monkey.enums.MonkeyKingReturnEnum;
import com.monkey.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	/** log */
	private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

	@Autowired
	private UserPatientDao userPatientDao;

	/**
	 * 添加就诊人
	 * 
	 * @param
	 * @since 2018-12-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public ResponseResult savePatient(SavePatientBean bean) {

		ResponseResult respResult = new ResponseResult();

		try {

			// 保存就诊人信息
			userPatientDao.saveUserPatient(bean.getUserId(), bean.getPatientName(), bean.getPatientSex(), bean.getPatientPhoneNo(), bean.getPatientAge());

		}  catch (Exception e) {
			logger.error("添加就诊人异常", "PatientServiceImpl.addPatient()", e);
			respResult.setRespCode(MonkeyKingReturnEnum.FAILED.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.FAILED.getMsg());
		}

		return respResult;
	}

	/**
	 * 查询用户就诊人
	 * 
	 * @param
	 * @since 2018-12-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public ResponseResult searchUserPatient(Long userId) {

		ResponseResult respResult = new ResponseResult();

		// 查询就诊人信息用map
		Map<Object, Object> returnsMap = new HashMap<Object, Object>();
		List<UserPatient> list = null;

		// 返回就诊人信息用List
		List<SearchPatientBean> returnList = new ArrayList<SearchPatientBean>();

		try {

			// 保存就诊人信息
			list = userPatientDao.searchUserPatientForList(userId);

			if (list != null) {

				for (UserPatient dto : list) {

					SearchPatientBean bean = null;
					bean = PatientConvert.UserPatientConvert(dto);
					returnList.add(bean);
				}
			}

			returnsMap.put("userPatient", returnList);
			respResult.setReturns(returnsMap);

		}  catch (Exception e) {
			logger.error("查询用户就诊人异常", "PatientServiceImpl.searchUserPatient()", e);
			respResult.setRespCode(MonkeyKingReturnEnum.FAILED.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.FAILED.getMsg());
		}

		return respResult;
	}
}

