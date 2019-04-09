///**
// * @模块名称：微信公众号绑定与解绑
// * @Description
// * @version：1.0
// * @author:zq
// * @date：2018年3月26日 上午10:06:22
// */
//package com.monkey.gzpt.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.monkey.basic.base.controller.CommonController;
//import com.monkey.gzpt.service.WXFollowService;
//import com.monkey.taf.log.TAFLog;
//import com.monkey.taf.web.util.BasicDto;
//import com.monkey.taf.web.util.Status;
//
///**
// * @模块名称：微信公众号绑定与解绑
// * @Description
// * @version：1.0
// * @author：zq
// * @date：2018年3月26日 上午10:06:22
// */
//@Controller
//@RequestMapping(value = "/follow")
//public class WXFollowController extends CommonController{
//
//    @Autowired
//    WXFollowService wxFollowService;
//    /**
//     * @模块名称：用户公众号绑定查询
//     * @Description
//     * @version：1.0
//     * @author：zq
//     * @date：2018年3月26日 上午10:06:22
//     */
//    @RequestMapping(value = "/selectAttention")
//    public void selectAttention(HttpServletRequest req, HttpServletResponse resp) {
//        Status status = new Status();
//        try {
//            BasicDto basicDto = new BasicDto();
//            super.initParams(status, basicDto, req);
//            wxFollowService.selectAttention(status, basicDto);
//        }
//        catch (Exception e) {
//            TAFLog.error("查询用户是否绑定微信公众号异常", e);
//            status.setStatusCode("000001");
//            status.setStatusMessage("服务器繁忙，请稍后再试");
//        }
//        super.writeToPage(status, resp);
//    }
//    
//    /**
//     * @模块名称：用户公众号绑定
//     * @Description
//     * @version：1.0
//     * @author：zq
//     * @date：2018年3月26日 下午4:06:22
//     */
//    @RequestMapping(value = "/attention")
//    public void attention(HttpServletRequest req, HttpServletResponse resp) {
//        Status status = new Status();
//        try {
//            BasicDto basicDto = new BasicDto();
//            super.initParams(status, basicDto, req);
//            wxFollowService.attention(status, basicDto);
//        }
//        catch (Exception e) {
//            TAFLog.error("用户绑定微信公众号异常", e);
//            status.setStatusCode("000001");
//            status.setStatusMessage("服务器繁忙，请稍后再试");
//        }
//        super.writeToPage(status, resp);
//    }
//    
//    /**
//     * @模块名称：用户公众号解绑
//     * @Description
//     * @version：1.0
//     * @author：zq
//     * @date：2018年3月27日 上午9:06:22
//     */
//    @RequestMapping(value = "/relieveAttention")
//    public void relieveAttention(HttpServletRequest req, HttpServletResponse resp) {
//        Status status = new Status();
//        try {
//            BasicDto basicDto = new BasicDto();
//            super.initParams(status, basicDto, req);
//            wxFollowService.relieveAttention(status, basicDto);
//        }
//        catch (Exception e) {
//            TAFLog.error("用户解除绑定微信公众号异常", e);
//            status.setStatusCode("000001");
//            status.setStatusMessage("服务器繁忙，请稍后再试");
//        }
//        super.writeToPage(status, resp);
//    }
//}
