package com.personalfinance.expense_tracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.dto.UserDTO;
import com.personalfinance.expense_tracker.entity.User;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.repository.UserRepository;
import com.personalfinance.expense_tracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {

        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User savedUser = userRepo.save(user);

        UserDTO response = new UserDTO();
        response.setUserId(savedUser.getUserId());
        response.setFullName(savedUser.getFullName());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());

        return response;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = userRepo.findAll();

        return users.stream().map(user -> {

            UserDTO dto = new UserDTO();
            dto.setUserId(user.getUserId());
            dto.setFullName(user.getFullName());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());

            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(long userId) {

    	User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());

        return dto;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User updatedUser = userRepo.save(user);

        UserDTO response = new UserDTO();
        response.setUserId(updatedUser.getUserId());
        response.setFullName(updatedUser.getFullName());
        response.setEmail(updatedUser.getEmail());
        response.setPassword(updatedUser.getPassword());

        return response;
    }

    @Override
    public void deleteUser(long userId) {
        userRepo.deleteById(userId);
    }
}