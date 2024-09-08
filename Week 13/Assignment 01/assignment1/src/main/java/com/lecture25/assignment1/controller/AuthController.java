package com.lecture25.assignment1.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lecture25.assignment1.dto.LoginRequest;
import com.lecture25.assignment1.entity.User;
import com.lecture25.assignment1.repository.UserRepository;
import com.lecture25.assignment1.service.TokenService;

@RestController
@RequestMapping("/api/v3/auth")
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String token(@RequestBody LoginRequest userLogin) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
        );

        return tokenService.generateToken(authentication);
    }

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest userRegister) {
        String encodedPassword = passwordEncoder.encode(userRegister.getPassword());
        
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        
        return "User registered successfully!";
    }
}
