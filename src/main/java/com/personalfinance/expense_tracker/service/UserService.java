package com.personalfinance.expense_tracker.service;

import java.util.List;

import com.personalfinance.expense_tracker.dto.UserDTO;


public interface UserService {

	UserDTO registerUser(UserDTO userDTO);

	List<UserDTO> getAllUsers();

	UserDTO getUserById(long userId);

	UserDTO updateUser(UserDTO userDTO);

	void deleteUser(long userId);
}
