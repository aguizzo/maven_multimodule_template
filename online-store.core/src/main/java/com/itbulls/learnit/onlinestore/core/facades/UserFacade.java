package com.itbulls.learnit.onlinestore.core.facades;

import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;

public interface UserFacade {
	
	void registerUser(User user);
	
	User getUserByEmail(String email);
	
}
