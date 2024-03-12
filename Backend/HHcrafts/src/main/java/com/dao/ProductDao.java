package com.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Products;
import com.dao.ProductRepository;

@Service
public class ProductDao {

	@Autowired
	private ProductRepository productRepository; 

	public List<Products> getProductByName(String productName) {
		return productRepository.findByProductName(productName);
	}

	public List<Products> getAllProducts() {
		return productRepository.findAll();
	}

	public Products getProductById(int productId) {
		return productRepository.findById(productId).orElse(null); 
	}

	public Products addProduct(Products product) {
		return productRepository.save(product);
	}

	public Products updateProduct(Products product) {
		return productRepository.save(product);
	}

	public void deleteProductById(int prodId) {
		productRepository.deleteById(prodId);
	}

}
