package com.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String productName;
	private double price;
	private String description;
	private int quantityAvailable;
	private String images;
	private int discount;
	private int rating;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	Category category;

	@JsonIgnore

	@OneToMany(mappedBy = "products")
	List<Order> orderList = new ArrayList<Order>();

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Products() {
	}

	public Products(String productName, String description, double price, int quantityAvailable, Category category,
			String images) {
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.quantityAvailable = quantityAvailable;
		this.category = category;
		this.images = images;
	}

	public Products(int productId, String productName, String description, double price, int quantityAvailable,
			Category category, String images) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.quantityAvailable = quantityAvailable;
		this.category = category;
		this.images = images;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
