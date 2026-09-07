package com.personalfinance.expense_tracker.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.personalfinance.expense_tracker.dto.AuthResponse;
import com.personalfinance.expense_tracker.dto.LoginRequest;
import com.personalfinance.expense_tracker.service.JwtService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;




@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        try {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()));

            if (authentication.isAuthenticated()) {

                String token = jwtService.generateToken(request.getEmail());

                return new AuthResponse(token);
            }

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid Credentials");

        } catch (AuthenticationException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid Email or Password");
        }
    }
}