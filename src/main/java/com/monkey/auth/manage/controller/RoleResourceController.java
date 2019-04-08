package com.monkey.auth.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monkey.auth.manage.pojo.RoleResource;
import com.monkey.auth.manage.pojo.ZtreeDTO;
import com.monkey.auth.manage.service.RoleResourceService;
import com.monkey.basic.base.controller.CommonController;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Status;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.controller
 * @类名称： RoleResourceController
 * @类描述：【类描述】角色资源控制
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日下午2:19:32
 */
@Controller
@RequestMapping(value = "/roleResource")
public class RoleResourceController extends CommonController {

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 当前角色下的资源
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/findRoleResourcesByRoleCode")
    public void findRoleResourceyUserCode(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        List < RoleResource > roleResourceList = null;
        try {
            String roleId = jsonObject.getString("roleId");// 角色id
            roleResourceList = roleResourceService.findRoleResourceByRoleCode(roleId);
        }
        catch (Exception e) {
            TAFLog.error("查询角色资源关系失败", e);
            status.setStatusCode("000001");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("roleResourceList", roleResourceList);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 当前角色下的资源
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/findResourcesByRoleCode")
    public void findResourcesByRoleCode(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        List < ZtreeDTO > ztreeDTOs = null;
        try {
            String roleId = jsonObject.getString("roleId");// 角色id
            ztreeDTOs = roleResourceService.findResourcesByRoleCode(roleId);
        }
        catch (Exception e) {
            TAFLog.error("查询角色资源关系失败", e);
            status.setStatusCode("000001");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("ztreeDTOs", ztreeDTOs);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 角色资源关系的维护
     * 
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/updateRoleResource")
    public void updateRoleResource(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        try {
            String roleId = jsonObject.getString("roleId");// 角色id
            String checked = jsonObject.getString("checked");// 选中的角色编码
            String unChecked = jsonObject.getString("unChecked");// 未选中的角色编码

            roleResourceService.updateRoleResource(roleId, checked, unChecked);

        }
        catch (Exception e) {
            TAFLog.error("保存角色资源关系失败", e);
            status.setStatusCode("000002");
            status.setStatusMessage(e.getMessage());
        }

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        super.writeToPage(jsonObj.toString(), resp);
    }

}
