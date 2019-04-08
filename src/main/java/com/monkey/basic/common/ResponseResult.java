package com.monkey.basic.common;

import java.util.HashMap;
import java.util.Map;

import com.monkey.bussiness.enums.MonkeyKingReturnEnum;

public class ResponseResult {

    private Integer respCode = MonkeyKingReturnEnum.SUCCESS.getCode();

    private String respMsg = MonkeyKingReturnEnum.SUCCESS.getMsg();

    private Map<Object, Object> returns;

	public ResponseResult() {
		this.respCode = 1;
		this.respMsg = "请求成功";
		this.returns = new HashMap<Object, Object>();
	}

	public Integer getRespCode() {
		return respCode;
	}

	public void setRespCode(Integer respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public Map<Object, Object> getReturns() {
		return returns;
	}

	public void setReturns(Map<Object, Object> returns) {
		this.returns = returns;
	}

	
}
