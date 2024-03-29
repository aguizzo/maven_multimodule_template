package com.itbulls.learnit.onlinestore.web.controllers;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itbulls.learnit.onlinestore.persistence.dto.RoleDto.*;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;

public class SignInServlet extends HttpServlet {
	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";
	
	private static final long serialVersionUID = 1L;
	private UserFacade userFacade = DefaultUserFacade.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String baseUrl = request.getScheme()
				+ "://"
				+ request.getServerName()
				+ ":"
				+ request.getServerPort()
				+ request.getServletContext().getContextPath();
		
		User user = userFacade.getUserByEmail(request.getParameter("email"));
		if (user == null) {
			// Not registered email scenario
			System.out.println("Email not registered");
			response.sendRedirect(baseUrl + "/login.html");
			return;
		}
		if (!request.getParameter("password").equals(user.getPassword())) {
			// Email is registered but password doesn't match scenario
			System.out.println("Invalid password");
			response.sendRedirect(baseUrl + "/login.html");
			return;
		}
		// Successful login scenario
		request.getSession().setAttribute(LOGGED_IN_USER_ATTR, user);
		
		if (user.getRoleName().equals(ADMIN_ROLE_NAME)) {
			System.out.println("Welcome admin!");
			response.getWriter().append("Your are an admin!");
			return;
		}
		System.out.println("Welcome customer");
		response.sendRedirect(baseUrl + "/index.html");
		
	}

}
