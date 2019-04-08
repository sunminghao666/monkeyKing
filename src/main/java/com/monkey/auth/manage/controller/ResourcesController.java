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
import com.monkey.auth.manage.pojo.User;
import com.monkey.auth.manage.pojo.ZtreeDTO;
import com.monkey.auth.manage.service.ResourcesService;
import com.monkey.basic.base.controller.CommonController;
import com.monkey.taf.common.Tools;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Pager;
import com.monkey.taf.web.util.Status;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.controller
 * @类名称： ResourcesController
 * @类描述：【类描述】资源管理模块
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日上午10:03:47
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourcesController extends CommonController {

    @Autowired
    private ResourcesService resourcesService;

    @RequiresPermissions("resource/init")
    @RequestMapping(value = "/init")
    public String init(ModelMap map) {
        return "redirect:/html/manage/resources.html";
    }

    /**
     * 新增资源
     * 
     * @param res
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/addRes")
    public void addResources(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        Resources res = new Resources();
        JSONObject jsonObject = super.stringToObject(req);
        try {
            Subject currentUser = SecurityUtils.getSubject();// 获得当前用户登录信息
            String resourceCode = jsonObject.getString("resourceCode");// 资源编码
            String sortId = jsonObject.getString("sortId");// 资源排序号
            String resourceName = jsonObject.getString("resourceName");// 资源名称
            String resourceType = jsonObject.getString("resourceType");// 资源类型
            String enabled = jsonObject.getString("enabled");// 启用标识
            String parentId = jsonObject.getString("parentId");// 父资源id
            String url = jsonObject.getString("url");// 资源路径
            String description = jsonObject.getString("description");// 资源描述
            res.setResourceCode(resourceCode);
            res.setSortOrder(Integer.parseInt(sortId));
            res.setResourceName(resourceName);
            List < Resources > resList = resourcesService.findResourcesByCondition(res);
            if (CollectionUtils.isNotEmpty(resList)) {
                TAFLog.error("资源名称或编码已经存在,不能添加");
                status.setStatusCode("000008");
                status.setStatusMessage("资源名称或编码已经存在,不能添加");
            }
            else {
                res.setResourceType(resourceType);
                res.setEnabled(enabled);
                res.setParentId(parentId);
                res.setUrl(url);
                res.setDescription(description);
                if (currentUser != null) {
                    User user = (User) currentUser.getSession().getAttribute("user");
                    if (user != null) {
                        res.setInsertUser(user.getUsername());
                    }
                    else {
                        res.setInsertUser("");
                    }
                }
                resourcesService.addResources(res);
            }
        }
        catch (Exception e) {
            TAFLog.error("新增资源失败", e);
            status.setStatusCode("000001");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("resource", res);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 分页查询
     * 
     * @param resourceCode
     * @param resourceName
     * @param pageIndexStr
     * @param req
     * @param resp
     * @return
     */
    @RequiresPermissions("resource/queryResourceByPage")
    @RequestMapping(value = "/queryResourceByPage")
    public void queryResourceByPage(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        Pager < Resources > page = new Pager < Resources >();
        JSONObject jsonObject = super.stringToObject(req);
        try {
            String resourceCode = jsonObject.getString("resourceCode");// 资源编码
            String resourceName = jsonObject.getString("resourceName");// 资源名称
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
            page = resourcesService.queryByPage(resourceCode, resourceName, pageSize, pageIndex);

        }
        catch (Exception e) {
            TAFLog.error("条件查询资源失败", e);
            status.setStatusCode("000002");
            status.setStatusMessage(e.getMessage());
        }

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("page", page);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 根据id查询资源
     * 
     * @param resId
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/findResById")
    public void findResourcesById(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        Resources relResources = null;
        try {
            String resourceId = jsonObject.getString("resourceId");// 资源id
            Long resId = Long.parseLong(resourceId);
            relResources = resourcesService.findResourcesById(resId);
        }
        catch (Exception e) {
            TAFLog.error("查询指定资源失败", e);
            status.setStatusCode("000003");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("resource", relResources);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 更新资源
     * 
     * @param res
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/updateRes")
    public void updateResources(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        Resources res = new Resources();
        JSONObject jsonObject = super.stringToObject(req);
        int updateResult = 0;
        try {
            Subject currentUser = SecurityUtils.getSubject();// 获得当前用户登录信息
            String resId = jsonObject.getString("resourceId");// 资源id
            Long id = Long.parseLong(resId);
            res = resourcesService.findResourcesById(id);
            String resourceCode = jsonObject.getString("resourceCode");// 资源编码
            String sortId = jsonObject.getString("sortId");// 资源排序号
            String resourceName = jsonObject.getString("resourceName");// 资源名称
            String resourceType = jsonObject.getString("resourceType");// 资源类型
            String enabled = jsonObject.getString("enabled");// 启用标识

            String parentId = jsonObject.getString("parentId");// 父资源id
            String url = jsonObject.getString("url");// 资源地址
            String description = jsonObject.getString("description");// 资源描述
            int a = 0;
            List < Resources > resList = resourcesService.findResourcesByCondition2(res);
            for (int i = 0; i < resList.size(); i++) {
                if (resourceCode.equals(resList.get(i).getResourceCode())
                        || resourceName.equals(resList.get(i).getResourceName())) {
                    a = 1;
                    break;
                }
            }
            if (a == 1) {
                TAFLog.error("资源名称或编码已经存在,不能添加");
                status.setStatusCode("000008");
                status.setStatusMessage("资源名称或编码已经存在,不能添加");
            }
            else {

                res.setResourceCode(resourceCode);
                res.setSortOrder(Integer.parseInt(sortId));
                res.setResourceName(resourceName);
                res.setResourceType(resourceType);
                res.setEnabled(enabled);
                if (!Tools.isBlank(parentId) && !"null".equals(parentId)) {
                    res.setParentId(parentId);
                }
                res.setUrl(url);
                res.setDescription(description);
                if (currentUser != null) {
                    User user = (User) currentUser.getSession().getAttribute("user");
                    if (user != null) {
                        res.setInsertUser(user.getUsername());
                    }
                }
                updateResult = resourcesService.updateResources(res);
            }
        }
        catch (Exception e) {
            TAFLog.error("更新资源失败", e);
            status.setStatusCode("000004");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("result", updateResult);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 删除资源
     * 
     * @param listResourcesId
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/deleteRes")
    public void deleteResources(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        JSONObject jsonObject = super.stringToObject(req);
        List < Long > listResourcesId = new ArrayList < Long >();
        try {
            String resourceIds = jsonObject.getString("resIds");// 资源id集合
            String[] resArray = resourceIds.split(",");
            for (int i = 0; i < resArray.length; i++) {
                listResourcesId.add(Long.parseLong(resArray[i]));
            }
            resourcesService.deleteResources(listResourcesId);
        }
        catch (Exception e) {
            TAFLog.error("删除资源失败", e);
            status.setStatusCode("000005");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 查询一级菜单资源
     * 
     * @param resId
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/findResourcesFirstClass")
    public void findResourcesFirstClass(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        List < Resources > relResources = null;
        try {
            relResources = resourcesService.findResourcesFirstClass();
        }
        catch (Exception e) {
            TAFLog.error("查询一级菜单失败！", e);
            status.setStatusCode("000001");
            status.setStatusMessage(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("resources", relResources);
        super.writeToPage(jsonObj.toString(), resp);
    }

    /**
     * 查询所有菜单资源
     * 
     * @param resId
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/findAllResource")
    public void findAllResource(HttpServletRequest req, HttpServletResponse resp) {
        Status status = new Status();
        List < Resources > relResources = null;
        List < ZtreeDTO > ztreeDTOs = new ArrayList < ZtreeDTO >();
        try {
            relResources = resourcesService.findAllResource();
        }
        catch (Exception e) {
            TAFLog.error("查询一级菜单失败！", e);
            status.setStatusCode("000001");
            status.setStatusMessage(e.getMessage());
        }
        for (int i = 0; i < relResources.size(); i++) {
            ZtreeDTO ztreeDTO = new ZtreeDTO();
            ztreeDTO.setId(relResources.get(i).getId());
            if (!Tools.isBlank(relResources.get(i).getParentId())) {
                ztreeDTO.setpId(Long.parseLong(relResources.get(i).getParentId()));
            }
            ztreeDTO.setOpen(true);
            ztreeDTO.setChecked(false);
            ztreeDTO.setName(relResources.get(i).getResourceName());
            ztreeDTOs.add(ztreeDTO);
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", status);
        jsonObj.put("ztreeDTOs", ztreeDTOs);
        super.writeToPage(jsonObj.toString(), resp);
    }
}
