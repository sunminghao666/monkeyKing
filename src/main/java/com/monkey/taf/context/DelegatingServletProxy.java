package com.monkey.taf.context;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.context
 * @类名称： DelegatingServletProxy
 * @类描述：【类描述】此类用于处理servlet引用spring中的bean 可参考http://www.shangxueba.com/jingyan/89328.html
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年9月19日上午9:58:43
 */
public class DelegatingServletProxy extends GenericServlet {

    private static final long serialVersionUID = 1L;

    private String targetBean;

    private Servlet proxy;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        proxy.service(req, res);
    }

    @Override
    public void init() throws ServletException {
        this.targetBean = getServletName();
        getServletBean();
        proxy.init(getServletConfig());
    }

    private void getServletBean() {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.proxy = (Servlet) wac.getBean(targetBean);
    }
}
