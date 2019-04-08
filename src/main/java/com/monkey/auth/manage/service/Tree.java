package com.monkey.auth.manage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monkey.auth.manage.pojo.Resources;

/**
 * 树形菜单处理
 * 
 * @author User
 *
 */
@Component
public class Tree {

    @Autowired
    ResourcesService resourcesService;

    private StringBuffer html;

    private List < Resources > resList;

    public Tree() {
    }

    public List < Resources > getResList() {
        return resList;
    }

    public void setResList(List < Resources > resList) {
        this.resList = resList;
    }

    public String buildTree() throws Exception {
        html = new StringBuffer();
        html.append("<ul>");
        for (Resources res : resList) {
            Integer id = res.getId().intValue();
            if (res.getParentId() == null || "".equals(res.getParentId())) {
                List < Resources > childrenlist = getChildren(res);
                if (childrenlist.size() == 0) {
                    // if(resourcesService.findResourcesByParentId(id).isEmpty()){
                    html.append("\r\n<li id='" + id
                            + "' class='nav' onclick=nvclick(this)><input type='hidden' value='" + res.getUrl()
                            + "'/><img src='img/menu_system_off.png'><span>" + res.getResourceName() + "</span></li>");
                }
                else {
                    html.append("\r\n<li id='" + id
                            + "' class='nav ' onclick='changeStyle(this,1)'><img src='img/menu_system_off.png'><span>"
                            + res.getResourceName() + "</span></li>");
                    build(res);
                }
            }
        }
        html.append("\r\n</ul>");
        return html.toString();
    }

    private void build(Resources res) {
        List < Resources > children = getChildren(res);
        if (!children.isEmpty()) {
            html.append("\r\n<ul>");
            for (Resources child : children) {
                Integer id = child.getId().intValue();
                List < Resources > childrenlist = getChildren(child);
                if (childrenlist.size() == 0) {
                    html.append("\r\n<li id='" + id
                            + "' class='nav' onclick=nvclick(this)><input type='hidden' value='" + child.getUrl()
                            + "'/><img src='img/menu_user_off.png'><span>" + child.getResourceName() + "</span></li>");
                }
                else {
                    html.append("\r\n<li id='" + id
                            + "' class='nav ' onclick='changeStyle(this,2)'><img src='img/menu_user_off.png'><span>"
                            + child.getResourceName() + "</span></li>");
                    build(child);
                }
            }
            html.append("\r\n</ul>");
        }
    }

    public String buildTreeforRole() throws Exception {
        html = new StringBuffer();
        html.append("<ul>");
        for (Resources res : resList) {
            Integer id = res.getId().intValue();
            if (res.getParentId() == null || "".equals(res.getParentId())) {
                html.append("<li id='"
                        + id
                        + "' onclick='changeStyle(this)' > &nbsp;<span><input type='checkbox' /> <input type='hidden' id='resId' value='"
                        + id + "'/>" + res.getResourceName() + "</span></li>");
                if (!resourcesService.findResourcesByParentId(id).isEmpty()) {
                    buildforRole(res);
                }
            }
        }
        html.append("</ul>"); // \r\n
        return html.toString();
    }

    private void buildforRole(Resources res) {
        List < Resources > children = getChildren(res);
        if (!children.isEmpty()) {
            html.append("<ul>");
            for (Resources child : children) {
                Integer id = child.getId().intValue();
                html.append("<li id='"
                        + id
                        + "' onclick='changeStyle(this)' > &nbsp;<span><input type='checkbox' /> <input type='hidden' id='resId' value='"
                        + id + "'/>" + child.getResourceName() + "</span></li>");
                buildforRole(child);
            }
            html.append("</ul>");
        }
    }

    private List < Resources > getChildren(Resources res) {
        List < Resources > children = new ArrayList < Resources >();
        Integer id = res.getId().intValue();
        for (Resources child : resList) {
            if (id.toString().equals(child.getParentId()) && "1".equals(child.getResourceType())) {
                children.add(child);
            }
        }
        return children;
    }
}