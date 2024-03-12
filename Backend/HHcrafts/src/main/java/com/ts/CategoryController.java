package com.ts;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dao.CategoryDao;
import com.model.Category;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryDao categoryDao; // Injecting CategoryDao dependency

	@GetMapping("/getCategories")
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	@GetMapping("/categories/{categoryId}")
	public Category getCategoryById(@PathVariable Long categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	@PostMapping("/addCategories")
	public Category addCategory(@RequestBody Category category) {
		return categoryDao.addCategory(category);
	}

	@PutMapping("/updateCategory")
	public Category updateCategory(@RequestBody Category category) {
		return categoryDao.updateCategory(category);
	}

	@DeleteMapping("/deleteCategory/{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		categoryDao.deleteCategory(categoryId);
	}

}
