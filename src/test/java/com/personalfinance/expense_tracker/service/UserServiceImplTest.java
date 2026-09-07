package com.personalfinance.expense_tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.personalfinance.expense_tracker.dto.UserDTO;
import com.personalfinance.expense_tracker.entity.User;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.repository.UserRepository;
import com.personalfinance.expense_tracker.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerUser_shouldReturnSavedUser() {

        // Arrange
        UserDTO userDTO = new UserDTO();

        userDTO.setFullName("Poojitha");
        userDTO.setEmail("poojitha@gmail.com");
        userDTO.setPassword("password123");

        when(passwordEncoder.encode("password123"))
                .thenReturn("encodedPassword");

        User savedUser = new User();
        savedUser.setUserId(1L);
        savedUser.setFullName("Poojitha");
        savedUser.setEmail("poojitha@gmail.com");
        savedUser.setPassword("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        // Act
        UserDTO result = userService.registerUser(userDTO);

        // Assert
        assertNotNull(result);

        assertEquals(1L, result.getUserId());
        assertEquals("Poojitha", result.getFullName());
        assertEquals("poojitha@gmail.com", result.getEmail());

        // Password should NOT be returned in DTO
        assertNull(result.getPassword());

        // Verify password encoding
        verify(passwordEncoder).encode("password123");

        // Verify user was saved
        verify(userRepository).save(any(User.class));

        // Verify the encoded password was actually saved
        ArgumentCaptor<User> userCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        assertEquals("encodedPassword", capturedUser.getPassword());
    }
    
    
    @Test
    void getAllUsers_shouldReturnAllUsers() {

        // Arrange
        User user1 = new User();
        user1.setUserId(1L);
        user1.setFullName("Poojitha");
        user1.setEmail("poojitha@gmail.com");

        User user2 = new User();
        user2.setUserId(2L);
        user2.setFullName("Rahul");
        user2.setEmail("rahul@gmail.com");

        when(userRepository.findAll())
                .thenReturn(java.util.List.of(user1, user2));

        // Act
        java.util.List<UserDTO> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(1L, result.get(0).getUserId());
        assertEquals("Poojitha", result.get(0).getFullName());
        assertEquals("poojitha@gmail.com", result.get(0).getEmail());

        assertEquals(2L, result.get(1).getUserId());
        assertEquals("Rahul", result.get(1).getFullName());
        assertEquals("rahul@gmail.com", result.get(1).getEmail());

        verify(userRepository).findAll();
    }
    
    
    @Test
    void getUserById_shouldReturnUser() {

        // Arrange
        long userId = 1L;

        User user = new User();
        user.setUserId(userId);
        user.setFullName("Poojitha");
        user.setEmail("poojitha@gmail.com");

        when(userRepository.findById(userId))
                .thenReturn(java.util.Optional.of(user));

        // Act
        UserDTO result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);

        assertEquals(1L, result.getUserId());
        assertEquals("Poojitha", result.getFullName());
        assertEquals("poojitha@gmail.com", result.getEmail());

        verify(userRepository).findById(userId);
    }
    
    
    @Test
    void getUserById_shouldThrowException_whenUserNotFound() {

        // Arrange
        long userId = 999L;

        when(userRepository.findById(userId))
                .thenReturn(java.util.Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> userService.getUserById(userId)
                );

        assertEquals(
                "User not found with id: 999",
                exception.getMessage()
        );

        verify(userRepository).findById(userId);
    }
    
    
    @Test
    void updateUser_shouldReturnUpdatedUser() {

        // Arrange
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(1L);
        userDTO.setFullName("Poojitha Updated");
        userDTO.setEmail("poojitha.updated@gmail.com");
        userDTO.setPassword("newpassword123");

        User existingUser = new User();
        existingUser.setUserId(1L);
        existingUser.setFullName("Poojitha");
        existingUser.setEmail("poojitha@gmail.com");

        User updatedUser = new User();
        updatedUser.setUserId(1L);
        updatedUser.setFullName("Poojitha Updated");
        updatedUser.setEmail("poojitha.updated@gmail.com");
        updatedUser.setPassword("encodedNewPassword");

        when(userRepository.findById(1L))
                .thenReturn(java.util.Optional.of(existingUser));

        when(passwordEncoder.encode("newpassword123"))
                .thenReturn("encodedNewPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(updatedUser);

        // Act
        UserDTO result = userService.updateUser(userDTO);

        // Assert
        assertNotNull(result);

        assertEquals(1L, result.getUserId());
        assertEquals("Poojitha Updated", result.getFullName());
        assertEquals("poojitha.updated@gmail.com", result.getEmail());

        // Password must not be exposed
        assertEquals(null, result.getPassword());

        verify(userRepository).findById(1L);
        verify(passwordEncoder).encode("newpassword123");
        verify(userRepository).save(any(User.class));
    }
    
    
    
    @Test
    void updateUser_shouldThrowException_whenUserNotFound() {

        // Arrange
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(999L);
        userDTO.setFullName("Unknown User");
        userDTO.setEmail("unknown@gmail.com");
        userDTO.setPassword("password123");

        when(userRepository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> userService.updateUser(userDTO)
                );

        assertEquals(
                "User not found with id: 999",
                exception.getMessage()
        );

        verify(userRepository).findById(999L);

        // These operations must NOT happen
        verify(passwordEncoder, never()).encode(any(String.class));
        verify(userRepository, never()).save(any(User.class));
    }
    
    @Test
    void deleteUser_shouldDeleteUser() {

        // Arrange
        long userId = 1L;

        User user = new User();
        user.setUserId(userId);
        user.setFullName("Poojitha");
        user.setEmail("poojitha@gmail.com");

        when(userRepository.findById(userId))
                .thenReturn(java.util.Optional.of(user));

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository).findById(userId);
        verify(userRepository).deleteById(userId);
    }
    
    
    @Test
    void deleteUser_shouldThrowException_whenUserNotFound() {

        // Arrange
        long userId = 999L;

        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> userService.deleteUser(userId)
                );

        assertEquals(
                "User not found with id: 999",
                exception.getMessage()
        );

        // Verify user was searched
        verify(userRepository).findById(userId);

        // Delete must NOT happen
        verify(userRepository, never()).deleteById(userId);
    }
}