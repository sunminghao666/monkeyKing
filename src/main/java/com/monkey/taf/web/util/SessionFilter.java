package com.monkey.taf.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SessionFilter extends HttpServlet implements Filter {

	public FilterConfig config;
	public String fon;
	public static ThreadLocal threadsession = new ThreadLocal();

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		//User user=null;
		if(session!=null&&session.getAttribute("user")!=null){
//			user=(User) session.getAttribute("user");
//            threadsession.set(user.getUsercode());//日志按操作员打印
		}	
        chain.doFilter(req, response);
		
	}

	public void init(FilterConfig config) {
		
	}
}
