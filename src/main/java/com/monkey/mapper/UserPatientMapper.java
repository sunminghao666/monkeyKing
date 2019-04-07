package com.monkey.mapper;

import com.monkey.dto.UserPatient;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPatientMapper {

	/**
	 * 保存就诊人信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
    int saveUserPatient(UserPatient dto);

	/**
	 * 查询就诊人信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
    List<UserPatient> searchUserPatient(Map<String, Object> reqParam);

}