package com.homework.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserRequestDTO {
    private String username;
    private int loggedIn;
    private String timeZone;
    private UUID tenantId;
}
