package com.personalfinance.expense_tracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.entity.User;
import com.personalfinance.expense_tracker.repository.UserRepository;
import com.personalfinance.expense_tracker.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	private final UserRepository userRepo;
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	@Override
	public User registerUser(User user) {
		return userRepo.save(user);
	}
	@Override
	public List<User> getAllUsers() {
		 return userRepo.findAll();
	}
	@Override
	public User getUserById(long userId) {
		  return userRepo.findById(userId).orElse(null);
	}
	@Override
	public User updateUser(User user) {
		 return userRepo.save(user);
	}
	@Override
	public void deleteUser(long userId) {
		 userRepo.deleteById(userId);
	}
	
	
}
