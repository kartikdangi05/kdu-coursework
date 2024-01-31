package com.homework.jdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String username;
    private int loggedIn;
    private String timeZone;
    private UUID tenantId;
    private Date createdAt;
    private Date updatedAt;
}