package com.bikkadit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikkadit.entity.Category;
import com.bikkadit.entity.Post;
import com.bikkadit.entity.User;

@Repository
public interface PostRepo  extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser (User user);
	List<Post> findByCategory (Category category);
	
	// to search post using title
	List<Post> findByPostTitleContaining (String postTitle);
	
	// to search post using containt
	List<Post> findByContaintContaining (String containt);
}
