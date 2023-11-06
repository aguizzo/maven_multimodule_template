package com.itbulls.learnit.onlinestore.core.menu.impl;

import java.util.List;

import com.itbulls.learnit.onlinestore.core.configs.ApplicationContext;
import com.itbulls.learnit.onlinestore.core.menu.Menu;
import com.itbulls.learnit.onlinestore.core.services.UserManagementService;
import com.itbulls.learnit.onlinestore.core.services.impl.MySqlUserManagementService;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;

public class CustomerListMenu implements Menu {

	@SuppressWarnings("unused")
	private ApplicationContext context;
	private UserManagementService userManagementService;
	
	{
		userManagementService = MySqlUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		List<User> users = userManagementService.getUsers();
		
		if (users == null || users.size() == 0) 
			System.out.println(context.getString("no.users.msg"));
		else 
			for (User user : users) 
				System.out.println(user);
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("customer.list.header"));	
	}

}
