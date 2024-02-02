package com.assessment.two.controller;

import com.assessment.two.config.CustomAuthenticationManager;
import com.assessment.two.dto.UserLoginDTO;
import com.assessment.two.entity.Users;
import com.assessment.two.filters.TokenGeneratorFilter;
import com.assessment.two.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final CustomAuthenticationManager customAuthProvider;
    private final TokenGeneratorFilter tokenGeneratorFilter;

    @Autowired
    public UserController(CustomAuthenticationManager customAuthProvider, TokenGeneratorFilter tokenGeneratorFilter,
                          UserService userService) {
        this.customAuthProvider = customAuthProvider;
        this.tokenGeneratorFilter = tokenGeneratorFilter;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(Users user){
        userService.addUser(user);
        return new ResponseEntity<>("User added successfully!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            Authentication authentication = customAuthProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
            );
            String token = tokenGeneratorFilter.generateJWT(authentication);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
