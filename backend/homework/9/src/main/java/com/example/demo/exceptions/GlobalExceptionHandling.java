package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleVehicleNotFoundException(VehicleNotFoundException ex){
        return new ResponseEntity<>(ex.getErrorDTO(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidVehicleRequestException.class)
    public ResponseEntity<ErrorDTO> handleInvalidVehicleRequestException(InvalidVehicleRequestException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<ErrorDTO> handleEmptyListException(EmptyListException ex){
        return new ResponseEntity<>(ex.getErrorDTO(),HttpStatus.NOT_FOUND);
    }
}
