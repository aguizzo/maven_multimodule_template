package com.itbulls.learnit.onlinestore.core.configs;

import java.util.ResourceBundle;

import com.itbulls.learnit.onlinestore.core.menu.Menu;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.Cart;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.impl.DefaultCart;

public class ApplicationContext {
	
	private static ApplicationContext instance;
	
	private User loggedInUser;
	private Menu mainMenu;
	private Cart sessionCart;
	
	private ResourceBundle rb;
	private static final String RESOURCE_BUNDLE_BASE_NAME = "i18n.labels";
	
	private ApplicationContext() {
		rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
	}
	
	public void setLoggedInUser(User user) {
		if (this.sessionCart != null) {
			this.sessionCart.clear(); // we have to clear session cart when new user is logged in
		}
		this.loggedInUser = user;
	}
	
	public User getLoggedInUser() {
		return this.loggedInUser;
	}
	
	public void setMainMenu(Menu menu) {
		this.mainMenu = menu;
	}
	
	public Menu getMainMenu() {
		return this.mainMenu;
	}

	public static ApplicationContext getInstance() {
		if (instance == null) {
			instance = new ApplicationContext();
		}
		return instance;
	}

	public Cart getSessionCart() {
		if (this.sessionCart == null) {
			this.sessionCart = new DefaultCart();
		}
		return this.sessionCart;
	}
	
	public String getString(String key) {
		return rb.getString(key);
	}

	public void changeLanguage() {
		rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
	}

}
