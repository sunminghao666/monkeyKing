package com.monkey.enums;

public enum MonkeyKingReturnEnum {

	SUCCESS(0, "请求成功"), 
	FAILED(-1, "请求异常"),

	
	
	REQ_PARAM_IS_NULL(-10000, "请求参数为空"),

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
