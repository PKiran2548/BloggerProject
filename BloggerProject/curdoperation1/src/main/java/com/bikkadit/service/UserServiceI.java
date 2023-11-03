package com.bikkadit.service;

import com.bikkadit.entity.User;

public interface UserServiceI {
	
	public User createUser (User user);
	
	public User getSingleUser (int userID);

}
