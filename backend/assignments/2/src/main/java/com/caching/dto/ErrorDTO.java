package com.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * If there is any custom exception, it will be thrown
 */
@AllArgsConstructor
@Data
public class ErrorDTO {
    String message;
    int statusCode;
}
