package com.ameed.demo.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api")
public class LoginController {

	private String userId;

	@PostMapping("/login")
	public String login(@RequestBody User user) {
		this.userId = UUID.randomUUID().toString();
		return userId;
	}

	@GetMapping("/action")
	public String dummyAction(@RequestHeader(value = "Authorization") String userId) {
		if (this.userId.equals(userId)) {
			return "You are in !!";
		}
		throw new IllegalArgumentException("Invalid user");
	}
}
