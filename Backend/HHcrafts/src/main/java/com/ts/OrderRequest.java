package com.ts;

import java.math.BigInteger;

public class OrderRequest {

	String customerName;
	String emailAddress;
	String phoneNumber;
	BigInteger amount;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return emailAddress;
	}

	public void setEmail(String email) {
		this.emailAddress = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

}