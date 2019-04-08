package com.monkey.auth.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monkey.auth.manage.pojo.UserRole;
import com.monkey.auth.manage.service.UserRoleService;
import com.monkey.basic.base.controller.CommonController;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Status;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.controller
 * @类名称： UserRoleController
 * @类描述：【类描述】用户角色控制
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日下午2:18:25
 */
@Controller
@RequestMapping(value = "/userRole")
public class UserRoleController extends CommonController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 当前用户下的角色
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/findUserRolesByUserCode")
    public void findUserRolesByUserCode(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        List < UserRole > userRoleList = null;
        try {
            String userId = jsonObject.getString("userId");// 用户id
            userRoleList = userRoleService.findUserRolesByUserCode(userId);
        }
        catch (Exception e) {
            TAFLog.error("查询用户角色关系失败", e);
            status.setStatusCode("000001");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("userRoleList", userRoleList);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 用户角色关系的维护
     * 
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/updateUserRoles")
    public void updateUserRoles(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        try {
            String userId = jsonObject.getString("userId");// 用户id
            String checked = jsonObject.getString("checked");// 选中的角色编码
            String unChecked = jsonObject.getString("unChecked");// 未选中的角色编码

            userRoleService.updateUserRole(userId, checked, unChecked);

        }
        catch (Exception e) {
            TAFLog.error("保存用户角色关系失败", e);
            status.setStatusCode("000002");
            status.setStatusMessage(e.getMessage());
        }

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        super.writeToPage(jsonObj.toString(), resp);

    }

}
