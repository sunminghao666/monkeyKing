package com.monkey.taf.web.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.web.util
 * @类名称： Status
 * @类描述：【类描述】统一响应封装
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年9月19日上午9:40:04
 */
public class Status {
    private Long customerId = null;// 用户唯一标识

    // 返回中间平台的 response 部分 接收状态类
    private String userCode = "";// 用户唯一标识

    private String channel = "";// 渠道

    private String transTime = "";// 交易时间

    private String transToken = "";// 交易token

    private String transSerialNumber = "";// 交易流水号

    private boolean isTrue = true;// 交易流水号

    private String statusCode = "";

    private String statusMessage = "";

    private Map < Object, Object > returns;

    public Status() {
        this.statusCode = "000000";
        this.statusMessage = "";
        this.returns = new HashMap < Object, Object >();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        if (!"000000".equals(statusCode)) {
            isTrue = false;
        }
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map < Object, Object > getReturns() {
        return returns;
    }

    public void setReturns(Map < Object, Object > returns) {
        this.returns = returns;
    }

    public String getTransToken() {
        return transToken;
    }

    public void setTransToken(String transToken) {
        this.transToken = transToken;
    }

    public String getTransSerialNumber() {
        return transSerialNumber;
    }

    public void setTransSerialNumber(String transSerialNumber) {
        this.transSerialNumber = transSerialNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }

    public Status(String userCode, String channel, String transTime, String transToken, String statusCode,
            String statusMessage, Map < Object, Object > returns) {
        super();
        this.userCode = userCode;
        this.channel = channel;
        this.transTime = transTime;
        this.transToken = transToken;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.returns = returns;
    }

    @Override
    public String toString() {
        return "Status [userCode=" + userCode + ", channel=" + channel + ", transTime=" + transTime + ", transToken="
                + transToken + ", statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", returns="
                + returns + "]";
    }

}
