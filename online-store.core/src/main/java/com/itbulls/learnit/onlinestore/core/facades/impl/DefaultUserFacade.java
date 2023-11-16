package com.itbulls.learnit.onlinestore.core.facades.impl;

import static com.itbulls.learnit.onlinestore.persistence.dto.RoleDto.CUSTOMER_ROLE_NAME;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.persistence.dao.UserDao;
import com.itbulls.learnit.onlinestore.persistence.dao.impl.MySqlJdbcUserDao;
import com.itbulls.learnit.onlinestore.persistence.dto.UserDto;
import com.itbulls.learnit.onlinestore.persistence.dto.converter.UserDtoToUserConverter;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;

public class DefaultUserFacade implements UserFacade {
	
	private static DefaultUserFacade instance;
	private UserDao userDao = new MySqlJdbcUserDao();
	private UserDtoToUserConverter converter = new UserDtoToUserConverter();
	
	private DefaultUserFacade() {}
	
	public static synchronized DefaultUserFacade getInstance() {
		if (instance == null)
			instance = new DefaultUserFacade();
		return instance;
	}
	
	@Override
	public void registerUser(User user) {
		UserDto userDto = converter.convertUserToUserDto(user);
		
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
