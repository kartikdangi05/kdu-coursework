package com.handson.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDTO {
    private String userName;
    private String password;
    private String email;
    private String role;
}
