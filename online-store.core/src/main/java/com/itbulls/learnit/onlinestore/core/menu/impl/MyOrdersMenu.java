package com.itbulls.learnit.onlinestore.core.menu.impl;


import com.itbulls.learnit.onlinestore.core.configs.ApplicationContext;
import com.itbulls.learnit.onlinestore.core.menu.Menu;
import com.itbulls.learnit.onlinestore.core.services.PurchaseManagementService;
import com.itbulls.learnit.onlinestore.core.services.impl.MySqlPurchaseManagementService;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.Purchase;

public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private PurchaseManagementService purchaseManagementService;
	{
		context = ApplicationContext.getInstance();
		purchaseManagementService = MySqlPurchaseManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		var loggedUser = context.getLoggedInUser();
		if (loggedUser != null) {
			int loggedUserId = loggedUser.getId();
			var orders = purchaseManagementService.getPurchasesByUserId(loggedUserId);
			if (orders != null && orders.size() > 0)
				for (Purchase order : orders) 
					System.out.println(order);
			else
				System.out.println("Unfortunately, you don't have any orders yet. Navigate back to main menu to place a new order");
		}
		else {
			System.out.println("Please, log in or create new account to see list of your orders");
		}
		
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MY ORDERS *****");
	}

}
