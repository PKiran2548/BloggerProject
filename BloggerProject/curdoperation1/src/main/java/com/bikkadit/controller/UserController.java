package com.bikkadit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.entity.User;
import com.bikkadit.service.UserServiceI;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceI userServiceI ;
	
	@PostMapping("/createUser")
	public User createUser (@RequestBody User user) {
		User createUser = userServiceI.createUser(user);
		return createUser ;
	}
	
	@GetMapping("/getSingleUserByID/{userID}")
	public User getSingleUser (int userId) {
		User singleUser = userServiceI.getSingleUser(userId);
		return singleUser ;
	}

}
