package com.monkey.bussiness.bean.respBean;

import java.io.Serializable;

public class AppTimeBean implements Serializable {

	private static final long serialVersionUID = -3987683247153792848L;

	private String appBeginTime;

	private String appEndTime;

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
