package com.itbulls.learnit.onlinestore.core.menu.impl;

import java.util.Scanner;

import com.itbulls.learnit.onlinestore.core.configs.ApplicationContext;
import com.itbulls.learnit.onlinestore.core.menu.Menu;
import com.itbulls.learnit.onlinestore.core.services.PurchaseManagementService;
import com.itbulls.learnit.onlinestore.core.services.impl.MySqlPurchaseManagementService;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.impl.DefaultPurchase;


public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private PurchaseManagementService purchaseManagementService;

	{
		context = ApplicationContext.getInstance();
		purchaseManagementService = MySqlPurchaseManagementService.getInstance();
	}

	@Override
	public void start() {
		while (true) {
			var sc = new Scanner(System.in);
			printMenuHeader();
			var userInput = sc.next();

			if (addOrder(userInput)) {
				System.out.println(
						context.getString("thank.you.msg"));
				break;
			} 
//					// https://github.com/AndriiPiatakha/learnit_java_core/blob/master/src/com/itbulls/learnit/javacore/oop/exam/com.itbulls.learnit/menu/impl/CheckoutMenu.java
//				System.out.println("You've entered invalid credit card number. "
//						+ "Valid credit card should contain 16 digits. " + "Please, try one more time.");
		}
	}

	private boolean addOrder(String creditCard) {
		var order = new DefaultPurchase();
		if (!order.isCreditCardNumberValid(creditCard)) {
			return false;
		}
		// TODO the program ask to introduce a credit card number but it is stored in user data
		order.setCreditCardNumber(context.getLoggedInUser().getCreditCard());
		order.setCustomerId(context.getLoggedInUser().getId());
		order.setProducts(context.getSessionCart().getProducts());
		
		purchaseManagementService.addPurchase(order);
		context.getSessionCart().clear();
		return true;
	}

	@Override
	public void printMenuHeader() {
		System.out.println(context.getString("checkout.menu.header"));
		System.out.print(context.getString("enter.credit.card.number.cta"));
	}

}
