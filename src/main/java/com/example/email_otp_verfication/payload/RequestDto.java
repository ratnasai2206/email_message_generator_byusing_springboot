package com.example.email_otp_verfication.payload;

import lombok.Data;

@Data
public class RequestDto {

	private String userName;
	private String userEmail;
	private String userPassword;
	private long userPhone;
	
}
