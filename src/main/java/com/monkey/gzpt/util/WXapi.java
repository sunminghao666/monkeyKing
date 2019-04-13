package com.monkey.gzpt.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.monkey.basic.base.util.ConfigInfo;
import com.monkey.gzpt.dao.GzptInfoMapper;
import com.monkey.gzpt.dto.WxConfig;
import com.monkey.gzpt.pojo.GzptInfo;
import com.monkey.gzpt.pojo.GzptSubscribeInfo;
import com.monkey.taf.common.Tools;
import com.monkey.taf.context.SpringUtil;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Status;

/**
 * 统一整合微信方法
 * 
 * @author cc
 * 
 */
public class WXapi {
 
    /**
     * 
     * 【方法功能描述】获取AccessToken的方法
     * 
     * 公众号用户名
     * 
     * @return 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:32:11
     */
    public static String getAccessToken() {
        Map < String, String > params = new HashMap < String, String >();
        String accessToken = null;
        GzptInfoMapper gzptInfoMapper = SpringUtil.getBean("gzptInfoMapper", GzptInfoMapper.class);
        List < GzptInfo > list = gzptInfoMapper.selectByUserName(ConfigInfo.getByProperties("wx.userName"));// 在数据库中查询
        if (list.size() >= 0) {
            GzptInfo gzptInfo = list.get(0);
            if (Tools.isBlank(gzptInfo.getAccessToken()) || Tools.isBlank(gzptInfo.getAccessTokenUpdateTime())
                    || (new Date().getTime() - gzptInfo.getAccessTokenUpdateTime().getTime()) > 7200 * 1000) {// 判断access_token的时效（两小时）
                params.put("grant_type", "client_credential");
                params.put("appid", gzptInfo.getAppId());
                params.put("secret", gzptInfo.getAppsecret());
                String backStr = WXmutual.http("GET", ConfigInfo.getByProperties("wx.accesstokenurl"), params);// 请求微信获取accessToken
                try {
                    Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
                    if (map.containsKey("access_token")) {// 判断map中是否存在access_token；确定返回报文是否正确
                        accessToken = (String) map.get("access_token");
                        gzptInfo.setAccessToken(accessToken);
                        gzptInfo.setAccessTokenUpdateTime(new Date());
                        gzptInfoMapper.updateByPrimaryKeySelective(gzptInfo);// 将最新的access_token更新到数据库中
                    }
                    else {
                        TAFLog.info("获取access_token返回报文" + backStr);
                    }
                }
                catch (Exception e) {
                    TAFLog.error("获取access_token请求异常", e);
                    return null;
                }
            }
            else {
                accessToken = gzptInfo.getAccessToken();
            }
        }
        return accessToken;
    }

    /**
     * 
     * 【方法功能描述】获取jsapi_ticket
     * 
     * 或数据库中超过两个小时则重新获取；重新获取时若accessToken超过两个小时则重新获取accessToken再获取jsapi_ticket；
     * 
     * 
     * 公众号用户名
     * 
     * @return 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:33:01
     */
    public static String getJsapiTicket() {
        GzptInfoMapper gzptInfoMapper = SpringUtil.getBean("gzptInfoMapper", GzptInfoMapper.class);
        List < GzptInfo > gzptInfos = gzptInfoMapper.selectByUserName(ConfigInfo.getByProperties("wx.userName"));
        if (gzptInfos.size() > 0) {
            GzptInfo gzptInfo = gzptInfos.get(0);
            // 若时间或者凭证一个为空或超出7200秒则从新获取
            if (Tools.isBlank(gzptInfo.getJsapiTicket()) || Tools.isBlank(gzptInfo.getJsapiTicketUpdateTime())
                    || (new Date().getTime() - gzptInfo.getJsapiTicketUpdateTime().getTime()) > 7200 * 1000) {
                String accesstoken = WXapi.getAccessToken();
                if (accesstoken == null) {
                    return null;
                }
                Map < String, String > params = new HashMap < String, String >();
                params.put("access_token", accesstoken);
                params.put("type", "jsapi");
                String backStr = WXmutual.http("GET", ConfigInfo.getByProperties("wx.getticket"), params);
                try {
                    Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
                    if ((Integer) map.get("errcode") == 0) {
                        String ticket = (String) map.get("ticket");
                        if (!Tools.isBlank(ticket)) {
                            GzptInfo gzptInfo1 = new GzptInfo();
                            gzptInfo1.setId(gzptInfo.getId());
                            gzptInfo1.setJsapiTicket(ticket);
                            gzptInfo1.setJsapiTicketUpdateTime(new Date());
                            gzptInfoMapper.updateByPrimaryKeySelective(gzptInfo1);
                            return ticket;
                        }
                        else {
                            return null;
                        }

                    }
                    else {
                        TAFLog.error(" 获取jsapi_ticket:" + backStr);
                        return null;
                    }
                }
                catch (Exception e) {
                    TAFLog.error("解析微信返回值异常，返回值为：" + backStr, e);
                    return null;
                }
            }
            else {
                return gzptInfo.getJsapiTicket();
            }
        }
        else {
            return null;
        }

    }

    /**
     * 
     * 【方法功能描述】根据时间戳，随机生成字符串和jsapi_ticket来进行签名
     * 
     * 
     * @param url 前端地址
     * @return 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:33:40
     */
    public static WxConfig getJsapiTicket(String url) {
        GzptInfoMapper gzptInfoMapper = SpringUtil.getBean("gzptInfoMapper", GzptInfoMapper.class);
        String jsapi_ticket = WXapi.getJsapiTicket();
        if (jsapi_ticket == null) {
            return null;
        }
        try {
            List < GzptInfo > gzptInfos = gzptInfoMapper.selectByUserName(ConfigInfo.getByProperties("wx.userName"));
            if (gzptInfos.size() > 0) {
                GzptInfo gzptInfo = gzptInfos.get(0);
                Map < String, String > map = WXUtil.sign(jsapi_ticket, url);
                WxConfig wxConfig = new WxConfig();
                wxConfig.setAppId(gzptInfo.getAppId());
                wxConfig.setNonceStr(map.get("nonceStr"));
                wxConfig.setSignature(map.get("signature"));
                wxConfig.setTimestamp(map.get("timestamp"));
                return wxConfig;
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            TAFLog.error("微信签名异常", e);
        }
        return null;
    }

    /**
     * 
     * 【方法功能描述】创建自定义菜单
     * 
     * 
     * @param json 菜单自定义json
     * @return 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:35:14
     */
    public static String createMenu(String json) {
        String str = null;
        try {
            String access_token = getAccessToken();
            str = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.createmenu") + "?access_token=" + access_token,
                    json);
            TAFLog.info("创建菜单返回" + str);
        }
        catch (Exception e) {
            TAFLog.error("创建菜单返回异常", e);
        }
        return str;
    }

    /**
     * 
     * 【方法功能描述】查询菜单
     * 
     * 
     * @return 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:35:49
     */
    public static String getMenu() {
        String str = null;
        try {
            Map < String, String > params = new HashMap < String, String >();
            String accesstoken = WXapi.getAccessToken();
            if (accesstoken == null) {
                return null;
            }
            params.put("access_token", accesstoken);
            str = WXmutual.http("GET", ConfigInfo.getByProperties("wx.getMenu"), params);
            TAFLog.info("查询自定义菜单" + str);
        }
        catch (Exception e) {
            TAFLog.error("查询自定义菜单异常", e);
        }
        return str;
    }

    /*************************************** 用户标签管理 *****************************************************/
    /**
     * 
     * 【方法功能描述】1. 创建标签 一个公众号，最多可以创建100个标签。
     * 
     * 
     * @param name 标签名
     * @param status
     * 
     *            【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:36:38
     */
    public static void tags_create(String name, Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, Object > map1 = new HashMap < String, Object >();
            Map < String, Object > map2 = new HashMap < String, Object >();
            map2.put("name", name);
            map1.put("tag", map2);
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.tags_create") + "?access_token="
                    + access_token, gson.toJson(map1));
            TAFLog.info("微信:" + backStr);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
            else {
                status.getReturns().put("id", (String) map.get("id"));
                status.getReturns().put("name", (String) map.get("name"));
            }
        }
        catch (Exception e) {
            TAFLog.error("创建标签异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("创建标签异常");
        }
    }

    /**
     * 
     * 【方法功能描述】2. 获取公众号已创建的标签
     * 
     * 
     * @param status
     * 
     *            【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:39:45
     */
    public static void tagsGet(Status status) {
        try {
            String access_token = getAccessToken();
            String backStr = WXmutual.http("GET", ConfigInfo.getByProperties("wx.tagsGet") + "?access_token="
                    + access_token, null);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
            else {
                status.getReturns().put("tags", map);
            }
        }
        catch (Exception e) {
            TAFLog.error("获取公众号已创建的标签异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("获取公众号已创建的标签异常");
        }
    }

    /**
     * 
     * 【方法功能描述】 3. 编辑标签
     * 
     * 
     * @param id 标签id
     * @param name 标签名
     * @param status
     * 
     *            【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:45:49
     */
    public static void tagsUpdate(String id, String name, Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, Object > map1 = new HashMap < String, Object >();
            Map < String, Object > map2 = new HashMap < String, Object >();
            map2.put("id", id);
            map2.put("name", name);
            map1.put("tag", map2);
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Map < String, String > params = new HashMap < String, String >();
            params.put("body", gson.toJson(map1));
            // params.put("body", json);
            System.out.println(gson.toJson(map1));
            String backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.tagsUpdate") + "?access_token="
                    + access_token, gson.toJson(map1));
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
        }
        catch (Exception e) {
            TAFLog.error("编辑标签异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("编辑标签异常");
        }
    }

    /**
     * 
     * 【方法功能描述】
     * 
     * 4. 删除标签
     * 
     * 请注意，当某个标签下的粉丝超过10w时，后台不可直接删除标签。此时，开发者可以对该标签下的openid列表， 先进行取消标签的操作，直到粉丝数不超过10w后，才可直接删除该标签。
     * 
     * 
     * @param id 标签id
     * @param status 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:47:45
     */
    public static void tagsDelete(String id, Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, Object > map1 = new HashMap < String, Object >();
            Map < String, Object > map2 = new HashMap < String, Object >();
            map2.put("id", id);
            map1.put("tag", map2);
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Map < String, String > params = new HashMap < String, String >();
            params.put("body", gson.toJson(map1));
            // params.put("body", json);
            String backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.tagsDelete") + "?access_token="
                    + access_token, gson.toJson(map1));
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
        }
        catch (Exception e) {
            TAFLog.error("删除标签异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("删除标签异常");
        }
    }

    /**
     * 
     * 【方法功能描述】5. 获取标签下粉丝列表
     * 
     * 
     * @param tagid 标签id
     * @param next_openid
     * @param status 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:48:16
     */
    public static void userTagGet(String tagid, String next_openid, Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, String > jsonMap = new HashMap < String, String >();
            jsonMap.put("tagid", tagid);
            jsonMap.put("next_openid", next_openid);
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Map < String, String > params = new HashMap < String, String >();
            params.put("body", gson.toJson(jsonMap));
            // params.put("body", json);
            String backStr = WXmutual.http("GET", ConfigInfo.getByProperties("wx.userTagGet") + "?access_token="
                    + access_token, params);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
            else {
                status.getReturns().put("count", (Integer) map.get("count"));

                status.getReturns().put("data", map.get("data"));

                status.getReturns().put("next_openid", (String) map.get("next_openid"));
            }
        }
        catch (Exception e) {
            TAFLog.error("获取标签下粉丝列表异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("获取标签下粉丝列表异常");
        }
    }

    /**
     * 
     * 【方法功能描述】1. 批量为用户打标签
     * 
     * 
     * @param openid_list 用户openid列表
     * @param tagid 标签id
     * @param status
     * 
     *            【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:51:30
     */
    public static void tagsMembersBatchtagging(String[] openid_list, String tagid, Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, Object > jsonMap = new HashMap < String, Object >();
            jsonMap.put("openid_list[]", openid_list);
            jsonMap.put("tagid", tagid);
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Map < String, String > params = new HashMap < String, String >();
            params.put("body", gson.toJson(jsonMap));
            // params.put("body", json);
            String backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.tagsMembersBatchtagging")
                    + "?access_token=" + access_token, gson.toJson(jsonMap));
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
        }
        catch (Exception e) {
            TAFLog.error("批量为用户打标签异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("批量为用户打标签异常");
        }
    }

    /**
     * 
     * 【方法功能描述】 2. 批量为用户取消打标签
     * 
     * 
     * @param openid_list 用户openid列表
     * @param tagid 标签id
     * @param status
     * 
     *            【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:52:52
     */
    public static void tagsMembersBatchuntagging(String[] openid_list, String tagid, Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, Object > jsonMap = new HashMap < String, Object >();
            jsonMap.put("openid_list[]", openid_list);
            jsonMap.put("tagid", tagid);
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Map < String, String > params = new HashMap < String, String >();
            params.put("body", gson.toJson(jsonMap));
            // params.put("body", json);
            String backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.tagsMembersBatchtagging")
                    + "?access_token=" + access_token, gson.toJson(jsonMap));
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
        }
        catch (Exception e) {
            TAFLog.error("取消打标签异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("取消打标签异常");
        }
    }

    /**
     * 
     * 【方法功能描述】3. 获取用户身上的标签列表
     * 
     * 
     * @param openid 用户id
     * @param status
     * 
     *            【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:56:13
     */
    public static void tagsGetidlist(String openid, Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, String > jsonMap = new HashMap < String, String >();
            jsonMap.put("openid", openid);

            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Map < String, String > params = new HashMap < String, String >();
            params.put("body", gson.toJson(jsonMap));
            // params.put("body", json);
            String backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.tagsGetidlist") + "?access_token="
                    + access_token, gson.toJson(jsonMap));
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
            else {
                status.getReturns().put("tagid_list", map.get("tagid_list"));
            }
        }
        catch (Exception e) {
            TAFLog.error("获取用户身上的标签列表异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("获取用户身上的标签列表异常");
        }
    }

    /*************************************** 用户管理 *****************************************************/

    /**
     * 开发者可以通过该接口对指定用户设置备注名
     * 
     * @param openid openid
     * @param remark 备注名
     * @return
     */
    public static void userInfoUpdateremark(String openid, String remark, Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, String > jsonMap = new HashMap < String, String >();
            jsonMap.put("openid", openid);
            jsonMap.put("remark", remark);
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.user_info_updateremark")
                    + "?access_token=" + access_token, gson.toJson(jsonMap));
            TAFLog.info(backStr);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
        }
        catch (Exception e) {
            TAFLog.error("修改用户别名异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("修改用户别名异常");
        }
    }

    /**
     * 
     * 【方法功能描述】获取单个用户基本信息
     * 
     * 
     * @param openId 用户id
     * @param status
     * 
     *            【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:58:22
     */
    public static void searchSingleUserInfo(String openId, Status status) {

        // 返回报文
        String backStr = "";

        try {

            // 获取“调用接口凭证”(access_token)
            String access_token = getAccessToken();

            // 拼装请求报文
            Map < String, String > requestMap = new HashMap < String, String >();
            requestMap.put("access_token", access_token);
            requestMap.put("openid", openId);
            requestMap.put("lang", "zh_CN");

            // 返回报文
            backStr = WXmutual.http("GET", ConfigInfo.getByProperties("wx.single_user_info"), requestMap);
            TAFLog.info("backStr:" + backStr);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage("获取用户信息异常");
            }
            else {
                GzptSubscribeInfo gzptSubscribeInfo = new GzptSubscribeInfo();
                gzptSubscribeInfo.setOpenid((String) map.get("openid"));
                gzptSubscribeInfo.setUnionid((String) map.get("unionid"));
                gzptSubscribeInfo.setSubscribe((String) map.get("subscribe"));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Integer time = (Integer) map.get("subscribe_time");
                String d = format.format(time);
                Date date = format.parse(d);
                gzptSubscribeInfo.setSubscribeTime(date);
                gzptSubscribeInfo.setNickname((String) map.get("nickname"));
                gzptSubscribeInfo.setHeadimgurl((String) map.get("headimgurl"));
                gzptSubscribeInfo.setSex((String) map.get("sex"));
                gzptSubscribeInfo.setLanguage((String) map.get("language"));
                gzptSubscribeInfo.setCountry((String) map.get("city"));
                gzptSubscribeInfo.setProvince((String) map.get("province"));
                gzptSubscribeInfo.setCity((String) map.get("country"));
                gzptSubscribeInfo.setRemark((String) map.get("remark"));
                gzptSubscribeInfo.setGroupid(((Integer) map.get("groupid")).toString());
                gzptSubscribeInfo.setTagidList(map.get("tagid_list").toString());
                status.getReturns().put("gzptSubscribeInfo", gzptSubscribeInfo);
            }
        }
        catch (Exception e) {
            TAFLog.error("获取单个用户基本信息异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("获取单个用户基本信息异常");
        }
    }

    /**
     * 
     * 【方法功能描述】获取多个用户基本信息
     * 
     * 
     * @param openIdList 用户id列表
     * @return 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:58:57
     */
    public static String searchMoreUserInfo(List < String > openIdList) {
        // 返回报文
        String backStr = "";
        // JSON数组
        JSONArray jsonArray = new JSONArray();
        try {
            // 获取“调用接口凭证”(access_token)
            String access_token = getAccessToken();
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            // 循环遍历openIdList
            for (int i = 0; i < openIdList.size(); i++) {
                if (!Tools.isBlank(openIdList.get(i))) {
                    Map < String, String > map = new HashMap < String, String >();
                    map.put("openid", openIdList.get(i));
                    map.put("lang", "zh-CN");
                    jsonArray.add(i, map);
                }
            }
            // 组装请求报文
            Map < String, Object > requestMap = new HashMap < String, Object >();
            requestMap.put("user_list", jsonArray);
            String json = gson.toJson(requestMap);
            System.out.println(json);
            backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.more_user_info") + "?access_token="
                    + access_token, json);
            TAFLog.info("backStr:" + backStr);
        }
        catch (Exception e) {
            TAFLog.error("获取多个用户基本信息异常", e);
        }
        return backStr;
    }

    /**
     * 
     * 【方法功能描述】 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。一次拉取调用最多拉取10000个关注者的OpenID，
     * 可以通过多次拉取的方式来满足需求。
     * 
     * 
     * @param openId 第一个拉取的OPENID，不填默认从头开始拉取
     * @return 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午2:59:38
     */
    public static String searchUserList(String openId) {

        // 返回报文
        String backStr = "";

        try {

            // 获取“调用接口凭证”(access_token)
            String access_token = getAccessToken();

            // 拼装请求报文
            Map < String, String > requestMap = new HashMap < String, String >();
            requestMap.put("access_token", access_token);
            requestMap.put("openid", openId);

            // 返回报文
            backStr = WXmutual.http("GET", ConfigInfo.getByProperties("wx.user_list"), requestMap);
            TAFLog.info("backStr:" + backStr);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
            }
        }
        catch (Exception e) {
            TAFLog.error("获取用户列表异常", e);
        }
        return backStr;
    }

    /*************************** 消息管理 *******************************************************/

    /********* 模版消息管理 **********/

    /**
     * 
     * 设置行业信息
     * 
     * 设置行业可在MP中完成，每月可修改行业1次，账号仅可使用所属行业中相关的模板，为方便第三方开发者，提供通过接口调用的方式来修改账号所属行业， 具体如下： 主行业 副行业 代码 //IT科技 互联网/电子商务 1 //IT科技
     * IT软件与服务 2 //IT科技 IT硬件与设备 3 //IT科技 电子技术 4 //IT科技 通信与运营商 5 //IT科技 网络游戏 6 //金融业 银行 7 //金融业 基金|理财|信托 8 //金融业 保险 9
     * //餐饮 餐饮 10 //酒店旅游 酒店 11 //酒店旅游 旅游 12 //运输与仓储 快递 13 //运输与仓储 物流 14 //运输与仓储 仓储 15 //教育 培训 16 //教育 院校 17 //政府与公共事业
     * 学术科研 18 //政府与公共事业 交警 19 //政府与公共事业 博物馆 20 //政府与公共事业 公共事业|非盈利机构 21 //医药护理 医药医疗 22 //医药护理 护理美容 23 //医药护理 保健与卫生 24
     * //交通工具 汽车相关 25 //交通工具 摩托车相关 26 //交通工具 火车相关 27 //交通工具 飞机相关 28 //房地产 建筑 29 //房地产 物业 30 //消费品 消费品 31 //商业服务 法律 32
     * //商业服务 会展 33 //商业服务 中介服务 34 //商业服务 认证 35 //商业服务 审计 36 //文体娱乐 传媒 37 //文体娱乐 体育 38 //文体娱乐 娱乐休闲 39 //印刷 印刷 40 //其它 其它
     * 41
     * 
     * @param industry_id1 主行业
     * @param industry_id2 父行业
     * @return
     */
    public static void templateApiSetIndustry(String industry_id1, String industry_id2, Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, String > jsonMap = new HashMap < String, String >();
            jsonMap.put("industry_id1", industry_id1);
            jsonMap.put("industry_id2", industry_id2);
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.template_api_set_industry")
                    + "?access_token=" + access_token, gson.toJson(jsonMap));
            TAFLog.info(backStr);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
        }
        catch (Exception e) {
            TAFLog.error("设置行业信息异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("设置行业信息异常");
        }
    }

    /**
     * 获取模版行业信息
     * 
     * @return
     */
    public static void templateGetIndustry(Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装系统报文
            String backStr = WXmutual.http("GET", ConfigInfo.getByProperties("wx.template_get_industry")
                    + "?access_token=" + access_token, null);
            TAFLog.info(backStr);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
            else {
                status.getReturns().put("backStr", backStr);
            }
        }
        catch (Exception e) {
            TAFLog.error("获取模版行业信息异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("获取模版行业信息异常");
        }
    }

    /**
     * 获取模版信息
     * 
     * @return
     */
    public static void templateGetAllPrivateTemplate(Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装系统报文
            String backStr = WXmutual.http("GET", ConfigInfo.getByProperties("wx.template_get_all_private_template")
                    + "?access_token=" + access_token, null);
            TAFLog.info(backStr);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
            else {
                status.getReturns().put("backStr", backStr);
            }
        }
        catch (Exception e) {
            TAFLog.error("获取模版信息异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("获取模版信息异常");
        }
    }

    /**
     * 推送模版信息
     * 
     * @param touser 接收者的openid
     * @param template_id 模版id
     * @param url 链接地址
     * @param data 模版信息
     * 
     *            { "touser":"OPENID", "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
     *            "url":"http://weixin.qq.com/download", "data":{ "first": { "value":"恭喜你购买成功！", "color":"#173177" },
     *            "keynote1":{ "value":"巧克力", "color":"#173177" }, "keynote2": { "value":"39.8元", "color":"#173177" },
     *            "keynote3": { "value":"2014年9月22日", "color":"#173177" }, "remark":{ "value":"欢迎再次购买！",
     *            "color":"#173177" } } }
     * 
     * 
     * @return
     */
    public static void templateSend(String touser, String template_id, String url, Map < String, Object > data,
            Status status) {
        try {
            String access_token = getAccessToken();
            // 拼装业务报文
            Map < String, Object > jsonMap = new HashMap < String, Object >();
            jsonMap.put("touser", touser);
            jsonMap.put("template_id", template_id);
            jsonMap.put("url", url);
            jsonMap.put("data", data);
            // 拼装系统报文
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            TAFLog.info(gson.toJson(jsonMap));
            String backStr = WXmutual.httpWXPost(ConfigInfo.getByProperties("wx.template_send") + "?access_token="
                    + access_token, gson.toJson(jsonMap));
            TAFLog.info(backStr);
            Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
            if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                status.setStatusCode("000001");
                status.setStatusMessage((String) map.get("errmsg"));
            }
            else {
                status.getReturns().put("backStr", backStr);
            }
        }
        catch (Exception e) {
            TAFLog.error("获取模版信息异常", e);
            status.setStatusCode("000001");
            status.setStatusMessage("获取模版信息异常");
        }
    }

    /**
     * 
     * 【方法功能描述】微信用户授权
     * 
     * 
     * @param code
     * @param status 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年2月28日 下午3:06:44
     */
    public static void wxAuthorize(String code, Status status) {
        try {
            GzptInfoMapper gzptInfoMapper = SpringUtil.getBean("gzptInfoMapper", GzptInfoMapper.class);
            // 拼装业务报文
            Map < String, String > jsonMap = new HashMap < String, String >();
            System.out.println(ConfigInfo.getByProperties("wx.userName"));
            List < GzptInfo > list = gzptInfoMapper.selectByUserName(ConfigInfo.getByProperties("wx.userName"));// 在数据库中查询
            if (list.size() >= 0) {
                GzptInfo gzptInfo = list.get(0);
                TAFLog.info(gzptInfo.getAppId());
                TAFLog.info(gzptInfo.getAppsecret());
                TAFLog.info(code);
                jsonMap.put("appid", gzptInfo.getAppId());
                jsonMap.put("secret", gzptInfo.getAppsecret());
                jsonMap.put("code", code);
                jsonMap.put("grant_type", "authorization_code");

                // 拼装系统报文
                Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                TAFLog.info(gson.toJson(jsonMap));
                String backStr = WXmutual.http("GET", ConfigInfo.getByProperties("wx.authorize"), jsonMap);

                TAFLog.info(backStr);
                Map < Object, Object > map = WXmutual.parseJSON2Map(backStr);
                if ((Integer) map.get("errcode") != null && (Integer) map.get("errcode") != 0) {
                    status.setStatusCode("000001");
                    status.setStatusMessage((String) map.get("errmsg"));
                }
                else {
                    status.getReturns().put("openid", map.get("openid"));
                    status.getReturns().put("scope", map.get("scope"));
                    status.getReturns().put("access_token", map.get("access_token"));
                    status.getReturns().put("expires_in", map.get("expires_in"));
                }
            }
            else {
                status.setStatusCode("000001");
                status.setStatusMessage("微信公众号名称错误！");
            }

        }
        catch (Exception e) {
            TAFLog.error("微信授权异常！", e);
            status.setStatusCode("000001");
            status.setStatusMessage("微信授权异常！");
        }
    }

    public static String downloadFileFromWX(String url, Status status) {
        try {
            // 统一资源
            URL uri = new URL(url);
            // 连接类的父类，抽象类
            URLConnection urlConnection = uri.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            URLConnection con = uri.openConnection();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String path = ConfigInfo.getByProperties("uploadUrl.base") + ConfigInfo.getByProperties("uploadUrl.wxPic")
                    + Tools.seveningDate() + Tools.getStringRandom(4) + "A.JPG";
//            String path ="D://a.jpg";
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buf = new byte[2048];
            int length = bin.read(buf);
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bin.read(buf);
            }
            bos.close();
            bin.close();
            httpURLConnection.disconnect();
            return path;
        }
        catch (Exception e) {
            TAFLog.error("微信图片下载异常！", e);
            status.setStatusCode("000001");
            status.setStatusMessage("微信图片下载异常！");
        }
        return null;
    }
}
