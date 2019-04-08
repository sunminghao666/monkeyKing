package com.monkey.bean.respBean;

import java.io.Serializable;

public class SearchTagBean implements Serializable {

	private static final long serialVersionUID = 5986000744639442728L;

	private Long tagId;

	private Integer tagType;

	private String tagName;

	private Integer effectiveFlag;

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Integer getTagType() {
		return tagType;
	}

	public void setTagType(Integer tagType) {
		this.tagType = tagType;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getEffectiveFlag() {
		return effectiveFlag;
	}

	public void setEffectiveFlag(Integer effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
	}
	
}
