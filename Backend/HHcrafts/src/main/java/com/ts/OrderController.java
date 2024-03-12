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
import com.dao.OrderDao;
import com.model.Order;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

	@Autowired
	private OrderDao orderDao;

	@GetMapping("/getAllOrders")
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();

	}

	@PostMapping("/addOrder")
	public Order addOrder(@RequestBody Order order) {
		return orderDao.addOrder(order);
	}

	@GetMapping("/getOrder/{orderId}")
	public Order getOrderById(@PathVariable int orderId) {
		return orderDao.getOrderById(orderId);
	}

	@PutMapping("/updateorder")
	public Order updateOrder(@RequestBody Order order) {
		return orderDao.updateOrder(order);
	}

	@DeleteMapping("/deleteOrder/{orderId}")
	public void deleteOrder(@PathVariable int orderId) {
		orderDao.deleteOrder(orderId);
	}
}
