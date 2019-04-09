package com.monkey.bussiness.dto;

import java.io.Serializable;
import java.util.Date;

public class AppointmentTime implements Serializable {

	private static final long serialVersionUID = -1163100826722000698L;

	private Long id;

    private Date gmtCreate;

    private Date gmtModify;

    private String appBeginTime;

    private String appEndTime;

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

    public String getAppBeginTime() {
        return appBeginTime;
    }

    public void setAppBeginTime(String appBeginTime) {
        this.appBeginTime = appBeginTime;
    }

    public String getAppEndTime() {
        return appEndTime;
    }

    public void setAppEndTime(String appEndTime) {
        this.appEndTime = appEndTime;
    }
}