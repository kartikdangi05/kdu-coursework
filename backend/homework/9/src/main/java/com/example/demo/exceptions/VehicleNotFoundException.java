package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import lombok.Getter;

@Getter
public class VehicleNotFoundException extends RuntimeException{
    public final ErrorDTO errorDTO;
    public VehicleNotFoundException(String message){
        super(message);
        errorDTO = new ErrorDTO(message,404);
    }
}
