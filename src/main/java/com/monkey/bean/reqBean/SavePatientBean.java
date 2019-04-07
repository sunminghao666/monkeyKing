package com.monkey.bean.reqBean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SavePatientBean", description = "添加就诊人信息Bean")
public class SavePatientBean implements Serializable {

	private static final long serialVersionUID = -8646103286735294255L;

	@ApiModelProperty(value="用户id", required=true)
	private Long userId;

	@ApiModelProperty(value="就诊人姓名", required=true)
	private String patientName;

	@ApiModelProperty(value="就诊人性别", required=true)
	private Integer patientSex;

	@ApiModelProperty(value="就诊人手机号", required=true)
	private String patientPhoneNo;

	@ApiModelProperty(value="就诊人年龄", required=true)
	private Integer patientAge;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
