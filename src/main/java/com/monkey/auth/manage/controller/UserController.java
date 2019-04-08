package com.monkey.auth.manage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monkey.auth.manage.pojo.User;
import com.monkey.auth.manage.service.UserService;
import com.monkey.basic.base.controller.CommonController;
import com.monkey.taf.encryption.MD5Util;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Pager;
import com.monkey.taf.web.util.Status;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.controller
 * @类名称： UserController
 * @类描述：【类描述】用户管理
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日下午2:18:50
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends CommonController {

    @Autowired
    UserService userService;

    @RequiresPermissions("user/init")
    @RequestMapping(value = "/init")
    public String init(ModelMap map) {
        return "redirect:/html/manage/user.html";
    }

    /**
     * 新增用户
     * 
     * @param res
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/addUser")
    public void addUser(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        User user = new User();
        JSONObject jsonObject = super.stringToObject(req);
        try {
            Subject currentUser = SecurityUtils.getSubject();// 获得当前用户登录信息
            String userCode = jsonObject.getString("userCode");// 用户账号
            String userName = jsonObject.getString("userName");// 用户名称
            String password = jsonObject.getString("password");// 密码
            String comcode = jsonObject.getString("comcode");// 管理保险公司代码
            String validStatus = jsonObject.getString("validStatus");// 有效标记
            String userPhone = jsonObject.getString("userPhone");// 手机号
            String userEmail = jsonObject.getString("userEmail");// 邮件
            String department = jsonObject.getString("department");// 所属部门
            String position = jsonObject.getString("position");// 职位
            String manageCompany = jsonObject.getString("manageCompany");// 管理机构代码
            user.setUsercode(userCode);
            user.setUsername(userName);
            List < User > userList = userService.findUserByCondition2(userName, userCode);
            if (CollectionUtils.isNotEmpty(userList)) {
                TAFLog.error("用户名称或编码已经存在,不能添加");
                status.setStatusCode("000008");
                status.setStatusMessage("用户名称或编码已经存在,不能添加");
            }
            else {
                // String.copyValueOf(agentPass.toCharArray())
                user.setPassword(MD5Util.encryption(String.copyValueOf(password.toCharArray())));
                user.setComcode(comcode);
                user.setValidstatus(validStatus);
                user.setUserphone(userPhone);
                user.setUseremail(userEmail);
                user.setDepartment(department);
                user.setPosition(position);
                user.setManageCompany(manageCompany);
                if (currentUser != null) {
                    User userInfo = (User) currentUser.getSession().getAttribute("user");
                    if (userInfo != null) {
                        user.setCreatorcode(userInfo.getUsername());
                    }
                    else {
                        user.setCreatorcode("");
                    }
                }
                user = userService.addUser(user);
            }

        }
        catch (Exception e) {
            TAFLog.error("新增用户失败", e);
            status.setStatusCode("000001");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("user", user);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 分页查询用户
     * 
     * @param
     * @since 2015-7-01 下午1:44:10
     * @author chenenyu
     * @return
     */
    @RequestMapping(value = "/queryUserByPage")
    public void queryUserByPage(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        Pager < User > page = new Pager < User >();
        JSONObject jsonObject = super.stringToObject(req);
        try {
            String usercode = jsonObject.getString("userCode");// 用户账号
            String username = jsonObject.getString("userName");// 用户名称

            String pageIndexStr = jsonObject.getString("pageIndex");// 查询页码
            String pageSizeStr = jsonObject.getString("pageSize");// 每页显示数

            int pageIndex = 1;
            int pageSize = 10;
            try {
                pageIndex = Integer.parseInt(pageIndexStr);
                pageSize = Integer.parseInt(pageSizeStr);
            }
            catch (Exception e) {
            }
            page = userService.queryByPage(usercode, username, pageSize, pageIndex);

        }
        catch (Exception e) {
            TAFLog.error("条件查询用户失败", e);
            status.setStatusCode("000002");
            status.setStatusMessage(e.getMessage());
        }

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("page", page);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 根据id查询用户
     * 
     * @param resId
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/findUserById")
    public void findUserById(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        User user = null;
        try {
            String userId = jsonObject.getString("userId");// 用户id
            Long uid = Long.parseLong(userId);
            user = userService.findUserById(uid);
        }
        catch (Exception e) {
            TAFLog.error("查询用户失败", e);
            status.setStatusCode("000003");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("user", user);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 更新用户
     * 
     * @param res
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/updateUser")
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        User user = new User();
        JSONObject jsonObject = super.stringToObject(req);
        int updateResult = 0;
        try {
            Subject currentUser = SecurityUtils.getSubject();
            // 获得当前用户登录信息
            String userId = jsonObject.getString("userId");// 用户id
            Long uid = Long.parseLong(userId);
            user = userService.findUserById(uid);

            String userCode = jsonObject.getString("userCode");// 用户账号
            String userName = jsonObject.getString("userName");// 用户名称
            String password = jsonObject.getString("password");// 密码
            String comcode = jsonObject.getString("comcode");// 管理保险公司代码
            String validStatus = jsonObject.getString("validStatus");// 有效标记
            String userPhone = jsonObject.getString("userPhone");// 手机号
            String userEmail = jsonObject.getString("userEmail");// 邮件
            String department = jsonObject.getString("department");// 所属部门
            String position = jsonObject.getString("position");// 职位
            String manageCompany = jsonObject.getString("manageCompany");// 管理机构代码
            int a = 0;
            List < User > userList = userService.findUserByCondition(user.getUsername(), user.getUsercode());
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i).getUsercode());
                System.out.println(userList.get(i).getUsername());

                if (userCode.equals(userList.get(i).getUsercode()) || userName.equals(userList.get(i).getUsername())) {
                    a = 1;
                    break;
                }
            }
            if (a == 1) {
                TAFLog.error("用户名称或编码已经存在,不能添加");
                status.setStatusCode("000008");
                status.setStatusMessage("用户名称或编码已经存在,不能添加");
            }
            else {
                if (password.length() <= 15) {
                    user.setPassword(MD5Util.encryption(String.copyValueOf(password.toCharArray())));
                }
                else {
                    user.setPassword(password);
                }
                user.setUsercode(userCode);
                user.setUsername(userName);
                user.setComcode(comcode);
                user.setValidstatus(validStatus);
                user.setUserphone(userPhone);
                user.setUseremail(userEmail);
                user.setDepartment(department);
                user.setPosition(position);
                user.setManageCompany(manageCompany);
                if (currentUser != null) {
                    User userInfo = (User) currentUser.getSession().getAttribute("user");
                    if (user != null) {
                        user.setCreatorcode(userInfo.getUsername());
                    }
                }
                updateResult = userService.updateUser(user);
            }
        }
        catch (Exception e) {
            TAFLog.error("更新用户失败", e);
            status.setStatusCode("000004");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("result", updateResult);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 删除用户
     * 
     * @param listUserId
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/deleteUser")
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        List < Long > listUserId = new ArrayList < Long >();
        try {
            String userIds = jsonObject.getString("userIds");// 用户id集合
            String[] userArray = userIds.split(",");
            for (int i = 0; i < userArray.length; i++) {
                listUserId.add(Long.parseLong(userArray[i]));
            }
            userService.deleteUser(listUserId);
        }
        catch (Exception e) {
            TAFLog.error("删除用户失败", e);
            status.setStatusCode("000005");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 修改用户密码
     * 
     * @param res
     * @param resp
     * @since 2015-07-21 上午10:35:23
     * @author gouxiaojuan
     */
    @RequestMapping(value = "/updateUserPwd")
    public void updateUserPwd(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        User user = new User();
        JSONObject jsonObject = super.stringToObject(req);
        int updateResult = 0;
        try {

            String userId = jsonObject.getString("userId");// 用户id
            Long uid = Long.parseLong(userId);
            user = userService.findUserById(uid);
            String oldPassword = jsonObject.getString("oldPassword");// 旧密码
            String newPassword = jsonObject.getString("newPassword");// 新密码
            if (user != null) {
                if (!MD5Util.encryption(oldPassword).equals(user.getPassword().trim())) {
                    // throw new AuthenticationException("用户密码不正确");
                    TAFLog.error("更新用户失败，用户密码不正确");
                    status.setStatusCode("000004");
                    status.setStatusMessage("用户密码不正确");
                }
                else {
                    user.setPassword(MD5Util.encryption(newPassword));
                    updateResult = userService.updateUser(user);
                }
            }
            else {
                TAFLog.error("更新用户失败，未查到用户ID为" + userId + "的用户");
                status.setStatusCode("000004");
                status.setStatusMessage("更新用户失败，未查到当前登录的用户信息");
            }
        }
        catch (Exception e) {
            TAFLog.error("更新用户失败", e);
            status.setStatusCode("000004");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("result", updateResult);
        super.writeToPage(jsonObj.toString(), resp);
    }

}
