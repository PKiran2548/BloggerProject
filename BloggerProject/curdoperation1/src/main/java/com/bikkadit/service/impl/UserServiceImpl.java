package com.bikkadit.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.entity.User;
import com.bikkadit.repository.UserRepo;
import com.bikkadit.service.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {
	
	@Autowired
	private UserRepo userRepo ;

	@Override
	public User createUser(User user) {
		User save = userRepo.save(user);
		return save;
	}

	@Override
	public User getSingleUser(int userID) {
		User userById = userRepo.findById(userID).get();
		return userById;
	}

	

	

}
