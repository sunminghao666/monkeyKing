/********************************************************
 * <p>Description: 优悦口腔_就诊人管理Dao</p>
 * <p>Project: UserPatientDao.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.dao;

import java.util.List;

import com.monkey.bussiness.dto.UserPatient;

public interface UserPatientDao {

	/**
	 * 保存就诊人信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	int saveUserPatient(Long userId, String patientName, Integer patientSex, String patientPhoneNo, Integer patientAge);

	/**
	 * 查询就诊人信息列表
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	List<UserPatient> searchUserPatientForList(Long userId);

	/**
	 * 查询就诊人信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	UserPatient searchUserPatientForDto(Long patientId);

}
