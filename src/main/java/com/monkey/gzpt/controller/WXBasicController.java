package com.monkey.gzpt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.monkey.basic.base.controller.CommonController;
import com.monkey.gzpt.dto.WxConfig;
import com.monkey.gzpt.service.WXBasicService;
import com.monkey.gzpt.util.WXapi;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.BasicDto;
import com.monkey.taf.web.util.Status;

/**
 * 微信对外controller
 * 
 * @author cc
 * 
 */
@Controller
@RequestMapping(value = "/wxBasic")
public class WXBasicController extends CommonController {
	@Autowired
	private WXBasicService wxbasicService;

	/**
	 * 
	 * 获取微信配置信息
	 * 
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/getConfig")
	public void getConfig(HttpServletRequest req, HttpServletResponse resp) {
		Status status = new Status();
		try {
			BasicDto basicDto = new BasicDto();
			super.initParams(status, basicDto, req);
			WxConfig config = WXapi.getJsapiTicket(basicDto.getBodyJSONObject()
					.getString("url"));
			if (config == null) {
				status.setStatusCode("000001");
				status.setStatusMessage("服务器繁忙，请稍后再试");
			} else {
				status.getReturns().put("config", config);
			}

		} catch (Exception e) {
			TAFLog.error("获取微信配置信息异常", e);
			status.setStatusCode("000001");
			status.setStatusMessage("服务器繁忙，请稍后再试");
		}
		super.writeToPage(status, resp);
	}

	/**
	 * 
	 * 静默授权
	 * 
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/authorize", method = RequestMethod.GET)
	public void authorize(@RequestParam String code, HttpServletRequest req,
			HttpServletResponse resp) {
		Status status = new Status();
		try {
			TAFLog.info("静默授权code:" + code);
			WXapi.wxAuthorize(code, status);
			TAFLog.info("静默授权statusCode:" + status.getStatusCode());
			status.getReturns().put("ip", req.getRemoteAddr());
			if ("000000".equals(status.getStatusCode())) {
				this.wxbasicService.authorize(status);
			}
			status.getReturns().remove("scope");
			status.getReturns().remove("expires_in");
			status.getReturns().remove("access_token");
		} catch (Exception e) {
			TAFLog.error("获取微信配置信息异常", e);
			status.setStatusCode("000001");
			status.setStatusMessage("服务器繁忙，请稍后再试");
		}
		super.writeToPage(status, resp);
	}

	/**
	 * 
	 * 模板消息发送
	 * 
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/templateSend")
	public void templateSend(HttpServletRequest req, HttpServletResponse resp) {
		Status status = new Status();
		try {
			BasicDto basicDto = new BasicDto();
			super.initParams(status, basicDto, req);
			this.wxbasicService.templateSend(basicDto, status);
		} catch (Exception e) {
			TAFLog.error("模板消息发送异常", e);
			status.setStatusCode("000001");
			status.setStatusMessage("服务器繁忙，请稍后再试");
		}
		super.writeToPage(status, resp);
	}

	/**
	 * 
	 * 【方法功能描述】微信临时素材下载
	 * 
	 * @param req
	 * @param resp
	 *            【修改描述】
	 * @version：1.0
	 * @author：liyaming
	 * @date：2018年3月27日 下午3:07:16
	 */
	@RequestMapping(value = "/downloadIdCard")
	public void WXTemporaryMaterialDownload(HttpServletRequest req,
			HttpServletResponse resp) {
		Status status = new Status();
		try {
			BasicDto basicDto = new BasicDto();
			super.initParams(status, basicDto, req);
			this.wxbasicService.WXTemporaryMaterialDownload(basicDto, status);
		} catch (Exception e) {
			TAFLog.error("微信图片下载异常！", e);
			status.setStatusCode("000001");
			status.setStatusMessage("服务器繁忙，请稍后再试");
		}
		super.writeToPage(status, resp);
	}

}
