package com.handson.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponseListDTO {
    List<UserResponseDTO> userList;
    String message;

}
