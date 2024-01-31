package com.homework.jdbc.dto;

import com.homework.jdbc.entities.ShiftType;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShiftTypeRequestDTO {

    private ShiftType.ShiftTypeName shiftTypeName;
    private String description;
    private boolean active;
    private String timeZone;
    private UUID tenantId;
}
