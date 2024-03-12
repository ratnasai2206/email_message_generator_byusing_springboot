package com.example.email_otp_verfication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.email_otp_verfication.payload.RequestDto;
import com.example.email_otp_verfication.payload.ResponseDto;
import com.example.email_otp_verfication.services.UserServices;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserServices services;
	
	@PostMapping("/user-register")
	public ResponseEntity<ResponseDto> registerUser( @RequestBody RequestDto requestDto){
		
		ResponseDto dto=this.services.registerUser(requestDto);
		return new  ResponseEntity<ResponseDto>(dto,HttpStatus.CREATED);
	}
	
	@PostMapping("/user-verified")
	public ResponseEntity<?> verifyUser(@RequestParam String email,@RequestParam String otp){
		String res=this.services.verifyUser(email, otp);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PostMapping("/user-login")
	public ResponseEntity<?> loginUser(@RequestParam String email,@RequestParam String password){
		String res=services.login(email, password);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	@PostMapping("/user-login-verfication")
	public ResponseEntity<?> verifyLogin(@RequestParam String email,@RequestParam String otp){
		String res=this.services.verifyLogin(email, otp);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
