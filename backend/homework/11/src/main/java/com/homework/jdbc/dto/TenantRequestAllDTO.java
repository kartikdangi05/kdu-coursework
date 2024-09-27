package com.homework.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TenantRequestAllDTO {
    UserRequestDTO userRequestDTO;
    ShiftTypeRequestDTO shiftTypeRequestDTO;
    ShiftRequestDTO shiftRequestDTO;
    ShiftUserRequestDTO shiftUserRequestDTO;
}
