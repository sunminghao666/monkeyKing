package com.monkey.bussiness.enums;

public enum MonkeyKingReturnEnum {

	SUCCESS(0, "请求成功"), 
	FAILED(-1, "请求异常"),

	
	
	REQ_PARAM_IS_NULL(-10000, "请求参数为空"),

	
	
	ADD_PATIENT_INFO(-11001, "请添加就诊人信息"),
	ADD_CURE_PROJECT_INFO(-11001, "请添加治疗项目"),

	SAVE_APP_FAILED(-11003, "预约失败，请稍后重试"),

	;

	MonkeyKingReturnEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private Integer code;
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
