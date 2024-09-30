package com.dzenang.springsecurityexample.controller;

import com.dzenang.springsecurityexample.dto.LoginResponse;
import com.dzenang.springsecurityexample.dto.UserDto;
import com.dzenang.springsecurityexample.model.User;
import com.dzenang.springsecurityexample.service.AuthenticationService;
import com.dzenang.springsecurityexample.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RequestMapping("/auth")
@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserDto userDto) {
        User authenticatedUser = authenticationService.login(userDto);

        String jwtToken = jwtService.generateToken(new HashMap<>(), authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getJwtExpiration());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserDto userDto) {
        User registeredUser = authenticationService.signUp(userDto);
        return ResponseEntity.ok(registeredUser);
    }
}
