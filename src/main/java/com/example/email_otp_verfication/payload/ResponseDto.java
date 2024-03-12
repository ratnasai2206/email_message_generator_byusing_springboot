package com.example.email_otp_verfication.payload;

import lombok.Data;

@Data
public class ResponseDto {

	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private boolean verified;
	private String message;
}
