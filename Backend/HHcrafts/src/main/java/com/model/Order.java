package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customerorder")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
	
	@ManyToOne
    @JoinColumn(name = "customer_id") 
    private Customer customer;

	@ManyToOne
	@JoinColumn(name = "productId")
    private Products products;
    
    public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public Order() {
    }
    
    public Order(int orderId) {
        this.orderId = orderId;
    }
    
    public int getOrderId() {
        return orderId;
    }
    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + "]";
    }

}