package com.monkey.dto;

import java.io.Serializable;
import java.util.Date;

public class Tag implements Serializable {

	private static final long serialVersionUID = -7553194468571551724L;

	private Long id;

    private Date gmtCreate;

    private Date gmtModify;

    private Integer tagType;

    private String tagName;

    private Integer effectiveFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
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