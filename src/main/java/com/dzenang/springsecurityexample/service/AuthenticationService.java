package com.dzenang.springsecurityexample.service;

import com.dzenang.springsecurityexample.dto.UserDto;
import com.dzenang.springsecurityexample.model.User;
import com.dzenang.springsecurityexample.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthenticationService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User signUp(UserDto input) {
        User user = new User(input.getUsername(), passwordEncoder.encode(input.getPassword()), true);
        return userRepository.save(user);
    }

    public User login(UserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));

        return userRepository.findByUsername(input.getUsername()).orElseThrow();
    }
}
