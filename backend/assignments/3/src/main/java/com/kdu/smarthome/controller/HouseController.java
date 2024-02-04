package com.kdu.smarthome.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.*;
import com.kdu.smarthome.dto.house.HouseListDTO;
import com.kdu.smarthome.dto.house.HouseRequestDTO;
import com.kdu.smarthome.dto.house.HouseResponseDTO;
import com.kdu.smarthome.dto.room.RoomsDevicesDTO;
import com.kdu.smarthome.dto.user.UsernameDTO;
import com.kdu.smarthome.services.HouseService;
import com.kdu.smarthome.validators.Validator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class HouseController {
    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }
    @PostMapping("/api/v1/house")
    public ResponseEntity<HouseResponseDTO> addHouse(@RequestBody HouseRequestDTO houseRequestDTO, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        HouseResponseDTO houseResponseDTO = houseService.addHouse(houseRequestDTO,token);
        return new ResponseEntity<>(houseResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/api/v1/house/{houseId}/add-user")
    public ResponseEntity<ResponseInfoDTO> addUser(@PathVariable String houseId, @RequestBody UsernameDTO username, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        ResponseInfoDTO userHouseDTO = houseService.addUser(Long.parseLong(houseId),username.getUsername(),token);
        return new ResponseEntity<>(userHouseDTO,HttpStatus.OK);
    }

    @GetMapping("/api/v1/house")
    public ResponseEntity<HouseListDTO> getHouses() throws JsonProcessingException {
        HouseListDTO houseListDTO = houseService.getAll();
        return new ResponseEntity<>(houseListDTO,HttpStatus.OK);
    }

    @PutMapping("/api/v1/house")
    public ResponseEntity<ResponseInfoDTO> updateAddress(@RequestParam String houseId,@RequestBody String newAddress) {
        if(!Validator.isParsable(houseId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ResponseInfoDTO userHouseDTO = houseService.updateAddress(Long.parseLong(houseId),newAddress);
        return new ResponseEntity<>(userHouseDTO,HttpStatus.OK);
    }

    @GetMapping("/api/v1/house/{houseId}")
    public ResponseEntity<RoomsDevicesDTO> getRoomsDevices(@PathVariable String houseId) throws JsonProcessingException {
        RoomsDevicesDTO roomsDevicesDTO = houseService.getRoomsDevices(Long.parseLong(houseId));
        return new ResponseEntity<>(roomsDevicesDTO,HttpStatus.OK);
    }
}
