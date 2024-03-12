package com.dao;

import java.util.List;
import java.util.Random;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.model.Customer;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class CustomerDao {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private JavaMailSender mailSender;

	private static final String ACCOUNT_SID = "AC82fc0031c5192ef9a8c2b8c191b20cb4";
	private static final String AUTH_TOKEN = "3e7f0424856cec01aa58218b927ffdc6";
	private static final String TWILIO_PHONE_NUMBER = "+15106069377";

	private String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	static {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(int customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}

	public List<Customer> getCustomerByName(String firstName) {
		return customerRepository.findByCustomerName(firstName);
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public void deleteCustomer(int customerId) {
		customerRepository.deleteById(customerId);
	}

	public Customer addCustomer(Customer customer) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptedPwd = bcrypt.encode(customer.getPassword());
		customer.setPassword(encryptedPwd);
		Customer savedCustomer = customerRepository.save(customer);
		sendWelcomeEmail(savedCustomer);
		String otp = generateOTP();
		customer.setOtp(otp);
		System.out.println(customer.getOtp());
		sendOtpSMS(savedCustomer);
		return savedCustomer;
	}

	private String generateOTP() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

	private void sendWelcomeEmail(Customer customer) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(customer.getEmailAddress());
		message.setSubject("Welcome to CraftedCharm – Unwrap the Artistry of Handlooms and Handicrafts!");
		message.setText("Dear " + customer.getFirstName() + ",\n\n"
				+ "Greetings from CraftedCharm! We are thrilled to welcome you as a "
				+ "valued member of our handlooms and handicrafts community. " + "\n\n" + "At CraftedCharm,"
				+ "we take pride in curating a unique collection of exquisite handcrafted products "
				+ "that showcase the rich heritage of artisanal craftsmanship. " + "As a registered member,"
				+ "you now have exclusive access to a world of artistry and tradition.");

		mailSender.send(message);
	}

	private void sendOtpSMS(Customer customer) {
		Message message = Message.creator(new PhoneNumber(customer.getPhoneNumber()),
				new PhoneNumber(TWILIO_PHONE_NUMBER), "Your OTP for registration is: " + customer.getOtp()).create();

		System.out.println("SMS Sent SID: " + message.getSid());
	}

	private void sendOtpEmail(Customer customer) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(customer.getEmailAddress());
		message.setSubject("Password Reset OTP");
		message.setText(
				"Dear " + customer.getFirstName() + ",\n\n" + "Your OTP for password reset is: " + customer.getOtp());

		mailSender.send(message);
	}

	public Customer getCustomerEmailId(String emailAddress) {
		Customer customer = customerRepository.findByEmailId(emailAddress);
		if (customer != null) {
			String otp = generateOTP();
			customer.setOtp(otp);
			sendOtpEmail(customer);
		}
		return customer;
	}

	public Customer updateCustomerPassword(String emailAddress, String password) {
		Customer customer = customerRepository.findByEmailId(emailAddress);
		Customer savedCustomer = null;
		if (customer != null) {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String encryptedPwd = bcrypt.encode(password);
			customer.setPassword(encryptedPwd);
			savedCustomer = customerRepository.save(customer);
		}
		return savedCustomer;
	}

	public Customer customerLogin(String emailAddress, String password) {
		Customer customer = customerRepository.findByEmailId(emailAddress);
		if (customer != null) {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			if (bcrypt.matches(password, customer.getPassword())) {
				return customer;
			}
		}
		return null;
	}
}