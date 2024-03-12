package com.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Order;

@Service
public class OrderDao {
	@Autowired
	private OrderRepository orderRepository;

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order getOrderById(int orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}

	public void deleteOrder(int orderId) {
		orderRepository.deleteById(orderId);
	}

	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}

}
