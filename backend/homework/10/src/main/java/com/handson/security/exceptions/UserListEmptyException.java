package com.handson.security.exceptions;

import com.handson.security.dto.ErrorDTO;
import lombok.Data;

@Data
public class UserListEmptyException extends RuntimeException {
    private final ErrorDTO errorDTO;

    public UserListEmptyException(String message){
        super(message);
        errorDTO = new ErrorDTO(message,404);
    }
}
