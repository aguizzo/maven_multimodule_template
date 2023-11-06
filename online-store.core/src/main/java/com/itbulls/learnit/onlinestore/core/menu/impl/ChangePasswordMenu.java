package com.itbulls.learnit.onlinestore.core.menu.impl;

import java.util.Scanner;

import com.itbulls.learnit.onlinestore.core.configs.ApplicationContext;
import com.itbulls.learnit.onlinestore.core.menu.Menu;

public class ChangePasswordMenu implements Menu {
	
	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		var sc = new Scanner(System.in);
		String userInput = sc.next();
		context.getLoggedInUser().setPassword(userInput);
		System.out.println(context.getString("change.password.msg"));
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("change.password.header"));
		System.out.print(context.getString("enter.new.pass.cta"));		
	}

}
