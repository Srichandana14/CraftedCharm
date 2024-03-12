package com.dao;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
	@Query("from Products where productName = :pName")
	List<Products> findByProductName(@Param("pName") String productName);
}
