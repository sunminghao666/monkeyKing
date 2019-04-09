///**
// * @模块名称：微信公众号绑定与解绑接口实现
// * @Description
// * @version：1.0
// * @author:zq
// * @date：2018年3月26日 上午10:21:25
// */
//package com.monkey.gzpt.service.impl;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.monkey.basic.base.util.ConfigInfo;
//import com.monkey.gzpt.dao.GzptSubscribeInfoMapper;
//import com.monkey.gzpt.pojo.GzptSubscribeInfo;
//import com.monkey.gzpt.service.WXFollowService;
//import com.monkey.taf.common.Tools;
//import com.monkey.taf.log.TAFLog;
//import com.monkey.taf.web.util.BasicDto;
//import com.monkey.taf.web.util.Status;
//
///**
// * @模块名称：微信公众号绑定与解绑接口实现
// * @Description @version：1.0
// * @author：zq
// * @date：2018年3月26日 上午10:21:25
// */
//@Service
//public class WXFollowServiceImpl implements WXFollowService {
//
//    @Autowired
//    CustomerBasicMapper customerBasicMapper;
//
//    @Autowired
//    GzptSubscribeInfoMapper gzptSubscribeInfoMapper;
//
//    @Autowired
//    MessageContentMapper messageContentMapper;
//
//    @Autowired
//    CustomerHisMapper customerHisMapper;
//
//    @Autowired
//    CustomerTokenMapper customerTokenMapper;
//
//    @Override
//    public void selectAttention(Status status, BasicDto basicDto) {
//        try {
//            Map < String, Object > mapBody = basicDto.getBody();
//            if (!Tools.isBlank(mapBody)) {
//                String userName = Tools.valueOf(mapBody.get("userName"));
//                if (Tools.isBlank(userName)) {
//                    status.setStatusCode("000001");
//                    status.setStatusMessage("用户手机号不能为空！");
//                }
//                if ("000000".equals(status.getStatusCode())) {
//                    if (!Tools.isMobileNO(userName)) {
//                        status.setStatusCode("000001");
//                        status.setStatusMessage("手机号格式错误！");
//                    }
//                }
//                if ("000000".equals(status.getStatusCode())) {
//                    String register = "";// 注册 01：已注册，02：未注册
//                    String isFollow = "";// 是否关注 01已绑定微信 02未绑定微信
//                    String isFGJRegister = "";// 是否富管家用户 01是 02否
//                    String isPassWord = "";// 是否设置登录密码 01是 02否
//                    int i = (int) (Math.random() * 900000 + 100000);
//                    String code = String.valueOf(i);
//                    CustomerBasic customerBasic = customerBasicMapper.selectByUserName(userName);
//                    // 1.判断是否注册
//                    if (Tools.isBlank(customerBasic)) {
//                        register = "02";
//                    }
//                    else {
//                        register = "01";
//                        List < GzptSubscribeInfo > gzptSubscribeInfos = gzptSubscribeInfoMapper
//                                .selectByCustomerId(customerBasic.getId());
//                        // 2.判断该手机号是否绑定微信
//                        if (gzptSubscribeInfos.size() > 0) {
//                            boolean flag = false;
//                            for (GzptSubscribeInfo gzptSubscribeInfo : gzptSubscribeInfos) {
//                                if ("1".equals(gzptSubscribeInfo.getSubscribe())) {
//                                    flag = true;
//                                }
//                            }
//                            if (flag) {
//                                isFollow = "01";
//                            }
//                            else {
//                                isFollow = "02";
//                                // 3.判断是否富管家用户
//                                if (!"101".equals(customerBasic.getSource())) {
//                                    isFGJRegister = "02";
//                                }
//                                else {
//                                    isFGJRegister = "01";
//                                    // 判断是否设置过登录密码
//                                    if (!Tools.isBlank(customerBasic.getPassword())) {
//                                        isPassWord = "01";
//                                    }
//                                    else {
//                                        isPassWord = "02";
//                                    }
//                                }
//                                // 发送短信，跳转绑定微信页
//                                try {
//                                    Map < String, Object > contentMap = new HashMap < String, Object >();
//                                    List < String > phones = new ArrayList<>();
//                                    String type = "2";// 1-消息推送；2-短信；3-全部
//                                    String templateCode = "BY000019";// 模板编号 必填
//                                    String sendPerson = "system";// 系统发送为system
//                                    String messageTitle = "用户绑定微信";// 消息标题
//                                    String messageSmallType = "2";// 消息小类:1-提醒；2-通知'
//                                    Map < String, Object > messageOptions = null;// 消息展示项(短信及不展示推送可为空)
//                                    phones.add(userName);// 获取用户手机号
//                                    contentMap.put("1", code);
//                                    MessageUtil.sendSmsByContentMap(type, templateCode, contentMap, phones, sendPerson,
//                                            messageSmallType, messageOptions, messageTitle, code, status);// 微信公众号绑定
//                                }
//                                catch (Exception e) {
//                                    TAFLog.error("用户绑定微信发送验证码异常！", e);
//                                    status.setStatusCode("000001");
//                                    status.setStatusMessage("服务器繁忙，请稍后再试");
//                                }
//                            }
//                        }
//                        else {
//                            isFollow = "02";
//                            // 3.判断是否富管家用户
//                            if (!"101".equals(customerBasic.getSource())) {
//                                isFGJRegister = "02";
//                            }
//                            else {
//                                isFGJRegister = "01";
//                                // 判断是否设置过登录密码
//                                if (!Tools.isBlank(customerBasic.getPassword())) {
//                                    isPassWord = "01";
//                                }
//                                else {
//                                    isPassWord = "02";
//                                }
//                            }
//                            // 发送短信，跳转绑定微信页
//                            try {
//                                Map < String, Object > contentMap = new HashMap < String, Object >();
//                                List < String > phones = new ArrayList<>();
//                                String type = "2";// 1-消息推送；2-短信；3-全部
//                                String templateCode = "BY000019";// 模板编号 必填
//                                String sendPerson = "system";// 系统发送为system
//                                String messageTitle = "用户绑定微信";// 消息标题
//                                String messageSmallType = "2";// 消息小类:1-提醒；2-通知'
//                                Map < String, Object > messageOptions = null;// 消息展示项(短信及不展示推送可为空)
//                                phones.add(userName);// 获取用户手机号
//                                contentMap.put("1", code);
//                                MessageUtil.sendSmsByContentMap(type, templateCode, contentMap, phones, sendPerson,
//                                        messageSmallType, messageOptions, messageTitle, code, status);// 微信公众号绑定
//                            }
//                            catch (Exception e) {
//                                TAFLog.error("用户绑定微信发送验证码异常！", e);
//                                status.setStatusCode("000001");
//                                status.setStatusMessage("服务器繁忙，请稍后再试");
//                            }
//                        }
//                    }
//                    status.getReturns().put("register", register);
//                    status.getReturns().put("isFollow", isFollow);
//                    status.getReturns().put("isFGJRegister", isFGJRegister);
//                    status.getReturns().put("isPassWord", isPassWord);
//                    if (!Tools.isBlank(customerBasic)) {
//                        status.getReturns().put("customerId", customerBasic.getId());
//                        status.getReturns().put("verificationCode", code);
//                    }
//                    else {
//                        status.getReturns().put("customerId", "");
//                        status.getReturns().put("verificationCode", "");
//                    }
//                }
//            }
//            else {
//                status.setStatusCode("000001");
//                status.setStatusMessage("请求参数不能为空！");
//            }
//        }
//        catch (Exception e) {
//            TAFLog.error("查询用户是否绑定微信公众号异常", e);
//            status.setStatusCode("000001");
//            status.setStatusMessage("服务器繁忙，请稍后再试");
//        }
//    }
//
//    @Override
//    public void attention(Status status, BasicDto basicDto) {
//        try {
//            Map < String, Object > mapHead = basicDto.getHead();
//            Map < String, Object > mapBody = basicDto.getBody();
//            if (!Tools.isBlank(mapBody)) {
//                String customerId = Tools.valueOf(mapBody.get("customerId"));// 用户ID
//                String openId = Tools.valueOf(mapBody.get("openId"));// 微信openId
//                String verificationCode = Tools.valueOf(mapBody.get("verificationCode"));// 验证码
//                if (Tools.isBlank(customerId)) {
//                    status.setStatusCode("000001");
//                    status.setStatusMessage("用户ID不能为空！");
//                }
//                if ("000000".equals(status.getStatusCode())) {
//                    if (Tools.isBlank(openId)) {
//                        status.setStatusCode("000001");
//                        status.setStatusMessage("微信openId不能为空！");
//                    }
//                }
//                if ("000000".equals(status.getStatusCode())) {
//                    if (Tools.isBlank(verificationCode)) {
//                        status.setStatusCode("000001");
//                        status.setStatusMessage("验证码不能为空！");
//                    }
//                }
//                GzptSubscribeInfo gzptSubscribeInfo = gzptSubscribeInfoMapper
//                        .selectByCustomerIdAndOpenId(Long.valueOf(customerId), openId);
//                if ("000000".equals(status.getStatusCode())) {
//                    if (!Tools.isBlank(gzptSubscribeInfo)) {
//                        if("1".equals(gzptSubscribeInfo.getSubscribe())){
//                            status.setStatusCode("000001");
//                            status.setStatusMessage("该用户已绑定微信！");
//                        }
//                    }
//                }
//                //根据customerId查询微信是否绑定
//                List<GzptSubscribeInfo> isCustomerId = gzptSubscribeInfoMapper.selectByCustomerId(Long.valueOf(customerId));
//                if("000000".equals(status.getStatusCode())){
//                    if(isCustomerId.size()>0){
//                        if("1".equals(isCustomerId.get(0).getSubscribe())){
//                            status.setStatusCode("000001");
//                            status.setStatusMessage("该手机号已绑定微信！");
//                        }
//                    }
//                }
//                //查询openid是否已绑定
//                GzptSubscribeInfo isOpenId = gzptSubscribeInfoMapper.selectByOpenId(openId);
//                if("000000".equals(status.getStatusCode())){
//                    if(!Tools.isBlank(isOpenId)){
//                        if("1".equals(isOpenId.getSubscribe())){
//                            status.setStatusCode("000001");
//                            status.setStatusMessage("该微信已绑定其他手机号！");
//                        }
//                    }
//                }
//                CustomerBasic customerBasic = customerBasicMapper.selectByPrimaryKey(Long.valueOf(customerId));
//                // 1.效验验证码
//                if ("000000".equals(status.getStatusCode())) {
//                    List < MessageContent > messageContent = messageContentMapper
//                            .queryByRemark(customerBasic.getUserName());
//                    // 获取当前时间与数据库时间比较,验证码输入必须在5分钟内 300000
//                    Date date = new Date();
//                    Date dateCreated = messageContent.get(0).getDateCreated();
//                    Long record = Tools.dateMillisecond(date, dateCreated);
//                    String remarkDate = (String) messageContent.get(0).getRemark();
//                    String messageTitle = messageContent.get(0).getMessageTitle();
//                    if ("000000".equals(status.getStatusCode())) {
//                        if (remarkDate.equals(verificationCode)) {
//                            if (record > 300000) {
//                                status.setStatusCode("000001");
//                                status.setStatusMessage("验证码已失效，请重新输入！");
//                            }
//                        }
//                    }
//                    if ("000000".equals(status.getStatusCode())) {
//                        if (!remarkDate.equals(verificationCode)) {
//                            status.setStatusCode("000001");
//                            status.setStatusMessage("验证码有误，请重新输入！");
//                        }
//                    }
//                    if ("000000".equals(status.getStatusCode())) {
//                        if (!"用户绑定微信".equals(messageTitle)) {
//                            status.setStatusCode("000001");
//                            status.setStatusMessage("验证码有误，请重新输入！");
//                        }
//                    }
//                }
//                // 2.绑定
//                if ("000000".equals(status.getStatusCode())) {
//                    try {
//                        if(!Tools.isBlank(gzptSubscribeInfo)){
//                            if("2".equals(gzptSubscribeInfo.getSubscribe())){
//                                gzptSubscribeInfo.setSubscribe("1");// 是否关注 1:关注 2取消关注
//                                gzptSubscribeInfo.setSubscribeTime(new Date());// 最后一次关注时间
//                                gzptSubscribeInfo.setUpdatedBy(String.valueOf(mapHead.get("ip")));//修改人
//                                gzptSubscribeInfo.setDateUpdated(new Date());//修改时间
//                                gzptSubscribeInfoMapper.updateByPrimaryKeySelective(gzptSubscribeInfo);
//                            }
//                        }
//                        else{
//                            if(!Tools.isBlank(isOpenId)){
//                                if("2".equals(isOpenId.getSubscribe())){
//                                    isOpenId.setCustomerId(Long.valueOf(customerId));
//                                    isOpenId.setSubscribe("1");
//                                    isOpenId.setSubscribeTime(new Date());
//                                    isOpenId.setUpdatedBy(String.valueOf(mapHead.get("ip")));
//                                    isOpenId.setDateUpdated(new Date());
//                                    gzptSubscribeInfoMapper.updateByPrimaryKeySelective(isOpenId);
//                                }
//                            }else{
//                                GzptSubscribeInfo gzptSubscribeInfos = new GzptSubscribeInfo();
//                                gzptSubscribeInfos.setOpenid(openId);// openId
//                                gzptSubscribeInfos.setCustomerId(Long.valueOf(customerId));// 用户ID
//                                gzptSubscribeInfos.setSubscribe("1");// 是否关注 1:关注 2取消关注
//                                gzptSubscribeInfos.setSubscribeTime(new Date());// 最后一次关注时间
//                                gzptSubscribeInfos.setDateCreated(new Date());// 创建时间
//                                gzptSubscribeInfos.setCreatedBy(String.valueOf(mapHead.get("ip")));// 创建人
//                                gzptSubscribeInfoMapper.insertSelective(gzptSubscribeInfos);
//                            }
//                        }
//
//                        CustomerHis customerHis = new CustomerHis();
//                        customerHis.setCustomerId(Long.valueOf(customerId));// 用户ID
//                        customerHis.setOperName("用户微信绑定");// 操作名称
//                        customerHis.setOperDesc("用户微信绑定成功");// 操作描述
//                        customerHis.setOperIp(String.valueOf(mapHead.get("ip")));// 操作ip
//                        customerHis.setDateCreated(new Date());// 创建时间
//                        customerHisMapper.insert(customerHis); 
//                        
//                        basicDto.getBody().put("userName", customerBasic.getUserName());
//                        saveToken(status, basicDto);
//
//                        // 绑定成功，向用户发送微信推送消息
//                        Status statuss = new Status();
//                        Date date = new Date();
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        String createdate = sdf.format(date);
//                        String templateId=ConfigInfo.getByProperties("wx.wxby0000001");
////                        String templateId = "FfbiyvLaONU_bQgxuoyjG9uM0WPquhwTy1Y9Ep9kQjs";
//                        Map < String, Object > data = new HashMap < String, Object >();
//                        Map < String, String > first = new HashMap < String, String >();
//                        Map < String, String > keyword1 = new HashMap < String, String >();
//                        Map < String, String > keyword2 = new HashMap < String, String >();
//                        Map < String, String > remark = new HashMap < String, String >();
//                        first.put("value", "您好，恭喜您账户绑定成功！\n");
//                        keyword1.put("value", customerBasic.getUserName() + "\n");
//                        keyword2.put("value", createdate + "\n");
//                        remark.put("value", "您可以使用下方微信菜单进行更多体验。保赢金融，用保险让金融更简单。\n");
//                        data.put("first", first);
//                        data.put("keyword1", keyword1);
//                        data.put("keyword2", keyword2);
//                        data.put("remark", remark);
//                        MessageUtil.wxTemplateSend(mapHead, Long.valueOf(customerId), data, templateId, "", statuss);
//                    }
//                    catch (Exception e) {
//                        TAFLog.error("存储用户数据异常", e);
//                        status.setStatusCode("000001");
//                        status.setStatusMessage("服务器繁忙，请稍后再试");
//                    }
//                }
//                // 3.富管家用户，查询实名状态及身份验证
//                String idAuth = "";// 实名状态 01-未实名，02-已实名
//                String authentication = "";// 身份验证 01:已进行身份验证 02：未进行身份验证
//                if ("000000".equals(status.getStatusCode())) {
//                    if ("101".equals(customerBasic.getSource())) {
//                        if ("01".equals(customerBasic.getIdAuth()) || "03".equals(customerBasic.getIdAuth())) {
//                            idAuth = "01";
//                        }
//                        else {
//                            idAuth = "02";
//                        }
//                        if ("01".equals(customerBasic.getRemark())) {
//                            authentication = "01";
//                        }
//                        else {
//                            authentication = "02";
//                        }
//                    }
//                }
//                status.getReturns().put("idAuth", idAuth);
//                status.getReturns().put("authentication", authentication);
//            }
//            else {
//                status.setStatusCode("000001");
//                status.setStatusMessage("请求参数不能为空！");
//            }
//        }
//        catch (Exception e) {
//            TAFLog.error("用户绑定微信公众号异常", e);
//            status.setStatusCode("000001");
//            status.setStatusMessage("服务器繁忙，请稍后再试");
//        }
//
//    }
//
//    @Override
//    public void relieveAttention(Status status, BasicDto basicDto) {
//        try {
//            Map < String, Object > mapHead = basicDto.getHead();
//            Map < String, Object > mapBody = basicDto.getBody();
//            if (!Tools.isBlank(mapBody)) {
//                String customerId = Tools.valueOf(mapBody.get("customerId"));// 用户ID
//                String openId = Tools.valueOf(mapBody.get("openId"));// 微信openId
//                if (Tools.isBlank(customerId)) {
//                    status.setStatusCode("000001");
//                    status.setStatusMessage("用户ID不能为空！");
//                }
//                if ("000000".equals(status.getStatusCode())) {
//                    if (Tools.isBlank(openId)) {
//                        status.setStatusCode("000001");
//                        status.setStatusMessage("微信openId不能为空！");
//                    }
//                }
//                if ("000000".equals(status.getStatusCode())) {
//                    GzptSubscribeInfo gzptSubscribeInfo = gzptSubscribeInfoMapper
//                            .selectByCustomerIdAndOpenId(Long.valueOf(customerId), openId);
//                    if (!Tools.isBlank(gzptSubscribeInfo)) {
//                        gzptSubscribeInfo.setSubscribe("2");
//                        gzptSubscribeInfo.setDateUpdated(new Date());
//                        gzptSubscribeInfo.setUpdatedBy(String.valueOf(mapHead.get("ip")));
//                        gzptSubscribeInfoMapper.updateByPrimaryKeySelective(gzptSubscribeInfo);
//                        
//                        TAFLog.info("ID为：<" + customerId + ">的用户进行了解绑操作。用户openId为：<" + openId + ">");
//
//                        CustomerHis customerHis = new CustomerHis();
//                        customerHis.setCustomerId(Long.valueOf(customerId));// 用户ID
//                        customerHis.setOperName("用户微信解绑");// 操作名称
//                        customerHis.setOperDesc("用户微信解绑成功");// 操作描述
//                        customerHis.setOperIp(String.valueOf(mapHead.get("ip")));// 操作ip
//                        customerHis.setDateCreated(new Date());// 创建时间
//                        customerHisMapper.insert(customerHis);
//                    }
//                    else {
//                        status.setStatusCode("000001");
//                        status.setStatusMessage("未查询到该用户信息！");
//                    }
//                }
//            }
//            else {
//                status.setStatusCode("000001");
//                status.setStatusMessage("请求参数不能为空！");
//            }
//        }
//        catch (Exception e) {
//            TAFLog.error("用户解除绑定微信公众号异常", e);
//            status.setStatusCode("000001");
//            status.setStatusMessage("服务器繁忙，请稍后再试");
//        }
//
//    }
//
//    private void saveToken(Status status, BasicDto basicDto) {
//        CustomerBasic customerBasic = customerBasicMapper
//                .selectByUserName(basicDto.getBody().get("userName").toString());
//        if (!Tools.isBlank(customerBasic)) {
//            Map < String, Object > params = new HashMap < String, Object >();
//            params.put("customerId", customerBasic.getId());
//            params.put("channelCode", Long.valueOf(basicDto.getHead().get("channelCode").toString()));
//            CustomerToken customerToken = this.customerTokenMapper.selectByParams(params);
//            String token = "";
//            if (Tools.isBlank(customerToken)) {
//                customerToken = new CustomerToken();
//                customerToken.setCustomerId(customerBasic.getId());
//                customerToken.setLastLoginTime(new Date());
//                customerToken.setLoginIp(basicDto.getHead().get("ip").toString());
//                customerToken.setLoginMethod(basicDto.getHead().get("channelCode").toString());
//                String cusStr = customerBasic.getUserName() + Tools.seveningDate();
//                token = MD5Utils.encrypt(cusStr);
//                customerToken.setToken(token);
//                this.customerTokenMapper.insertSelective(customerToken);
//            }
//            else {
//                customerToken.setLastLoginTime(new Date());
//                customerToken.setLoginIp(basicDto.getHead().get("ip").toString());
//                customerToken.setLoginMethod(basicDto.getHead().get("channelCode").toString());
//                String cusStr = customerBasic.getUserName() + Tools.seveningDate();
//                token = MD5Utils.encrypt(cusStr);
//                customerToken.setToken(token);
//                this.customerTokenMapper.updateByPrimaryKeySelective(customerToken);
//            }
//            status.getReturns().put("customerToken", customerToken.getToken());
//            CustomerHis customerHis = new CustomerHis();
//            customerHis.setCustomerId(customerBasic.getId());// 用户ID
//            customerHis.setOperName("用户登录");// 操作名称
//            customerHis.setOperDesc(basicDto.getHead().get("channelCode").toString());// 操作描述
//            customerHis.setOperIp((String) basicDto.getHead().get("ip"));// 操作ip
//            customerHis.setDateCreated(new Date());// 创建时间
//            customerHis.setRemark(token);
//            customerHisMapper.insertSelective(customerHis);
//        }
//        else {
//            status.setStatusCode("104004");
//            status.setStatusMessage("该用户还未注册！");
//        }
//    }
//
//}
