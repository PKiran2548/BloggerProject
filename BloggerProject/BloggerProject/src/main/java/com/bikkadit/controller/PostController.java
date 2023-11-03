package com.bikkadit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.dto.PostDto;
import com.bikkadit.helper.AppConstant;
import com.bikkadit.helper.PostResponce;
import com.bikkadit.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	Logger log = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	/**
	 * @author Kiran
	 * @apiNote To create a post in database
	 * @since v 4.0.0
	 * @param postDto
	 * @param userID
	 * @param categoryID
	 * @return postDto1
	 */
	@PostMapping("/post/user/{userId}/category/{categoryID}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userID,
			@PathVariable("categoryID") Integer categoryID) {
		log.info("Getting the request to create post with userID{}:", userID + "categoryID{}:", categoryID);
		PostDto postDto1 = this.postService.createPost(postDto, userID, categoryID);
		log.info("Completed the request to create post with userID{}:", userID + "categoryID{}:", categoryID);
		return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);
	}

	/**
	 * @author Kiran
	 * @apiNote To get the post by using categoryID from database
	 * @since v 4.0.0
	 * @param categoryID
	 * @return postByCategory
	 */

	@GetMapping("/post/category/{categoryID}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryID") Integer categoryID) {
		log.info("Getting the request to get post by category with categoryID{}:", categoryID);
		List<PostDto> postByCategory = this.postService.getPostByCategory(categoryID);
		log.info("Completed the request to get post by category with categoryID{}:", categoryID);
		return new ResponseEntity<List<PostDto>>(postByCategory, HttpStatus.OK);
	}

	/**
	 * @author Kiran
	 * @apiNote To get all post of user from database
	 * @since v 4.0.0
	 * @param userID
	 * @return postByUsr
	 */

	@GetMapping("/post/user/{userID}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userID") Integer userID) {
		log.info("Getting the requst to fetch the post of User by userID{};", userID);
		List<PostDto> postByUsr = this.postService.getPostByUsr(userID);
		log.info("Completed the requst to fetch the post of User by userID{};", userID);
		return new ResponseEntity<List<PostDto>>(postByUsr, HttpStatus.OK);
	}

	/**
	 * @author Kiran
	 * @apiNote To get single post by using postID
	 * @since v 4.0.0
	 * @param postID
	 * @return singlePost
	 */

	@GetMapping("/post/{postID}")
	public ResponseEntity<PostDto> getSinglepost(@PathVariable("postID") Integer postID) {
		log.info("Getting the request to fetch post using postID {};", postID);
		PostDto singlePost = this.postService.getSinglePost(postID);
		log.info("Completed the request to fetch the post using postID {}:", postID);
		return new ResponseEntity<PostDto>(singlePost, HttpStatus.OK);
	}

	/**
	 * @author Kiran
	 * @apiNote To get the list of posts from database
	 * @since v 4.0.0
	 * @return postResponce
	 */
	@GetMapping("/allPost")
	public ResponseEntity<PostResponce> getallPost(

			@RequestParam(value = "pageNumber", defaultValue =AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortby)

	{
		log.info("getting the request to get all post ");
		PostResponce postResponce = this.postService.getAllPost(pageNumber, pageSize, sortBy);
		log.info("Completed the request to get all post ");
		return new ResponseEntity<PostResponce>(postResponce, HttpStatus.OK);
	}

	/**
	 * @author Kiran
	 * @apiNote To get post containg the keyword in title
	 * @param keyword
	 * @return List<PostDto>
	 */

	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitleContaing(@PathVariable("keyword") String keyword) {
		log.info("getting the request to get post whoes title contain keyword {}:", keyword);
		List<PostDto> searchPost = this.postService.searchPost(keyword);
		log.info("Completed the request to get post whoes title contain keyword {};", keyword);
		return new ResponseEntity<List<PostDto>>(searchPost, HttpStatus.OK);
	}

}
