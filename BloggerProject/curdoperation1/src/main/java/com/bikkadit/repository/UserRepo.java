package com.bikkadit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikkadit.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
