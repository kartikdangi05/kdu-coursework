package com.handson.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String userName;
    private String email;
    private String role;
    private String message;
}
