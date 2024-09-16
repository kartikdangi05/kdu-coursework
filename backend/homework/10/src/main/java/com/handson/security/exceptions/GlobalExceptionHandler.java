package com.handson.security.exceptions;

import com.handson.security.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserListEmptyException.class)
    public ResponseEntity<ErrorDTO> handleUserListEmptyException(UserListEmptyException ex){
        return new ResponseEntity<>(ex.getErrorDTO(),HttpStatus.NOT_FOUND);
    }

}
