package com.itbulls.learnit.onlinestore.core.menu.impl;

import java.util.Scanner;

import com.itbulls.learnit.onlinestore.core.configs.ApplicationContext;
import com.itbulls.learnit.onlinestore.core.menu.Menu;

import com.itbulls.learnit.onlinestore.core.services.UserManagementService;
import com.itbulls.learnit.onlinestore.core.services.impl.MySqlUserManagementService;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.impl.DefaultUser;

public class SignUpMenu implements Menu {

	private UserManagementService userManagementService;
	private ApplicationContext context;

	{
		userManagementService = MySqlUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		
		var sc = new Scanner(System.in);

		System.out.print(context.getString("enter.your.first.name"));
		var firstName = sc.next();
		System.out.print(context.getString("enter.your.last.name"));
		var lastName = sc.next();
		System.out.print(context.getString("enter.your.pass"));
		var password = sc.next();
		System.out.print(context.getString("enter.your.email"));
		var email = sc.next();
		// TODO use resource bundle
		System.out.print("Please, enter your credit card: ");
		var creditcard = sc.next();
		
		
		var user = new DefaultUser(firstName, lastName, password, email, creditcard);
		
		
		String errorMessage = userManagementService.registerUser(user);
		if (errorMessage != null && errorMessage.equals(MySqlUserManagementService.SUCCESSFULL_REGISTRATION_MESSAGE)) {
			context.setLoggedInUser(user);
			System.out.println(context.getString("user.created.msg"));
		} else {
			System.out.println(errorMessage);
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("sign.up.header"));
	}

}
