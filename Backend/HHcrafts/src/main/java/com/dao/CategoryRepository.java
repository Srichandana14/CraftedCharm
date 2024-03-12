package com.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query("from Category where categoryName = :categoryName")
	List<Category> findByCategoryName(String categoryName);
}
