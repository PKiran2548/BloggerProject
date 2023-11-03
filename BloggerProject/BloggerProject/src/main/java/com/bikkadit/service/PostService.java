package com.bikkadit.service;

import java.util.List;

import com.bikkadit.dto.PostDto;
import com.bikkadit.entity.Post;
import com.bikkadit.helper.PostResponce;

public interface PostService {

	// to create post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryID);

	

	// to get post
	PostDto getSinglePost(Integer postID);

	// to get all post
	PostResponce getAllPost(Integer PageNumber, Integer pageSize, String sortBy);

	// to get all post by category
	List<PostDto> getPostByCategory(Integer categoryID);

	// to get all post by category
	List<PostDto> getPostByUsr(Integer userID);

	// to search post
	List<PostDto> searchPost(String keyword);

}
