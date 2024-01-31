package com.homework.jdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftType {
    public enum ShiftTypeName {
        MORNING,
        AFTERNOON,
        EVENING
    }

    private ShiftTypeName shiftTypeName;
    private UUID id;
    private String description;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
    private String timeZone;
    private UUID tenantId;


}

