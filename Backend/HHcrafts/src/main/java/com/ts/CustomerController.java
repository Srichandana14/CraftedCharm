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
import com.dao.CustomerDao;
import com.model.Credentials;
import com.model.Customer;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private CustomerDao customerDao;

	@GetMapping("/getAllCustomers")
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	@GetMapping("getCustomerEmailId/{emailAddress}")
	public Customer getCustomerEmailAddress(@PathVariable String emailAddress) {
		return customerDao.getCustomerEmailId(emailAddress);
	}

	@PostMapping("/addCustomer")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerDao.addCustomer(customer);
	}

	@GetMapping("/getCustomer/{customerId}")
	public Customer getCustomerById(@PathVariable int customerId) {
		return customerDao.getCustomerById(customerId);
	}

	@PutMapping("/updateCustomer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerDao.updateCustomer(customer);
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public void deleteCustomer(@PathVariable int customerId) {
		customerDao.deleteCustomer(customerId);
	}

	@PutMapping("updateCustomerPassword")
	public Customer updateUserPassword(@RequestBody Credentials cred) {
		System.out.println(cred.getEmailAddress());
		System.out.println(cred.getPassword());
		return customerDao.updateCustomerPassword(cred.getEmailAddress(), cred.getPassword());
	}

	@GetMapping("customerLogin/{emailAddress}/{password}")
	public Customer customerLogin(@PathVariable String emailAddress, @PathVariable String password) {
		return customerDao.customerLogin(emailAddress, password);
	}
}
