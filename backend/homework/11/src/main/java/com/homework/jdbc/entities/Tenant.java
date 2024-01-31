package com.homework.jdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    private UUID id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
