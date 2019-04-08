/********************************************************
 * <p>Description: 优悦口腔_就诊人管理Dao实现层</p>
 * <p>Project: UserPatientDaoImpl.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monkey.bussiness.dao.UserPatientDao;
import com.monkey.bussiness.dto.UserPatient;
import com.monkey.bussiness.mapper.UserPatientMapper;

@Repository
public class UserPatientDaoImpl implements UserPatientDao {

	@Autowired
	private UserPatientMapper userPatientMapper;

	/**
	  * 保存就诊人信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public int saveUserPatient(Long userId, String patientName, Integer patientSex, String patientPhoneNo, Integer patientAge) {

		// 保存用dto
		UserPatient userPatient = new UserPatient();

		// 当前时间
		Date date = new Date();

		// 创建时间
		userPatient.setGmtCreate(date);
		// 修改时间
		userPatient.setGmtModify(date);
		// 用户id
		userPatient.setUserId(userId);
		// 就诊人姓名
		userPatient.setPatientName(patientName);
		// 就诊人性别
		userPatient.setPatientSex(patientSex);
		// 就诊人电话
		userPatient.setPatientPhoneNo(patientPhoneNo);
		// 就诊人年龄
		userPatient.setPatientAge(patientAge);
		
		return userPatientMapper.saveUserPatient(userPatient);
	}

	/**
	 * 查询用户就诊人信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public List<UserPatient> searchUserPatientForList(Long userId) {

		// 请求参数
		Map<String, Object> reqParam = new HashMap<String, Object>();
		reqParam.put("userId", userId);

		// 查询用List
		List<UserPatient> list = null;
		list = userPatientMapper.searchUserPatient(reqParam);

		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

}
