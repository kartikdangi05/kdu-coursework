package com.handson.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserModel {
    private String userName;
    private String password;
    private String email;
    private String role;
}
