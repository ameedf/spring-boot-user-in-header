package com.ameed.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ameed.demo.services.UserService;

@RestController
@RequestMapping("rest/api")
public class LoginController {

	private UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public User login(@RequestBody User user) {
		return userService.login(user);
	}

	@GetMapping("/action")
	public String dummyAction(@RequestHeader(value = "Authorization") String userName) {
		User user = userService.get(userName);
		return "User is logged-in >> " + user.getId();
	}

	@GetMapping("/logout")
	public boolean login(@RequestHeader(value = "Authorization") String userName) {
		return userService.remove(userName);
	}
}
