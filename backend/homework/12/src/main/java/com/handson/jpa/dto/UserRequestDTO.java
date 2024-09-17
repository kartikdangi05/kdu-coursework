package com.handson.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserRequestDTO {
    private UUID uuid;
    private String username;
    private int loggedIn;
    private String timeZone;
    private UUID tenantId;
}
