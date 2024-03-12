package com.example.email_otp_verfication.entity;

import com.example.email_otp_verfication.util.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id")
	@SequenceGenerator(name = "user_id", initialValue = 1, allocationSize = 1, sequenceName = "user_sequence")
	private int userId;
	private String userName;
	@Column(unique = true)
	private String userEmail;
	@JsonIgnore
	private String password;
	@Column(unique = true)
	private long userPhone;
	
	private String otp;
	
	private boolean verfiied;
	
	@Enumerated(EnumType.STRING)
	private UserRoles userRoles;

}
