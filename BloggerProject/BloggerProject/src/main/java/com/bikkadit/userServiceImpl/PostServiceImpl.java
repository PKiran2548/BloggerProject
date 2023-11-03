package com.bikkadit.userServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bikkadit.dto.PostDto;
import com.bikkadit.entity.Category;
import com.bikkadit.entity.Post;
import com.bikkadit.entity.User;
import com.bikkadit.exception.ResourceNotFoundException;
import com.bikkadit.helper.PostResponce;
import com.bikkadit.repository.CategoryRepo;
import com.bikkadit.repository.PostRepo;
import com.bikkadit.repository.UserRepo;
import com.bikkadit.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryID) {
		log.info("Initiating the request to create post with userID{}:", userId + "CategoryID{}:", categoryID);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userID", userId));

		Category category = this.categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryID", categoryID));

		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post post1 = postRepo.save(post);

		PostDto postDto1 = modelMapper.map(post1, PostDto.class);
		log.info("Complete the request to create post with userID{}: ", userId + "CategoryID{}:", categoryID);
		return postDto1;
	}
	

	@Override
	public PostDto getSinglePost(Integer postID) {
		log.info("Initiating the request to fetch post with postID {}:", postID);
		Post post = this.postRepo.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postID", postID));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		log.info("Complete the request to fetch post with postID {}:", postID);
		return postDto;
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

		Page<Post> pageOfPost = this.postRepo.findAll(p); // one page
		List<Post> allPost = pageOfPost.getContent(); // list of pages
		List<PostDto> postDtos = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponce postResponce = new PostResponce();
		postResponce.setContaint(postDtos);
		postResponce.setPageNumber(pageOfPost.getNumber());
		postResponce.setPageSize(pageOfPost.getSize());
		postResponce.setTotalElement(pageOfPost.getTotalElements());
		postResponce.setTotalPages(pageOfPost.getTotalPages());
		postResponce.setLastPage(pageOfPost.isLast());

		return postResponce;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryID) {
		log.info("Initiating the request to fetch post with categoryID {}:", categoryID);
		Category category = this.categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryID", categoryID));
		List<Post> listOfPost = postRepo.findByCategory(category);
		List<PostDto> postDtos = listOfPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		log.info("Complete the request to fetch post with category {}:", categoryID);
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUsr(Integer userID) {
		log.info("Initiating the request to fetch post of user with userID {}:", userID);
		User user = this.userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));
		List<Post> listOfPost = this.postRepo.findByUser(user);
		List<PostDto> postDtos = listOfPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		log.info("Complete the request to fetch post of user with userID {}:", userID);
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		log.info("Initiating the request to fetch post with title keyword {}:", keyword);
		List<Post> byTitleContaining = this.postRepo.findByPostTitleContaining(keyword);
		List<PostDto> postDtosByTitle = byTitleContaining.stream()
				.map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		log.info("Complete the request to fetch post with title keyword {}:", keyword);
		return postDtosByTitle;
	}

}
