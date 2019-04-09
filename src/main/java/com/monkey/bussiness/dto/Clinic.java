package com.monkey.bussiness.dto;

import java.io.Serializable;
import java.util.Date;

public class Clinic implements Serializable {

	private static final long serialVersionUID = -2418514421592424814L;

	private Long id;

    private Date gmtCreate;

    private Date gmtModify;

    private String clinicName;

    private String clinicAddress;

    private String clinicTelephone;

    private Long longitude;

    private Long latitude;

    private String contacts;

    private String wifiAccount;

    private String wifiPassword;

    private String businessTime;

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