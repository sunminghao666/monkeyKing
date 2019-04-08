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

import com.monkey.auth.manage.pojo.Resources;
import com.monkey.auth.manage.pojo.Role;
import com.monkey.auth.manage.pojo.User;
import com.monkey.auth.manage.service.ResourcesService;
import com.monkey.auth.manage.service.RoleService;
import com.monkey.auth.manage.service.Tree;
import com.monkey.basic.base.controller.CommonController;
import com.monkey.taf.dataStructure.JSONUtil;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Pager;
import com.monkey.taf.web.util.Status;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.controller
 * @类名称： RoleController
 * @类描述：【类描述】角色管理
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日下午2:19:52
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends CommonController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourcesService resService;

    @Autowired
    private Tree tree;

    @RequiresPermissions("role/init")
    @RequestMapping(value = "/init")
    public String init(ModelMap map) {
        return "redirect:/html/manage/role.html";
    }

    /**
     * 新增角色
     * 
     * @param role
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/addRole")
    public void addRole(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        Role role = new Role();
        JSONObject jsonObject = super.stringToObject(req);
        try {
            Subject currentUser = SecurityUtils.getSubject();// 获得当前用户登录信息
            String roleName = jsonObject.getString("roleName");// 角色名称
            String roleCode = jsonObject.getString("roleCode");// 角色编码
            String enabled = jsonObject.getString("enabled");// 启用标识
            String description = jsonObject.getString("description");// 描述
            role.setRoleName(roleName);
            role.setRoleCode(roleCode);
            List < Role > roleList = roleService.findRoleByCondition(role);
            if (CollectionUtils.isNotEmpty(roleList)) {
                TAFLog.error("角色名称或编码已经存在,不能添加");
                status.setStatusCode("000008");
                status.setStatusMessage("角色名称或编码已经存在,不能添加");
            }
            else {
                role.setEnabled(enabled);
                role.setDescription(description);
                if (currentUser != null) {
                    User user = (User) currentUser.getSession().getAttribute("user");
                    if (user != null) {
                        role.setInsertUser(user.getUsername());
                    }
                    else {
                        role.setInsertUser("");
                    }
                }
                role = roleService.addRole(role);
            }
        }
        catch (Exception e) {
            TAFLog.error("新增角色失败", e);
            status.setStatusCode("000001");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("role", role);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 分页查询角色
     * 
     * @param
     * @since 2015-7-01 下午1:44:10
     * @author chenenyu
     * @return
     */
    @RequestMapping(value = "/queryRoleByPage")
    public void queryRoleByPage(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        Pager < Role > page = new Pager < Role >();
        JSONObject jsonObject = super.stringToObject(req);
        try {
            String roleCode = jsonObject.getString("roleCode");// 角色编码
            String roleName = jsonObject.getString("roleName");// 角色名称
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
            page = roleService.queryByPage(roleCode, roleName, pageSize, pageIndex);

        }
        catch (Exception e) {
            TAFLog.error("条件查询角色失败", e);
            status.setStatusCode("000002");
            status.setStatusMessage(e.getMessage());
        }

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("page", page);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 根据id查询角色
     * 
     * @param roleId
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/findRoleById")
    public void findRoleById(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        Role role = null;
        try {
            String roleId = jsonObject.getString("roleId");// 角色id
            Long rid = Long.parseLong(roleId);
            role = roleService.findRoleById(rid);
        }
        catch (Exception e) {
            TAFLog.error("查询角色失败", e);
            status.setStatusCode("000003");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("role", role);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 更新角色
     * 
     * @param role
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/updateRole")
    public void updateRole(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        Role role = new Role();
        JSONObject jsonObject = super.stringToObject(req);
        int updateResult = 0;
        try {
            Subject currentUser = SecurityUtils.getSubject();// 获得当前用户登录信息
            String roleId = jsonObject.getString("roleId");// 角色id
            Long rId = Long.parseLong(roleId);
            role = roleService.findRoleById(rId);
            String roleName = jsonObject.getString("roleName");// 角色名称
            String roleCode = jsonObject.getString("roleCode");// 角色编码
            String enabled = jsonObject.getString("enabled");// 启用标识
            String description = jsonObject.getString("description");// 描述
            int a = 0;
            List < Role > roleList = roleService.findRoleByCondition2(role);
            for (int i = 0; i < roleList.size(); i++) {
                if (roleName.equals(roleList.get(i).getRoleName()) || roleCode.equals(roleList.get(i).getRoleCode())) {
                    a = 1;
                    break;
                }
            }
            if (a == 1) {
                TAFLog.error("角色名称或代码已经存在,不能修改");
                status.setStatusCode("000008");
                status.setStatusMessage("角色名称或代码已经存在,不能修改");
            }
            else {

                role.setRoleName(roleName);
                role.setRoleCode(roleCode);
                role.setEnabled(enabled);
                role.setDescription(description);
                if (currentUser != null) {
                    User user = (User) currentUser.getSession().getAttribute("user");
                    if (user != null) {
                        role.setInsertUser(user.getUsername());
                    }
                }
                updateResult = roleService.updateRole(role);
            }
        }
        catch (Exception e) {
            TAFLog.error("更新角色失败", e);
            status.setStatusCode("000004");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("result", updateResult);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 删除角色
     * 
     * @param listRolesId
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/deleteRole")
    public void deleteRoles(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        List < Long > listRoleId = new ArrayList < Long >();
        try {
            String roleIds = jsonObject.getString("roleIds");// 角色id集合
            String[] roleArray = roleIds.split(",");
            for (int i = 0; i < roleArray.length; i++) {
                listRoleId.add(Long.parseLong(roleArray[i]));
            }
            roleService.deleteRoles(listRoleId);
        }
        catch (Exception e) {
            TAFLog.error("删除角色失败", e);
            status.setStatusCode("000005");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 树形菜单
     * 
     * @param req
     * @param resp
     */
    @RequestMapping("getResTree")
    public void getResTree(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        String treeMenu = "";
        try {
            Resources resource = new Resources();
            List < Resources > resList = resService.findResourcesByCondition(resource);
            tree.setResList(resList);
            treeMenu = tree.buildTreeforRole();
        }
        catch (Exception e) {
            TAFLog.error("获取资源失败", e);
            status.setStatusCode("000006");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("treeMenu", treeMenu);
        super.writeToPage(jsonObj.toString(), resp);
    }

}
