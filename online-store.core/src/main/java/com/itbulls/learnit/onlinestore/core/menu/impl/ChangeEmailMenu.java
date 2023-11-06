package com.itbulls.learnit.onlinestore.core.menu.impl;

import java.util.Scanner;

import com.itbulls.learnit.onlinestore.core.configs.ApplicationContext;
import com.itbulls.learnit.onlinestore.core.menu.Menu;

public class ChangeEmailMenu implements Menu {

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		var sc = new Scanner(System.in);
		String userInput = sc.next();
		context.getLoggedInUser().setEmail(userInput);
		System.out.println(context.getString("mail.changed.msg"));
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("change.email.header"));
		System.out.print(context.getString("enter.new.email.cta"));
	}

}
