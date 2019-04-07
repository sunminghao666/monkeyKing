package com.monkey.bean.respBean;

import java.io.Serializable;

public class SearchPatientBean implements Serializable {

	private static final long serialVersionUID = 4054073907289814834L;

	private Long patientId;

	private String patientName;

	private Integer patientSex;

	private String patientPhoneNo;

	private Integer patientAge;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(Integer patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientPhoneNo() {
		return patientPhoneNo;
	}

	public void setPatientPhoneNo(String patientPhoneNo) {
		this.patientPhoneNo = patientPhoneNo;
	}

	public Integer getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

}
