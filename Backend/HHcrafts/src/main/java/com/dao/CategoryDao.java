package com.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Category;
import com.dao.CategoryRepository;

@Service
public class CategoryDao {

	@Autowired
	private CategoryRepository categoryRepository; 

	public List<Category> getCategoryByName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId).orElse(null); 
	}

	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}

	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}

}
