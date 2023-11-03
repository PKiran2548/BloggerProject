package com.bikkadit.userServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.dto.UserDto;
import com.bikkadit.entity.User;
import com.bikkadit.exception.ResourceNotFoundException;
import com.bikkadit.repository.UserRepo;
import com.bikkadit.service.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	// to create single user
	@Override
	public UserDto createUser(UserDto userDto) {
		// userdto into user bcoz save() not save userdto
		log.info("Intiating save method to save user in database");
		User user = modelMapper.map(userDto, User.class);

		// User user = dtoToUser(userDto); // userdto to user

		User save = userRepo.save(user); // save using save();

		// UserDto dto = userToDto(user); // user into userdto

		UserDto userDto2 = modelMapper.map(save, UserDto.class);
		log.info("User save in database");
		return userDto2;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userID) {
		log.info("Initiating request to find and update user in DB");
		User user1 = userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));

		user1.setUserName(userDto.getUserName());
		user1.setPassword(userDto.getPassword());
		user1.setEmailId(userDto.getEmailId());
		user1.setAbout(userDto.getAbout());

		User user2 = userRepo.save(user1);

		UserDto userDto2 = userToDto(user2);
		log.info("User info update in database");
		return userDto2;
	}

	@Override
	public UserDto getSingleUser(Integer userID) {
		log.info("Initiating request to find and fetch user from DB");
		User user = userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userID));

		// UserDto userDtoById = userToDto(user);

		UserDto userDtoById = modelMapper.map(user, UserDto.class);
		log.info("Complete the request to find and fetch user from DB");
//		User  findById = userRepo.findById(userID).get();		
//		UserDto userDtoById = userToDto(findById); 
		return userDtoById;
	}

	@Override
	public List<UserDto> getAllUser() {
		log.info("Initiating request to find and fetch All users from DB");
		List<User> listOfUser = userRepo.findAll();

		List<UserDto> listOfUserDto = listOfUser.stream().map((user) -> userToDto(user)).collect(Collectors.toList());
		log.info("Complete request to find and fetch All users from DB");
		return listOfUserDto;
	}

	@Override
	public void deleteUser(Integer userID) {
		log.info("Initiating request to find and delete users from DB");
		User user = userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));

		userRepo.delete(user);
		log.info("Complete request to find and delete users from DB");
	}

	public User dtoToUser(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);

//		user.setUserName(userDto.getUserName());
//		user.setUserId(userDto.getUserId());
//		user.setPassword(userDto.getPassword());
//		user.setEmailId(userDto.getEmailId());
//		user.setAbout(userDto.getAbout());

		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);

//		userDto.setUserName(user.getUserName());
//		userDto.setUserId(user.getUserId());
//		userDto.setPassword(user.getPassword());
//		userDto.setEmailId(user.getEmailId());
//		userDto.setAbout(user.getAbout());

		return userDto;
	}

}
