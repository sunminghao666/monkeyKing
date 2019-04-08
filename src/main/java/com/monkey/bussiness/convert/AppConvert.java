package com.monkey.bussiness.convert;

import com.monkey.bussiness.bean.respBean.AppTimeBean;
import com.monkey.bussiness.dto.AppointmentTime;

public class AppConvert {

	/**
	 * AppointmentTime转换为AppTimeBean返回给前端
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	public static AppTimeBean appTimeConvert(AppointmentTime dto) {

		// 预约时间段用Bean
		AppTimeBean bean = new AppTimeBean();

		if (dto != null) {

			// 预约开始时间
			bean.setAppBeginTime(dto.getAppBeginTime());
			// 预约结束时间
			bean.setAppEndTime(dto.getAppEndTime());
		}
		return bean;
	}

}
