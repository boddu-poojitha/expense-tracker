package com.personalfinance.expense_tracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.dto.UserDTO;
import com.personalfinance.expense_tracker.entity.User;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.mapper.UserMapper;
import com.personalfinance.expense_tracker.repository.UserRepository;
import com.personalfinance.expense_tracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {

        System.out.println("Step 1");

        User user = UserMapper.toEntity(userDTO);

        System.out.println("Step 2");

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        System.out.println("Step 3");

        User savedUser = userRepository.save(user);

        System.out.println("Step 4");

        return UserMapper.toDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {

        userRepository.findById(userDTO.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userDTO.getUserId()));

        User user = UserMapper.toEntity(userDTO);

        // Encode password before updating
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User updatedUser = userRepository.save(user);

        return UserMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(long userId) {

        userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        userRepository.deleteById(userId);
    }
}