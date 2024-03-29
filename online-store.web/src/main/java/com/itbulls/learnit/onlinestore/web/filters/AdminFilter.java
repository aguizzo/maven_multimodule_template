package com.itbulls.learnit.onlinestore.web.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;


import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import static com.itbulls.learnit.onlinestore.persistence.dto.RoleDto.*;
import static com.itbulls.learnit.onlinestore.web.controllers.SignInServlet.LOGGED_IN_USER_ATTR;;

public class AdminFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		User user = (User)((HttpServletRequest)request)
				.getSession()
				.getAttribute(LOGGED_IN_USER_ATTR);
		System.out.println(user);
		if (user == null) {
			((HttpServletResponse)response).sendRedirect(
					request.getScheme()
					+ "://"
					+ request.getServerName()
					+ ":"
					+ request.getServerPort()
					+ request.getServletContext().getContextPath()
					+ "/login.html"
			);
			return;
		}
		
		if (user.getRoleName().equals(ADMIN_ROLE_NAME)) {
			chain.doFilter(request, response);
		}
		else {
			((HttpServletResponse)response).sendError(403);
		}
	}
}
