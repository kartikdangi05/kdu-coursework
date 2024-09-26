package com.caching.exceptions;

import com.caching.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
    /**
     * For any generic exception
     * @param ex
     * @return Response thrown by exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Custom exception
     * @param ex
     * @return errorDTO object
     */
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorDTO> handleInvalidResponseException(InvalidRequestException ex){
        return new ResponseEntity<>(ex.errorDTO,HttpStatus.NOT_FOUND);
    }

}
