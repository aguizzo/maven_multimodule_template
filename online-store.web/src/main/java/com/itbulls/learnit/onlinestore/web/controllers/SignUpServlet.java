package com.itbulls.learnit.onlinestore.web.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itbulls.learnit.onlinestore.web.filters.PartnerCodeFilter.PARTNER_CODE_COOKIE_NAME;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.impl.DefaultUser;



public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserFacade userFacade = DefaultUserFacade.getInstance();
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String baseUrl = request.getScheme()
				+ "://"
				+ request.getServerName()
				+ ":"
				+ request.getServerPort()
				+ request.getServletContext().getContextPath();
		
		User user = new DefaultUser();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		String partnerCode = null;
		if(request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals(PARTNER_CODE_COOKIE_NAME)) {
					partnerCode = cookie.getValue();
				}
			}
		}
		
		userFacade.registerUser(user, partnerCode);
		response.sendRedirect(baseUrl + "/login.html");
	}

}
