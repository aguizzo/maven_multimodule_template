package com.itbulls.learnit.dao;

import java.util.List;

import com.itbulls.learnit.dto.UserDto;

public interface UserDao {
	
	boolean saveUser(UserDto user);
	
	List<UserDto> getUsers();
	
	UserDto getUserByEmail(String userEmail);
	
	UserDto getUserById(int id);
}
