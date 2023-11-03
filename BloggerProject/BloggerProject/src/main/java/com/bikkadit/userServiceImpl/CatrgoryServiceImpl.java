package com.bikkadit.userServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.dto.CategoryDto;
import com.bikkadit.entity.Category;
import com.bikkadit.exception.ResourceNotFoundException;
import com.bikkadit.repository.CategoryRepo;
import com.bikkadit.service.CategoryService;

@Service
public class CatrgoryServiceImpl implements CategoryService {

	Logger log = LoggerFactory.getLogger(CatrgoryServiceImpl.class);

	@Autowired
	private CategoryRepo catRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto catDto) {
		log.info("Initiating request to create category in DB");
		Category cat = modelMapper.map(catDto, Category.class);
		Category category = catRepo.save(cat);

		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		log.info("Complete request to create category in DB");
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto catDto, Integer categoryID) {
		log.info("Initiating request to find and update category in DB");
		Category category1 = catRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryID", categoryID));

		category1.setCategoryTitle(catDto.getCategoryTitle());
		category1.setCategoryDesc(catDto.getCategoryDesc());

		CategoryDto categoryDto1 = modelMapper.map(category1, CategoryDto.class);
		log.info("Complete request to find and update category in DB");
		return categoryDto1;
	}

	@Override
	public CategoryDto getSingleCategory(Integer categoryID) {
		log.info("Initiating request to find category from DB");
		Category category2 = catRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryID", categoryID));

		CategoryDto categoryDto2 = modelMapper.map(category2, CategoryDto.class);
		log.info("Complete request to find category from DB");
		return categoryDto2;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		log.info("Initiating request to find All category from DB");
		List<Category> list = catRepo.findAll();
		List<CategoryDto> listOfCategory = list.stream().map((category) -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
		log.info("Complete request to find category from DB");
		return listOfCategory;
	}

	@Override
	public void deleteCategory(Integer categoryID) {
		log.info("Initiating request to find and delete category from DB");
		Category category3 = catRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryID", categoryID));
		catRepo.delete(category3);
		log.info("Complete request to find and delete category from DB");
	}

}
