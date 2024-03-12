package com.example.email_otp_verfication.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.email_otp_verfication.entity.Users;
import com.example.email_otp_verfication.payload.RequestDto;
import com.example.email_otp_verfication.payload.ResponseDto;
import com.example.email_otp_verfication.repository.UserRepository;
import com.example.email_otp_verfication.util.UserRoles;

@Service
public class UserServices {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private EmailServices emailServices;
	
	public ResponseDto registerUser(RequestDto dto){
		
		ResponseDto res=new ResponseDto();
		
		Users existingUser=this.repository.findByUserEmail(dto.getUserEmail());
		
		if(existingUser!=null) {
			
			res.setMessage("User already register");
			
			
		}else {
			Random random=new Random();
			String otp=String.format("%06d", random.nextInt(100000));
			
			Users newUser =new Users();
			newUser.setUserName(dto.getUserName());
			newUser.setUserEmail(dto.getUserEmail());
			newUser.setPassword(dto.getUserPassword());
			newUser.setOtp(otp);
			newUser.setUserPhone(dto.getUserPhone());
			newUser.setUserRoles(UserRoles.USER);
			newUser.setVerfiied(false);
			
			Users savedUser=this.repository.save(newUser);
			String subject ="Email Verfication";
			String body="Your Verification Otp is :"+savedUser.getOtp()+"";
			//Email send 
			this.emailServices.sendEmail(dto.getUserEmail(), subject, body);
			
			res.setUserId(savedUser.getUserId());
			res.setUserName(savedUser.getUserName());
			res.setUserEmail(savedUser.getUserEmail());	
			res.setUserPassword(savedUser.getPassword());
			res.setMessage("OTP Sents Successfully....!!");
		}
		return res;
	}
	
	public String verifyUser(String email,String otp) {
		String response="";
		Users user=this.repository.findByUserEmail(email);
		if(user !=null && user.isVerfiied()) {
			 response="User is already verified";
		}else if(otp.equals(user.getOtp())) {
			user.setVerfiied(true);
			this.repository.save(user);
			 response="User verified Successfully";
		}else {
			response="User not not verified";
		}
		
		return response;
	}
	
	public String login(String email,String password) {
		Users isAvailable=this.repository.findByUserEmailAndPassword(email, password);
		if(isAvailable!=null) {
			Random random=new Random();
			String otp=String.format("%06d", random.nextInt(100000));
			isAvailable.setOtp(otp);
			repository.save(isAvailable);
			String subject ="Email Verfication";
			String body="Your Verification Otp is :"+isAvailable.getOtp()+".";
			this.emailServices.sendEmail(email, subject, body);
			return "Please Enter you OTP of Login";
			}
		return null;
	}
	
	public String verifyLogin(String email,String otp) {
		
		Users user=this.repository.findByUserEmailAndOtp(email,otp);
		
		if(user!=null) {
			return "Login Successfull";
		}
		return "Login Failed";
	}
}
