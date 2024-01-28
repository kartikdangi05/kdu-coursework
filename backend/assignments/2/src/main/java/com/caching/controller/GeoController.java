package com.caching.controller;
import com.caching.dto.ResponseDTO;
import com.caching.exceptions.InvalidRequestException;
import com.caching.services.GeoServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class GeoController {

    @Autowired
    private GeoServices geoServices ;

    /**
     * Handles the GET request for geocoding based on the provided address.
     * @param address The address for geocoding.
     * @return ResponseEntity containing the geocoded response and HTTP status.
     */
    @GetMapping("/geocoding")
    public ResponseEntity<ResponseDTO> getLatLong(@RequestParam(required = true) String address){
        ResponseDTO responseDTO = geoServices.getLatLong(address);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Handles the GET request for reverse geocoding based on the provided latitude and longitude.
     * @param latitude  The latitude for reverse geocoding.
     * @param longitude The longitude for reverse geocoding.
     * @return ResponseEntity containing the reverse geocoded response and HTTP status.
     */
    @GetMapping("/reverse-geocoding")
    public ResponseEntity<String> getAddress(@RequestParam(required = true) String latitude, @RequestParam(required = true) String longitude){
        ResponseDTO responseDTO = geoServices.getAddress(latitude,longitude);
        return new ResponseEntity<>(responseDTO.getAddress(), HttpStatus.OK);
    }

}
