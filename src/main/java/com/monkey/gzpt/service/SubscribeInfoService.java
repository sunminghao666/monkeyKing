package com.monkey.gzpt.service;

import net.sf.json.JSONObject;

import com.monkey.taf.web.util.Status;

public interface SubscribeInfoService {

	void getCode(String phone, Status status);

	void bind(String userName, String openid, Status status);

	void unBind(JSONObject params, Status status);
}
