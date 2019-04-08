package com.monkey.auth.base.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monkey.auth.manage.dao.UserMapper;
import com.monkey.auth.manage.pojo.Resources;
import com.monkey.auth.manage.pojo.User;
import com.monkey.auth.manage.pojo.UserExample;
import com.monkey.auth.manage.service.ResourcesService;
import com.monkey.auth.manage.service.Tree;
import com.monkey.taf.encryption.MD5Util;

@Controller
public class LoginController {
    @Autowired
    ResourcesService resourcesService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    Tree tree;

    @RequestMapping("/login")
    public String login(ModelMap map, String manageName, String managePassword) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsercodeEqualTo(manageName);
        List < User > user = userMapper.selectByExample(userExample);
        if (user.size() > 0) {
            // 添加用户认证信息
            Subject subject = SecurityUtils.getSubject();
            // timeout:-1000ms 永不超时
            SecurityUtils.getSubject().getSession().setTimeout(3600 * 1000);
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(manageName,
                    MD5Util.encryption(managePassword));
            // 进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()) {

                try {
                    List < Resources > resourceList = resourcesService.findCurrUserAuthRes(user.get(0).getId());
                    tree.setResList(resourceList);
                    // 树形菜单数据
                    String menuTree = tree.buildTree();
                    // 树形菜单放入session中
                    map.put("menuTree", menuTree);
                }
                catch (Exception e) {
                    throw new AuthenticationException("获取菜单失败！");
                }
            }
            return "index";
        }
        else {
            throw new AuthenticationException("用户不存在");
        }

    }

/*    @RequestMapping("/index")
    public String index() {
        return "index";
    }*/

    @RequestMapping("/error1")
    public String error() {
        return "error";
    }
}
