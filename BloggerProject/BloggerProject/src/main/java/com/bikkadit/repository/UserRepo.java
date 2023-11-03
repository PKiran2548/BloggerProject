package com.bikkadit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikkadit.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	//Optional<User> findByEmail (String email);

	Optional<User> findByEmail(String username);

}
