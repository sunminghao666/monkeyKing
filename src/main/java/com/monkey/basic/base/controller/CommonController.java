package com.monkey.basic.base.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.monkey.basic.base.util.ConfigInfo;
import com.monkey.taf.common.Tools;
import com.monkey.taf.dataStructure.JSONUtil;
import com.monkey.taf.encryption.AESEncrypt;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.BasicDto;
import com.monkey.taf.web.util.Status;

@Controller
public class CommonController {
    public static JSONObject stringToObject(HttpServletRequest request) {
        try {
            // 解密秘钥
            String DencptKey = ConfigInfo.getByProperties("ejx.password");
            // 加密后的参数
            String beforeParam = request.getParameter("jsonKey");
            // 解密后的参

            String afterParam = AESEncrypt.aesDencrypt(DencptKey, beforeParam, DencptKey);

            // 转成json格式
            JSONObject jsonObject = JSONObject.fromObject(afterParam);
            // 获取request节点
            jsonObject = JSONUtil.parseObjectToJSONObject(jsonObject.get("request").toString());

            return jsonObject;
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @方法名：initParams
     * @方法描述【方法功能描述】1.接收jsonkey；2.解密密文；3.报文封装
     * @param status
     * @param basicDto
     * @param request
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年10月15日 下午4:27:11
     * @修改人：cc
     * @修改时间：2018年10月15日 下午4:27:11
     */
    protected void initParams(Status status, BasicDto basicDto, HttpServletRequest request) {
        // 解密秘钥
        String DencptKey = "t171420100302rsa";
        if (Tools.isBlank(DencptKey)) {
            status.setStatusCode("900003");
            status.setStatusMessage("错误原因：渠道秘钥不存在！");
        }
        else {
            // 加密后的参数
            String beforeParam = request.getParameter("jsonKey");
            if (Tools.isBlank(beforeParam)) {
                status.setStatusCode("900004");
                status.setStatusMessage("错误原因：渠道报文为空！");
            }
            else {
                // 解密后的参数
                String afterParam = null;
                try {
                    afterParam = AESEncrypt.aesDencrypt(DencptKey, beforeParam, DencptKey);
                }
                catch (Exception e) {
                    status.setStatusCode("900005");
                    status.setStatusMessage("错误原因：渠道报文解析错误！");
                }
                if ("000000".equals(status.getStatusCode())) {
                    TAFLog.info("接收前端报文：" + afterParam);
                    try {
                        // 转成json格式
                        JSONObject jsonObject = JSONObject.fromObject(afterParam);
                        basicDto.setBodyJSONObject(jsonObject.getJSONObject("body"));
                        basicDto.setHeadJSONObject(jsonObject.getJSONObject("head"));
                        Map < String, Object > head = JSONUtil.parseJSON2Map(jsonObject.getJSONObject("head"));
                        head.put("ip", this.getIpAddress(request));
                        basicDto.setHead(head);
                        basicDto.setBody(JSONUtil.parseJSON2Map(jsonObject.getJSONObject("body")));
                        status.setChannel(head.get("channelCode") + "");
                        if (Tools.isNotBlank(basicDto.getHead().get("customerId"))
                                && !(basicDto.getHead().get("customerId") + "").equals("null")) {
                            status.setCustomerId(Long.valueOf(basicDto.getHead().get("customerId").toString()));
                        }
                        status.setTransToken(Tools.toStr(basicDto.getHead().get("transToken")));
                        status.setUserCode(Tools.toStr(basicDto.getHead().get("userCode")));
                        status.setTransSerialNumber(Tools.toStr(basicDto.getHead().get("transSerialNumber")));
                    }
                    catch (Exception e) {
                        TAFLog.error("报文解析错误！", e);
                        status.setStatusCode("900006");
                        status.setStatusMessage("错误原因：报文格式错误！");

                    }

                }
            }
        }

    }

    /**
     * 向页面写数据
     * 
     * @param obj
     * @param resp
     */
    protected void writeToPage(Object obj, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");// 设置编码
            resp.setHeader("Cache-Control", "no-cache");
            PrintWriter out = resp.getWriter();
            Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
            TAFLog.info("平台返回参数" + gson.toJson(obj));
            out.print(gson.toJson(obj));
        }
        catch (Exception e) {
            TAFLog.error("向页面输出数据失败:" + obj.toString(), e);
        }
    }

    protected String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        try {

            String ip = request.getHeader("X-Forwarded-For");

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            }
            else if (ip.length() > 15) {
                String[] ips = ip.split(",");
                for (int index = 0; index < ips.length; index++) {
                    String strIp = (String) ips[index];
                    if (!("unknown".equalsIgnoreCase(strIp))) {
                        ip = strIp;
                        break;
                    }
                }
            }
            return ip;

        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * 
     * @param URL url地址
     * @return url请求参数部分
     */
    protected Map < String, String > URLRequest(String URL) {
        Map < String, String > mapRequest = new HashMap < String, String >();

        String[] arrSplit = null;

        // String strUrlParam = TruncateUrlPage(URL);
        if (URL == null) {
            return mapRequest;
        }
        // 每个键值为一组 www.2cto.com
        arrSplit = URL.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            }
            else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 解析出url请求的路径，包括页面
     * 
     * @param strURL url地址
     * @return url路径
     */
    protected String UrlPage(String strURL) {
        String strPage = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            if (arrSplit.length > 1) {
                if (arrSplit[0] != null) {
                    strPage = arrSplit[0];
                }
            }
        }

        return strPage;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     * 
     * @param strURL url地址
     * @return url请求参数部分
     */
    protected String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

}
