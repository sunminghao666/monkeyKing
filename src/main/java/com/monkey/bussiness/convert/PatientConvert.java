package com.monkey.bussiness.convert;

import com.monkey.bussiness.bean.respBean.SearchPatientBean;
import com.monkey.bussiness.dto.UserPatient;

public class PatientConvert {

	/**
	 * UserPatient转换为UserPatientBean返回给前端
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	public static SearchPatientBean UserPatientConvert(UserPatient dto) {

		// 就诊人用Bean
		SearchPatientBean bean = new SearchPatientBean();

		if (dto != null) {

			// 就诊人id
			bean.setPatientId(dto.getId());
			// 就诊人姓名
			bean.setPatientName(dto.getPatientName());
			// 就诊人性别
			bean.setPatientSex(dto.getPatientSex());
			// 就诊人手机号
			bean.setPatientPhoneNo(dto.getPatientPhoneNo());
			// 就诊人年龄
			bean.setPatientAge(dto.getPatientAge());
		}
		return bean;
	}

}
