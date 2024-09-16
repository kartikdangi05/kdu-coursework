package com.handson.security.controller;

import com.handson.security.config.CustomAuthenticationManager;
import com.handson.security.dto.UserRequestDTO;
import com.handson.security.filters.TokenGeneratorFilter;
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
@RequestMapping("/auth")
public class JWTGenerateToken {

    private final CustomAuthenticationManager customAuthProvider;
    private final TokenGeneratorFilter tokenGeneratorFilter;

    @Autowired
    public JWTGenerateToken(CustomAuthenticationManager customAuthProvider, TokenGeneratorFilter tokenGeneratorFilter) {
        this.customAuthProvider = customAuthProvider;
        this.tokenGeneratorFilter = tokenGeneratorFilter;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDTO requestLoginDTO) {
        try {
            Authentication authentication = customAuthProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(requestLoginDTO.getUserName(), requestLoginDTO.getPassword())
            );
            String token = tokenGeneratorFilter.generateJWT(authentication);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
