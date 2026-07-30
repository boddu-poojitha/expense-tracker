package com.personalfinance.expense_tracker.service;

import java.util.List;

import com.personalfinance.expense_tracker.entity.User;

public interface UserService {

	 User registerUser(User user);

	    List<User> getAllUsers();

	    User getUserById(long userId);

	    User updateUser(User user);

	    void deleteUser(long userId);
}
