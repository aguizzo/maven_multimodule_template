package com.itbulls.learnit.onlinestore.core.facades.impl;

import static com.itbulls.learnit.onlinestore.persistence.dto.RoleDto.CUSTOMER_ROLE_NAME;

import java.util.List;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.services.AffiliateMarketingService;
import com.itbulls.learnit.onlinestore.core.services.impl.DefaultAffiliateMarketingService;
import com.itbulls.learnit.onlinestore.persistence.dao.UserDao;
import com.itbulls.learnit.onlinestore.persistence.dao.impl.MySqlJdbcUserDao;
import com.itbulls.learnit.onlinestore.persistence.dto.UserDto;
import com.itbulls.learnit.onlinestore.persistence.dto.converter.UserDtoToUserConverter;
import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;

public class DefaultUserFacade implements UserFacade {
	
	private static DefaultUserFacade instance;
	private UserDao userDao = new MySqlJdbcUserDao();
	private UserDtoToUserConverter converter = new UserDtoToUserConverter();
	private AffiliateMarketingService marketingService = new DefaultAffiliateMarketingService();
	
	private DefaultUserFacade() {}
	
	public static synchronized DefaultUserFacade getInstance() {
		if (instance == null)
			instance = new DefaultUserFacade();
		return instance;
	}
	
	@Override
	public void registerUser(User user, String referrerCode) {
		user.setRoleName(CUSTOMER_ROLE_NAME);
		user.setPartnerCode(marketingService.generateUniquePartnerCode());
		UserDto userReferrerDto = userDao.getUserByPartnerCode(referrerCode);
		user.setReferrerUser(converter.convertUserDtoToUser(userReferrerDto));
		
		UserDto userDto = converter.convertUserToUserDto(user);
		boolean saved = userDao.saveUser(userDto);
		if (saved) {
			System.out.println("User saved to data base");
		}
		else {
			System.out.println("Failed to save user");
		}
	}

	@Override
	public User getUserByEmail(String email) {
		UserDto userDto = userDao.getUserByEmail(email);
		if (userDto != null) { 
			return converter.convertUserDtoToUser(userDto);	
		}
		return null;
	}

	@Override
	public List<User> getUsers() {
		return converter.convertUserDtosToUsers(userDao.getUsers());
	}

}
