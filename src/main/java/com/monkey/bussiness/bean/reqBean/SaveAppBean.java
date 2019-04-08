package com.monkey.bussiness.bean.reqBean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaveAppBean", description = "添加预约信息Bean")
public class SaveAppBean implements Serializable {

	private static final long serialVersionUID = 8954837338719921490L;

	@ApiModelProperty(value="用户id", required=true)
	private Long userId;

	@ApiModelProperty(value="就诊人id", required=true)
	private Long patientId;

	@ApiModelProperty(value="诊所id", required=true)
	private Long clinicId;

	@ApiModelProperty(value="预约时间", required=true)
	private Date appDate;

	@ApiModelProperty(value="预约的项目id", required=true)
	private Long cureProjectId;

	@ApiModelProperty(value="症状标签", required=false)
	private String symptomTag;

	@ApiModelProperty(value="症状描述", required=false)
	private String symptomDescribe;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getClinicId() {
		return clinicId;
	}

	public void setClinicId(Long clinicId) {
		this.clinicId = clinicId;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public Long getCureProjectId() {
		return cureProjectId;
	}

	public void setCureProjectId(Long cureProjectId) {
		this.cureProjectId = cureProjectId;
	}

	public String getSymptomTag() {
		return symptomTag;
	}

	public void setSymptomTag(String symptomTag) {
		this.symptomTag = symptomTag;
	}

	public String getSymptomDescribe() {
		return symptomDescribe;
	}

	public void setSymptomDescribe(String symptomDescribe) {
		this.symptomDescribe = symptomDescribe;
	}

}
