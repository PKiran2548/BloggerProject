package com.bikkadit.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.dto.UserDto;
import com.bikkadit.service.UserServiceI;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
//@Slf4j
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);
// Insted of creating object of Logger we user @slf4j annotation at class level
// at that time we use only log as varibale refference 
	@Autowired
	private UserServiceI userServiceI;

	/**
	 * @author Kiran
	 * @apiNote save the user in database
	 * @since v 4.0.0
	 * @param userdto
	 * @return userDto
	 */
	@PostMapping("/creat")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto) {
		log.info("Getting request to create user");
		UserDto userdto1 = userServiceI.createUser(userdto);
		log.info("create user request completed");
		return new ResponseEntity<UserDto>(userdto1, HttpStatus.CREATED);

	}

	/**
	 * @author kiran
	 * @apiNote To update user data in database
	 * @since v 4.0.0
	 * @param userDto
	 * @param userID
	 * @return userDto2
	 */

	@PutMapping("/update/{userID}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userID") Integer userID) {
		log.info("Getting request to update user with userID {}:", userID);
		UserDto userDto2 = userServiceI.updateUser(userDto, userID);
		log.info("Completed update user request of userID {}:", userID);
		return new ResponseEntity<UserDto>(userDto2, HttpStatus.CREATED);
	}

	/**
	 * @author Kiran
	 * @apiNote To get single user from database
	 * @since v 4.0.0
	 * @param userID
	 * @return singleUser
	 */

	@GetMapping("/get/{userID}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userID") Integer userID) {
		log.info("Getting the request to fetch the data with userID {}:", userID);
		UserDto singleUser = userServiceI.getSingleUser(userID);
		log.info("Completed the request to fetch the data with userID {}:", userID);
		return new ResponseEntity<UserDto>(singleUser, HttpStatus.OK);

	}

	/**
	 * @author Kiran
	 * @apiNote To get all the user from database
	 * @since v 4.0.0
	 * @return allUser
	 */

	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAllUser() {
		log.info("Getting the request to fetch the data of all user");
		List<UserDto> allUser = userServiceI.getAllUser();
		log.info("Completed the request to fetch the data of all user");
		return new ResponseEntity<List<UserDto>>(allUser, HttpStatus.OK);
	}

	/**
	 * @author Kiran
	 * @apiNote To delete the user from the database
	 * @since v 4.0.0
	 * @param userID
	 * @return msg
	 */
	@DeleteMapping("/delete/{userID}")
	public ResponseEntity<String> deleteUser(@PathVariable("userID") Integer userID) {
		log.info("Getting the request to delete the data with userID {}:", userID);
		userServiceI.deleteUser(userID);
		String msg = "User is deleted sucessfully ";
		log.info("Completed the request to dlete the data with userID {}:", userID);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
