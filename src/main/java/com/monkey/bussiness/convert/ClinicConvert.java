package com.monkey.bussiness.convert;

import com.monkey.bussiness.bean.respBean.CureProjectBean;
import com.monkey.bussiness.bean.respBean.SearchClinicBean;
import com.monkey.bussiness.dto.Clinic;
import com.monkey.bussiness.dto.CureProject;

public class ClinicConvert {

	/**
	 * Clinic转换为SearchClinicBean返回给前端
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	public static SearchClinicBean clinicConvert(Clinic dto) {

		// 标签用Bean
		SearchClinicBean bean = new SearchClinicBean();

		if (dto != null) {

			// 诊所id
			bean.setClinicId(dto.getId());
			// 诊所名称
			bean.setClinicName(dto.getClinicName());
			// 诊所地址
			bean.setClinicAddress(dto.getClinicAddress());
			// 诊所电话
			bean.setClinicTelephone(dto.getClinicTelephone());
			// 经度
			bean.setLongitude(dto.getLongitude());
			// 纬度
			bean.setLatitude(dto.getLatitude());
			// 联系人
			bean.setContacts(dto.getContacts());
			// wifi账号
			bean.setWifiAccount(dto.getWifiAccount());
			// wifi密码
			bean.setWifiPassword(dto.getWifiPassword());
			// 营业时间
			bean.setBusinessTime(dto.getBusinessTime());
			// 有效性
			bean.setEffectiveFlag(dto.getEffectiveFlag());
		}
		return bean;
	}

	/**
	 * Clinic转换为CureProjectBean返回给前端
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	public static CureProjectBean cureProjectConvert(CureProject dto) {

		// 返回用Bean
		CureProjectBean bean = new CureProjectBean();

		if (dto != null) {

			// 治疗项目id
			bean.setCureProjectId(dto.getId());
			// 治疗项目名称
			bean.setCureProjectName(dto.getCureProjectName());
			// 有效性
			bean.setEffectiveFlag(dto.getEffectiveFlag());
		}
		return bean;
	}

}
