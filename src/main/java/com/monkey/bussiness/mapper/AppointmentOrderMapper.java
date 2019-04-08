package com.monkey.bussiness.mapper;

import com.monkey.bussiness.dto.AppointmentOrder;

public interface AppointmentOrderMapper {

	/**
	  * 保存预约信息
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
    int saveAppOrder(AppointmentOrder dto);

}