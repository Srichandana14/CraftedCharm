package com.ts;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.dao.ProductDao;
import com.model.Products;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductDao productDao; // Injecting ProductDao dependency

	@GetMapping("getAllProducts")
	public List<Products> getAllProducts() {
		return productDao.getAllProducts();
	}

	@GetMapping("getProductByName/{productName}")
	public List<Products> getProductByName(@PathVariable("productName") String productName) {
		return productDao.getProductByName(productName);
	}

	@GetMapping("getProductById/{productId}")
	public Products getProductById(@PathVariable("productId") int productId) {
		return productDao.getProductById(productId);
	}

	@PostMapping("addProduct")
	public Products addProduct(@RequestBody Products product) {
		return productDao.addProduct(product);
	}

	@PutMapping("updateProduct")
	public Products updateProduct(@RequestBody Products product) {
		return productDao.updateProduct(product);
	}

	@DeleteMapping("deleteProductById/{id}")
	public String deleteProductById(@PathVariable("id") int prodId) {
		productDao.deleteProductById(prodId);
		return "Product with ProductId: " + prodId + ", Deleted Successfully";
	}

}
