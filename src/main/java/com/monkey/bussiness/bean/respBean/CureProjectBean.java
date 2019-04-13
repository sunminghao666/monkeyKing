package com.monkey.bussiness.bean.respBean;

import java.io.Serializable;

public class CureProjectBean implements Serializable {

	private static final long serialVersionUID = 7693027933194831777L;

	// 治疗项目id
	private Long cureProjectId;

	// 治疗项目名称
	private String cureProjectName;

	// 有效性
	private Integer effectiveFlag;

	public Long getCureProjectId() {
		return cureProjectId;
	}

	public void setCureProjectId(Long cureProjectId) {
		this.cureProjectId = cureProjectId;
	}

	public String getCureProjectName() {
		return cureProjectName;
	}

	public void setCureProjectName(String cureProjectName) {
		this.cureProjectName = cureProjectName;
	}

	public Integer getEffectiveFlag() {
		return effectiveFlag;
	}

	public void setEffectiveFlag(Integer effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
	}

}
