package com.personalfinance.expense_tracker.mapper;

import com.personalfinance.expense_tracker.dto.UserDTO;
import com.personalfinance.expense_tracker.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {

        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());

        // Never expose password
        // dto.setPassword(user.getPassword());

        return dto;
    }

    public static User toEntity(UserDTO dto) {

        if (dto == null) {
            return null;
        }

        User user = new User();

        // DO NOT set the ID here for new registrations
        // user.setUserId(dto.getUserId());

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }
}