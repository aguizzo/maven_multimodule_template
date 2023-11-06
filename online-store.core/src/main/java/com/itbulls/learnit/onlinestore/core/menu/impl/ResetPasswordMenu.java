package com.itbulls.learnit.onlinestore.core.menu.impl;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import com.itbulls.learnit.onlinestore.core.configs.ApplicationContext;
import com.itbulls.learnit.onlinestore.core.menu.Menu;
import com.itbulls.learnit.onlinestore.core.services.UserManagementService;
import com.itbulls.learnit.onlinestore.core.services.impl.MySqlUserManagementService;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;

public class ResetPasswordMenu implements Menu {
	
	private UserManagementService userManagementService;
	private ApplicationContext context;
	
	{
		userManagementService = MySqlUserManagementService.getInstance();
		context =  ApplicationContext.getInstance();
	}
	

	@Override
	public void start() {
		printMenuHeader();
		var sc = new Scanner(System.in);
		String userInput = sc.next();
		System.out.println(context.getString("pass.sent.to.email"));
		CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			User user = userManagementService.getUserByEmail(userInput);
			userManagementService.resetPasswordForUser(user);
		});
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("reset.pass.header"));
		System.out.print(context.getString("enter.your.email.msg"));
	}

}
