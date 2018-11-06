package com.ameed.demo.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.ameed.demo.controllers.User;
import com.ameed.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private Map<String, User> cache;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostConstruct
	public void initialize() {
		this.cache = new HashMap<>();
		userRepository.save(new User("ameed", "ameed"));
		userRepository.save(new User("dani", "dani"));
	}

	@Override
	public User get(String userName) {
		User user = cache.get(userName);
		if (user == null) {
			throw new RuntimeException("User not logged-in: " + userName);
		}
		return user;
	}

	@Override
	public boolean remove(String userName) {
		if (!cache.containsKey(userName)) {
			throw new RuntimeException("User not found: " + userName);
		}
		cache.remove(userName);
		return true;
	}

	@Override
	public User login(User loginDetails) {
		String userName = loginDetails.getUserName();
		User user = cache.get(userName);
		if (user != null) {
			throw new RuntimeException("User is already logged in: " + userName);
		}
		user = userRepository.findByUserName(userName).orElse(null);
		if (user == null) {
			throw new RuntimeException("Failed to fetch user: " + userName);
		}
		if (!loginDetails.getPassword().equals(user.getPassword())) {
			throw new RuntimeException("Bad user name or password !!");
		}
		cache.put(userName, user);
		return user;
	}
}
