package com.example.email_otp_verfication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.email_otp_verfication.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Users findByUserName(String userName);
	
	Users findByUserEmail(String userEmail);
	
	Users findByUserPhone(long userPhone);

	Users findByUserEmailAndPassword(String userEmail, String userPassword);
	
	Users findByUserEmailAndOtp(String userEmail,String otp);
	
}
