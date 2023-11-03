package com.bikkadit.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bikkadit.dto.CategoryDto;
import com.bikkadit.service.CategoryService;

@RestControllerAdvice
@RequestMapping("/category")
public class CategoryController {

	Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService catSer;

	/**
	 * @author Kiran
	 * @apiNote To create
	 * @since v 4.0.0
	 * @param catDto
	 * @return createCategory
	 */
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto catDto) {
		log.info("Getting the request to create category ");
		CategoryDto createCategory = catSer.createCategory(catDto);
		log.info("Create category request completed ");
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

	/**
	 * @author Kiran
	 * @apiNote To update category in database
	 * @since v 4.0.0
	 * @param catdeto
	 * @param CategoryID
	 * @return updateCategory
	 */

	@PutMapping("/update/{categoryID}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto catdeto,
			@PathVariable("categoryID") Integer categoryID) {
		log.info("Getting request to update category with categoryID {}:", categoryID);
		CategoryDto updateCategory = catSer.updateCategory(catdeto, categoryID);
		log.info("Completed request to update category with categoryID {}:", categoryID);

		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.CREATED);
	}

	/**
	 * @author Kiran
	 * @apiNote To get single user from the database
	 * @since v 4.0.0
	 * @param categoryID
	 * @return singleCategory
	 */

	@GetMapping("/get/{categoryID}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("categoryID") Integer categoryID) {
		log.info("Getting the request to fetch category using categoryID {}:", categoryID);
		CategoryDto singleCategory = catSer.getSingleCategory(categoryID);
		log.info("Completed the request to fetch category using categoryID {}:", categoryID);
		return new ResponseEntity<CategoryDto>(singleCategory, HttpStatus.OK);
	}

	/**
	 * @author Kiran
	 * @apiNote To get all the category from database
	 * @since v 4.0.0
	 * @return allCategory
	 */

	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		log.info("Getting the request to fetch all categories from DB ");
		List<CategoryDto> allCategory = catSer.getAllCategory();
		log.info("Completed the request to fetch all categories from DB ");
		return new ResponseEntity<List<CategoryDto>>(allCategory, HttpStatus.OK);
	}

	/**
	 * @author Kiran
	 * @apiNote To delete the category from database
	 * @since v 4.0.0
	 * @param categoryID
	 * @return msg
	 */
	@DeleteMapping("/delete/{categoryID}")
	public ResponseEntity<String> deleteCategory(@PathVariable("categoryID") Integer categoryID) {
		log.info("Getting request to delete the category with categoryID {}:", categoryID);
		catSer.deleteCategory(categoryID);

		String msg = "User delete sucessfully";
		log.info("Completed request to delete the category with categoryID {}:", categoryID);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

}
