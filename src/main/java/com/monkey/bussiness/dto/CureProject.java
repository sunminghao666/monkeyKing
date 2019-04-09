package com.monkey.bussiness.dto;

import java.io.Serializable;
import java.util.Date;

public class CureProject implements Serializable {

	private static final long serialVersionUID = -1163150826722000698L;

    private Long id;

    private Date gmtCreate;

    private Date gmtModify;

    private String cureProjectName;

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