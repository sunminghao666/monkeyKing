package com.monkey.gzpt.pojo;

import java.util.Date;

public class GzptJoinUp {
    private Long id;

    private String openid;

    private Date interactiveTime;

    private String inFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Date getInteractiveTime() {
        return interactiveTime;
    }

    public void setInteractiveTime(Date interactiveTime) {
        this.interactiveTime = interactiveTime;
    }

    public String getInFlag() {
        return inFlag;
    }

    public void setInFlag(String inFlag) {
        this.inFlag = inFlag == null ? null : inFlag.trim();
    }
}