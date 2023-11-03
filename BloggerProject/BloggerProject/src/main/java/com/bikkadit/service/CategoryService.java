package com.bikkadit.service;

import java.util.List;

import com.bikkadit.dto.CategoryDto;

public interface CategoryService {

	// create
	public CategoryDto createCategory(CategoryDto catDto);

	// update
	public CategoryDto updateCategory(CategoryDto catDto, Integer categoryID);

	// get
	public CategoryDto getSingleCategory(Integer categoryID);

	// getAll
	public List<CategoryDto> getAllCategory();

	// delete
	public void deleteCategory(Integer categoryID);
}
