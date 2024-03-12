package com.example.email_otp_verfication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServices {

	@Autowired
	JavaMailSender javaMailSender;
	
	
	public void sendEmail(String to,String subject,String body) {
		
		
		
		try {
			MimeMessage message=javaMailSender.createMimeMessage();
			MimeMessageHelper  helper=new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
