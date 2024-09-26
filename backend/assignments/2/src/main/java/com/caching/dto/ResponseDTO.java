package com.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Custom response to the API calls
 */
@Data
@AllArgsConstructor
public class ResponseDTO {
    String address;
    double latitude;
    double longitude;
    String region;
}
