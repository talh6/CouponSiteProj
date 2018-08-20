package com.tal.couponsproj.api;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/rest/site/*")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// class HttpServlet does not have the method getCookies,
		// YET.. the actual object IS (!) HttpServletRequest (HttpServletRequest extends ServletRequest)
		// and HttpServletRequest DOES have getCookies()
		// So, we cast request into a pointer of type HttpServletRequest
		HttpServletRequest req = (HttpServletRequest)request;
		
		HttpSession session = req.getSession(false);
		
		if (session != null){
			chain.doFilter(req, response);
			return;
		}
		
		HttpServletResponse res = (HttpServletResponse) response; 
		res.setStatus(401);
		res.setHeader("ErrorCause", "No login cookie was found");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Filter started");
	}
	

}
