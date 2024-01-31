package com.homework.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ShiftUserRequestDTO {
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
}
