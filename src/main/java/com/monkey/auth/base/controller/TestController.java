package com.monkey.auth.base.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    // 用户拥有admin角色即可访问
    @RequiresRoles("admin")
    @RequestMapping("/test")
    public Map < String, String > test() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("test", "test");
        return map;
    }

    // 用户拥有/test3角色即可访问
    @RequiresRoles("/test3")
    @RequestMapping("/test3")
    public Map < String, String > test3() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("test", "test");
        return map;
    }

    // 用户拥有/add/test权限即可访问
    @RequiresPermissions("/add/test")
    @RequestMapping("/add/test")
    public Map < String, String > addTest() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("test", "test1");
        return map;
    }

    // 用户拥有/test2权限即可访问
    @RequiresPermissions("/test2")
    @RequestMapping("/test2")
    public Map < String, String > test2() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("test", "test1");
        return map;
    }

    // 用户拥有/test4权限即可访问
    @RequestMapping("/test4")
    public Map < String, String > test4() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("test", "test1");
        return map;
    }

    // 用户必须拥有admin和test5角色才可访问
    @RequiresRoles({ "admin", "test5" })
    @RequestMapping("/test5")
    public Map < String, String > test5() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("test", "test");
        return map;
    }

    // 用户必须拥有admin或test6角色即可访问
    @RequiresRoles(value = { "admin", "test6" }, logical = Logical.OR)
    @RequestMapping("/test6")
    public Map < String, String > test6() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("test", "test6");
        return map;
    }

}
