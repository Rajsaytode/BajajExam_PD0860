package com.app.service;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.app.dto.UserDto;

@Service

public class UserService {
	
	List<String> phoneList = new ArrayList<String>();
	List<String> emailList = new ArrayList<String>();
	
	
	public String validateUser(UserDto user) {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		double phoneNumber = user.getPhoneNumber();
		String emailId = user.getEmailId();
		
		String regex = "^[A-Za-z]+$";
		String regexNumber = "^[0-9]{10}$";
		String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		
		Pattern pattern = Pattern.compile(regex);
		Pattern pattern1 = Pattern.compile(regexNumber);
		Pattern pattern2 = Pattern.compile(regexEmail);
		
		Matcher firstNameMatch = pattern.matcher(firstName);
		Matcher lastNameMatch = pattern.matcher(lastName);
		
		String phoneNumber1 = String.valueOf((long)phoneNumber);
		Matcher phoneNumberMatch = pattern1.matcher(phoneNumber1);
		
		Matcher emailIdMatch = pattern2.matcher(emailId);
		
		if(phoneList.contains(phoneNumber1)) {
			return "phone Number can't be duplicated";
		}
		
		if(emailList.contains(emailId)) {
			return "emailId can't be duplicated";
		}
		
		
		
		if(!firstNameMatch.matches())
			return "first name does not match";
		
		if(!lastNameMatch.matches())
			return "last name does not match";
		
		if(!phoneNumberMatch.matches()) {
			System.out.println(phoneNumber1);
			System.out.println(phoneNumber);
			return "Phone  does not match "+ phoneNumber1;
		}
		if(!emailIdMatch.matches())
			return "email does not match";
		
		if(firstName=="" || lastName==""  || emailId=="") {
			return "field is required";
		}
		
		if(phoneNumber/100000000>=1 && phoneNumber/100000000<=9) {
			return "phoneNumber is required";
		}
		
		phoneList.add(phoneNumber1);
		emailList.add(emailId);
		
		return " Valid User " + " PD0860 ";
		
		
		
		
	}
}
