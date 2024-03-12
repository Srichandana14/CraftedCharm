package com.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("from Customer where firstName = :cName")
	List<Customer> findByCustomerName(@Param("cName") String firstName);

	@Query("UPDATE Customer SET password = :encryptedPassword WHERE customerid = :customerId")
	Customer updatePassword(@Param("customerId") int customerId, @Param("encryptedPassword") String encryptedPassword);

	@Query("from Customer where emailAddress = :emailAddress and password = :password")
	Customer customerLogin(@Param("emailAddress") String emailAddress, @Param("password") String password);

	@Query("from Customer where emailAddress = :emailAddress")
	Customer findByEmailId(@Param("emailAddress") String emailAddress);

	@Query("from Customer where phoneNumber = :phoneNumber")
	Customer findByPhoneNumber(String phoneNumber);
}
