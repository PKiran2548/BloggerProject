package com.bikkadit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bikkadit.entity.User;
import com.bikkadit.exception.ResourceNotFoundException;
import com.bikkadit.repository.UserRepo;

public class CustumUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email", 0));
		return user;
	}

}
