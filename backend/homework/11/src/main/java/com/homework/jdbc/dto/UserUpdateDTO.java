package com.homework.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserUpdateDTO {
    private UUID id;
    private String username;
    private int loggedIn;
    private String timeZone;
}
