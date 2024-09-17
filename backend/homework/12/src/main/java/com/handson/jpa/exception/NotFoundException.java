package com.handson.jpa.exception;

import com.handson.jpa.dto.ErrorDTO;
import lombok.Data;

@Data
public class NotFoundException extends RuntimeException{
    private final ErrorDTO errorDTO;

    public NotFoundException(String message){
        super(message);
        this.errorDTO = new ErrorDTO(message,404);
    }
}
