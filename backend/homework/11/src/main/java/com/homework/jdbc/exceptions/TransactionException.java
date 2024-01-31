package com.homework.jdbc.exceptions;

import com.homework.jdbc.dto.ErrorDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TransactionException extends RuntimeException{
    private final ErrorDTO errorDTO;

    public TransactionException(String message){
        super(message);
        errorDTO = new ErrorDTO(message, HttpStatus.BAD_REQUEST.value());
    }
}
