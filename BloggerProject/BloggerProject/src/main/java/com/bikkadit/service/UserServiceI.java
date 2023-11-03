package com.bikkadit.service;

import java.util.List;

import com.bikkadit.dto.UserDto;

public interface UserServiceI {

	// to create user
	public UserDto createUser(UserDto userDto);

	// to update user
	public UserDto updateUser(UserDto userDto, Integer userID);

	// to get single user
	public UserDto getSingleUser(Integer userID);

	// to get all user
	public List<UserDto> getAllUser();

	// to delete single user
	public void deleteUser(Integer userID);

}
