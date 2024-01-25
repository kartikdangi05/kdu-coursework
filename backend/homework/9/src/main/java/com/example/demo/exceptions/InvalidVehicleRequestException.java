package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import lombok.Getter;

@Getter
public class InvalidVehicleRequestException extends RuntimeException {
    public final ErrorDTO errorDTO;
    public InvalidVehicleRequestException(String message){
        super(message);
        errorDTO = new ErrorDTO(message,400);
    }

}
