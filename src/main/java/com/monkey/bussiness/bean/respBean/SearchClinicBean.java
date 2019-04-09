package com.monkey.bussiness.bean.respBean;

import java.io.Serializable;

public class SearchClinicBean implements Serializable {

	private static final long serialVersionUID = 9070498156189096840L;

	// 诊所Id
	private Long clinicId;

	// 诊所名称
	private String clinicName;

	// 诊所地址
	private String clinicAddress;

	// 诊所电话
	private String clinicTelephone;

	// 经度
	private Long longitude;

	// 纬度
	private Long latitude;

	// 联系人
	private String contacts;

	// wifi账号
	private String wifiAccount;

	// wifi密码
	private String wifiPassword;

	// 营业时间
	private String businessTime;

	// 有效性
	private Integer effectiveFlag;

	public Long getClinicId() {
		return clinicId;
	}

	public void setClinicId(Long clinicId) {
		this.clinicId = clinicId;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getClinicTelephone() {
		return clinicTelephone;
	}

	public void setClinicTelephone(String clinicTelephone) {
		this.clinicTelephone = clinicTelephone;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getWifiAccount() {
		return wifiAccount;
	}

	public void setWifiAccount(String wifiAccount) {
		this.wifiAccount = wifiAccount;
	}

	public String getWifiPassword() {
		return wifiPassword;
	}

	public void setWifiPassword(String wifiPassword) {
		this.wifiPassword = wifiPassword;
	}

	public String getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	public Integer getEffectiveFlag() {
		return effectiveFlag;
	}

	public void setEffectiveFlag(Integer effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
	}

}
