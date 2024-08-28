package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDto;
import com.app.service.UserService;
@RestController
@RequestMapping("/user/create")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UserDto user){
		return ResponseEntity.ok(userService.validateUser(user));
	}
}
