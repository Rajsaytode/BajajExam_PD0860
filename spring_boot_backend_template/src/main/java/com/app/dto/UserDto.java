package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserDto {
	private String firstName;
	private String lastName;
	private double phoneNumber;
	private String emailId;
	
}
