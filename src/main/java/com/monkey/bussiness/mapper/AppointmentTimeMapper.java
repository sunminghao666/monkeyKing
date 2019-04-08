package com.monkey.bussiness.mapper;

import java.util.List;

import com.monkey.bussiness.dto.AppointmentTime;

public interface AppointmentTimeMapper {

	/**
	  * 查询可预约时间段
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
   List<AppointmentTime> searchAppTime();
}