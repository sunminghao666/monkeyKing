/********************************************************
 * <p>Description: 优悦口腔_诊所模块Dao</p>
 * <p>Project: ClinicDao.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.dao;

import java.util.List;

import com.monkey.bussiness.dto.Clinic;

public interface ClinicDao {

	/**
	  * 查询诊所信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	List<Clinic> searchClinicForList(Long clinicId, String clinicName, Integer effectiveFlag);

}
