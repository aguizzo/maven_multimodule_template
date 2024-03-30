package com.itbulls.learnit.onlinestore.web.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class PartnerCodeFilter extends HttpFilter implements Filter {
    
	private static final long serialVersionUID = 1L;
	public static final String PARTNER_CODE_PARAMETER_NAME = "partner_code";
	public static final String PARTNER_CODE_COOKIE_NAME = "partner_code";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String partnerCode = request.getParameter(PARTNER_CODE_PARAMETER_NAME);
		if (partnerCode != null && !partnerCode.isEmpty()) {
			System.out.println(partnerCode);
			((HttpServletResponse)response)
				.addCookie(new Cookie(PARTNER_CODE_COOKIE_NAME, partnerCode));
		}
		
		chain.doFilter(request, response);
	}

}
