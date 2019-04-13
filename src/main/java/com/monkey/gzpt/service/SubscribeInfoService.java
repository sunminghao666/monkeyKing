package com.monkey.gzpt.service;

import net.sf.json.JSONObject;

import com.monkey.taf.web.util.Status;

public interface SubscribeInfoService {

	void getCode(JSONObject params, Status status);

	void bind(JSONObject params, Status status);

	void unBind(JSONObject params, Status status);
}
