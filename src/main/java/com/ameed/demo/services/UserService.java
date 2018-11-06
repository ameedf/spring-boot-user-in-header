package com.ameed.demo.services;

import com.ameed.demo.controllers.User;

public interface UserService {

	User get(String userName);

	boolean remove(String userName);

	User login(User user);

}