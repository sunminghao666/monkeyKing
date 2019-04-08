package com.monkey.bussiness.dto;

import java.io.Serializable;
import java.util.Date;

public class AppointmentOrder implements Serializable {

	private static final long serialVersionUID = 2056417781307362478L;

	private Long id;

    private Date gmtCreate;

    private Date gmtModify;

    private Long userId;

    private Long patientId;

    private Long clinicId;

    private Integer appStatus;

    private Date appDate;

    private Long cureProjectId;

    private String symptomTag;

    private String symptomDescribe;

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

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
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