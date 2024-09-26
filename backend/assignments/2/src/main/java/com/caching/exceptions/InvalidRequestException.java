package com.caching.exceptions;

import com.caching.dto.ErrorDTO;
import lombok.Getter;

/**
 * Custom exception
 */
@Getter
public class InvalidRequestException extends RuntimeException{
    public final ErrorDTO errorDTO;

    public InvalidRequestException(String message){
        super(message);
        this.errorDTO = new ErrorDTO(message,404);
    }
}
