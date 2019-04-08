package com.monkey.db.base.pojo;

import java.util.Date;

public class PwbDictionary {
    private Long id;

    private String dicType;

    private String dicTypeLabel;

    private String dicCode;

    private String dicValue;

    private String dicExplain;

    private String dicStatus;

    private Integer dicOrder;

    private String dicChannel;

    private String insertcode;

    private Date inserttime;

    private String remark;

    private String systemChannel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType == null ? null : dicType.trim();
    }

    public String getDicTypeLabel() {
        return dicTypeLabel;
    }

    public void setDicTypeLabel(String dicTypeLabel) {
        this.dicTypeLabel = dicTypeLabel == null ? null : dicTypeLabel.trim();
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue == null ? null : dicValue.trim();
    }

    public String getDicExplain() {
        return dicExplain;
    }

    public void setDicExplain(String dicExplain) {
        this.dicExplain = dicExplain == null ? null : dicExplain.trim();
    }

    public String getDicStatus() {
        return dicStatus;
    }

    public void setDicStatus(String dicStatus) {
        this.dicStatus = dicStatus == null ? null : dicStatus.trim();
    }

    public Integer getDicOrder() {
        return dicOrder;
    }

    public void setDicOrder(Integer dicOrder) {
        this.dicOrder = dicOrder;
    }

    public String getDicChannel() {
        return dicChannel;
    }

    public void setDicChannel(String dicChannel) {
        this.dicChannel = dicChannel == null ? null : dicChannel.trim();
    }

    public String getInsertcode() {
        return insertcode;
    }

    public void setInsertcode(String insertcode) {
        this.insertcode = insertcode == null ? null : insertcode.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSystemChannel() {
        return systemChannel;
    }

    public void setSystemChannel(String systemChannel) {
        this.systemChannel = systemChannel == null ? null : systemChannel.trim();
    }
}