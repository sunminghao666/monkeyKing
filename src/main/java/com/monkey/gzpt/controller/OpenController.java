package com.monkey.gzpt.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monkey.basic.base.controller.CommonController;
import com.monkey.gzpt.service.BasicEventService;
import com.monkey.taf.dataStructure.XmlToJson;
import com.monkey.taf.log.TAFLog;

/**
 * 
 * @模块名称：微信平台请求入口
 * @Description
 * @version：1.0
 * @author：cc
 * @date：2018年2月8日 下午4:37:36
 */
@Controller
@RequestMapping("open")
public class OpenController extends CommonController {
    @Autowired
    BasicEventService basicEventService;

    @RequestMapping("init")
    public void init(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            // 若请求为get
            if (request.getMethod().equals("GET")) {
                // 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
                //
                // 1）将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信

                // 微信加密签名
                String signature = request.getParameter("signature");
                // 时间戳
                String timestamp = request.getParameter("timestamp");
                // 随机数
                String nonce = request.getParameter("nonce");
                // 随机字符串
                String echostr = request.getParameter("echostr");

                TAFLog.info(signature + timestamp + nonce + echostr);
                // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
                // if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
                // }
                out.close();
                out = null;

            }
            else {

                String baowen = "";
                String backBaowen = "";

                response.setCharacterEncoding("UTF-8");
                BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
                String line = "";
                // 获取报文
                while ((line = br.readLine()) != null) {
                    baowen += line;
                }

                if (baowen != null) {
                    TAFLog.info("接入接口请求明文报文xml格式：" + baowen);
                    baowen = XmlToJson.xml2JSON(baowen);
                    TAFLog.info("接入接口请求明文报文json格式：" + baowen);
                    JSONObject jsonObject = JSONObject.fromObject(baowen);
                    jsonObject = jsonObject.getJSONObject("xml");
                    // 事件推送
                    if ("event".equals(jsonObject.getString("MsgType")) && jsonObject.containsKey("Event")) {
                        // 关注
                        if ("subscribe".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入关注事件");
                            backBaowen = this.basicEventService.subscribe(jsonObject, backBaowen);
                        }
                        // 取消关注
                        if ("unsubscribe".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入取消关注事件");
                            backBaowen = this.basicEventService.unsubscribe(jsonObject, backBaowen);
                        }

                        // 2. 用户已关注时的事件推送

                        if ("SCAN".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入扫描带参数二维码事件");
                            backBaowen = this.basicEventService.scan(jsonObject, backBaowen);
                        }
                        // 上报地理位置事件

                        if ("LOCATION".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入上报地理位置事件");
                            backBaowen = this.basicEventService.location(jsonObject, backBaowen);
                        }
                        /********************************* 自定义菜单事件 ***********************************/
                        // 自定义菜单事件
                        // 用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。
                        if ("CLICK".equals(jsonObject.getString("Event"))) {

                            TAFLog.info("进入用户点击自定义菜单事件");
                            // 如果是点击“接入客服”按钮的情况下
                            if ("CUSTOMER_SERVICE".equals(jsonObject.getString("EventKey"))) {
                                TAFLog.info("1用户点击“接入客服”，转入客服系统。");
                                // 转客服系统
                                backBaowen = basicEventService.gotoKefu(jsonObject, backBaowen);

                            }
                            else {
                                backBaowen = this.basicEventService.click(jsonObject, backBaowen);
                            }

                        }

                        // 点击菜单跳转链接时的事件推送

                        if ("VIEW".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入点击菜单跳转链接时的事件");
                            backBaowen = this.basicEventService.view(jsonObject, backBaowen);
                        }

                        // scancode_push：扫码推事件的事件推送

                        if ("scancode_push".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入扫码推事件的事件");
                            backBaowen = this.basicEventService.scancodePush(jsonObject, backBaowen);
                        }

                        // scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框的事件推送

                        if ("scancode_waitmsg".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入扫码推事件且弹出“消息接收中”提示框的事件");
                            backBaowen = this.basicEventService.scancodeWaitmsg(jsonObject, backBaowen);
                        }
                        // pic_sysphoto：弹出系统拍照发图的事件推送
                        if ("pic_sysphoto".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入弹出系统拍照发图的事件推送");
                            backBaowen = this.basicEventService.picSysphoto(jsonObject, backBaowen);
                        }

                        // pic_photo_or_album：弹出拍照或者相册发图的事件推送
                        if ("pic_photo_or_album".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入弹出拍照或者相册发图的事件推送");
                            backBaowen = this.basicEventService.picPhotoOrAlbum(jsonObject, backBaowen);
                        }

                        // pic_weixin：弹出微信相册发图器的事件推送
                        if ("pic_weixin".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入弹出微信相册发图器的事件推送");
                            backBaowen = this.basicEventService.picWeixin(jsonObject, backBaowen);
                        }

                        // location_select：弹出地理位置选择器的事件推送
                        if ("location_select".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入弹出地理位置选择器的事件推送");
                            backBaowen = this.basicEventService.location_select(jsonObject, backBaowen);
                        }
                        // 在模版消息发送任务完成后，微信服务器会将是否送达成功作为通知，发送到开发者中心中填写的服务器配置地址中。
                        if ("TEMPLATESENDJOBFINISH".equals(jsonObject.getString("Event"))) {
                            TAFLog.info("进入弹出地理位置选择器的事件推送");
                        }
                    }
                    else {
                        TAFLog.info("转客服系统");
                        // 转客服系统
                        // :<MsgType>text</MsgType>
                        backBaowen = basicEventService.clickToCustomerService(jsonObject, backBaowen);
                    }

                    TAFLog.info("接入接口请求返回报文明文：" + backBaowen);
                }

                out.print(backBaowen);
                out.flush();
                out.close();
            }
        }
        catch (Exception e) {
            TAFLog.error("微信服务器响应异常！", e);
        }
    }

}
