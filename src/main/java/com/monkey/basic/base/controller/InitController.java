package com.monkey.basic.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.monkey.basic.base.service.InitService;
import com.monkey.db.base.pojo.PwbDictionary;
import com.monkey.taf.log.TAFLog;

@Controller
@RequestMapping(value = "/init")
public class InitController extends CommonController {
    @Autowired
    InitService initService;

    @RequestMapping(value = "/loadDicBytype", method = RequestMethod.POST)
    public void loadDicBytype(HttpServletRequest req, HttpServletResponse resp) {
        List < PwbDictionary > ls = null;
        try {
            String type = req.getParameter("dic_type");
            ls = initService.loadDicBytype(type);
        }
        catch (Exception e) {
            TAFLog.error("查询字典表失败", e);
        }
        super.writeToPage(ls, resp);
    }
}
