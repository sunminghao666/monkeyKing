package com.monkey.gzpt.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.basic.base.util.ConfigInfo;
import com.monkey.gzpt.dao.GzptSubscribeInfoMapper;
import com.monkey.gzpt.pojo.GzptSubscribeInfo;
import com.monkey.gzpt.service.WXBasicService;
import com.monkey.gzpt.util.WXapi;
import com.monkey.taf.common.Tools;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.BasicDto;
import com.monkey.taf.web.util.Status;

@Service("wxbasicServiceImpl")
public class WXBasicServiceImpl implements WXBasicService {
    @Autowired
    private GzptSubscribeInfoMapper gzptSubscribeInfoMapper;


    @Override
    public void authorize(Status status) throws Exception {
        if ("000000".equals(status.getStatusCode())) {
            TAFLog.info("静默授权openid:" + status.getReturns().get("openid"));
            if (status.getReturns().get("openid") != null) {
                GzptSubscribeInfo gzptSubscribeInfo = this.gzptSubscribeInfoMapper.selectByOpenId((String) status
                        .getReturns().get("openid"));
                if (gzptSubscribeInfo != null && "1".equals(gzptSubscribeInfo.getSubscribe())) {
                    gzptSubscribeInfo.setDateUpdated(new Date());
                    status.getReturns().put("customerId", gzptSubscribeInfo.getCustomerId());
                    status.getReturns().put("flag", "2");                }
                else {
                    status.getReturns().put("flag", "1");
                    status.getReturns().put("customerId", null);
                }
            }
        }
    }

    @Override
    public void templateSend(BasicDto basicDto, Status status) throws Exception {
        Long customerId = Long.valueOf(basicDto.getBody().get("customerId") + "");
        String templateId = String.valueOf(basicDto.getBody().get("templateId"));
        if (Tools.isBlank(templateId)) {
            status.setStatusCode("000001");
            status.setStatusMessage("模版id不能为空！");
        }
        else {
            Map < String, Object > data = (Map < String, Object >) basicDto.getBody().get("data");

            GzptSubscribeInfo gzptSubscribeInfos = this.gzptSubscribeInfoMapper
                    .selectByCustomerIdAndSubscribe(customerId);
            if (!Tools.isBlank(gzptSubscribeInfos)) {
                WXapi.templateSend(gzptSubscribeInfos.getOpenid(), templateId,
                        String.valueOf(basicDto.getBody().get("url")), data, status);
            }
            else {
                status.setStatusCode("000001");
                status.setStatusMessage("该用户未绑定微信号！");
            }
        }

    }

    @Override
    public void WXTemporaryMaterialDownload(BasicDto basicDto, Status status) throws Exception {
        Map < String, Object > body = basicDto.getBody();
        String mediaId = Tools.valueOf(body.get("mediaId"));
        if (Tools.isBlank(mediaId)) {
            TAFLog.info("mediaId不能为空");
            status.setStatusCode("000001");
            status.setStatusMessage("mediaId不能为空");
        }
        if ("000000".equals(status.getStatusCode())) {
            String accessToken = WXapi.getAccessToken();
            String url = ConfigInfo.getByProperties("wx.temp_source") + "?access_token=" + accessToken + "&media_id="
                    + mediaId;
            String path = WXapi.downloadFileFromWX(url, status);
            status.getReturns().put("path", path);
        }

    }
}