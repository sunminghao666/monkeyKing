package com.monkey.gzpt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.google.gson.Gson;
import com.monkey.basic.base.util.ConfigInfo;
import com.monkey.basic.message.util.SmsUtil;
import com.monkey.bussiness.dto.ClinicUser;
import com.monkey.bussiness.mapper.ClinicUserMapper;
import com.monkey.gzpt.dao.GzptSubscribeInfoMapper;
import com.monkey.gzpt.pojo.GzptSubscribeInfo;
import com.monkey.gzpt.service.SubscribeInfoService;
import com.monkey.taf.common.Tools;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Status;

@Service
public class SubscribeInfoServiceImpl implements SubscribeInfoService {
	@Autowired
	private GzptSubscribeInfoMapper gzptSubscribeInfoMapper;
	@Autowired
	private ClinicUserMapper clinicUserMapper;
	/**
	 * 获取验证码
	 */
	@Override
	public void getCode(String phone, Status status) {
		GzptSubscribeInfo gzptSubscribeInfo = this.gzptSubscribeInfoMapper
				.selectByUserName(phone);
		status.getReturns().put("userName", phone);
		if (gzptSubscribeInfo == null) {
			status.getReturns().put("validateCode",
					(int) ((Math.random() * 9 + 1) * 100000) + "");
		} else {
			if ("1".equals(gzptSubscribeInfo.getSubscribe())) {
				status.setStatusCode("000001");
				status.setStatusMessage("该用户已绑定微信号！");
			} else {
				status.getReturns().put("validateCode",
						(int) ((Math.random() * 9 + 1) * 100000) + "");
			}

		}
		System.out.println(status.getReturns().get("validateCode"));
		// status.getReturns().put("validateCode", "123456");
		if (Tools.isNotBlank(status.getReturns().get("validateCode"))) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("code", (String) status.getReturns().get("validateCode"));
			Gson gson = new Gson();
			try {
				SendSmsResponse response = SmsUtil.sendSms(
						ConfigInfo.getByProperties("sms.temp0"),
						gson.toJson(msg), phone);
				if ("OK".equals(response.getCode())) {

				} else {
					status.setStatusCode("000001");
					status.setStatusMessage("短信发送失败！");
				}
			} catch (Exception e) {
				TAFLog.error("短信发送失败", e);
				status.setStatusCode("000001");
				status.setStatusMessage("短信发送失败！");
			}
		}
	}

	/**
	 * 进行绑定
	 */
	@Override
	public void bind(String userName, String openid, Status status) {
		GzptSubscribeInfo gzptSubscribeInfo = new GzptSubscribeInfo();
		ClinicUser clinicUser = new ClinicUser();
		clinicUser.setGmtCreate(new Date());
		clinicUser.setOpenid(openid);
		clinicUser.setPhoneNo(userName);
		this.clinicUserMapper.insertSelective(clinicUser);
		gzptSubscribeInfo.setCustomerId(clinicUser.getId());
		gzptSubscribeInfo.setOpenid(openid);
		gzptSubscribeInfo.setSubscribeTime(new Date());
		this.gzptSubscribeInfoMapper.insertSelective(gzptSubscribeInfo);
	}

	@Override
	public void unBind(JSONObject params, Status status) {
		GzptSubscribeInfo gzptSubscribeInfo = this.gzptSubscribeInfoMapper
				.selectByOpenId(params.get("openid") + "");
		gzptSubscribeInfo.setSubscribe("0");
		gzptSubscribeInfo.setDateUpdated(new Date());
		this.gzptSubscribeInfoMapper
				.updateByPrimaryKeySelective(gzptSubscribeInfo);
	}
}
