package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import lombok.Getter;

@Getter
public class EmptyListException extends RuntimeException{
    ErrorDTO errorDTO;
    public EmptyListException(String message){
        super(message);
        errorDTO = new ErrorDTO(message,404);
    }
}
