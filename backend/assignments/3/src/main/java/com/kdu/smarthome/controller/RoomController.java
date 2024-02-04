package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.room.RoomNameDTO;
import com.kdu.smarthome.dto.room.RoomResponseDTO;
import com.kdu.smarthome.services.RoomService;
import com.kdu.smarthome.validators.Validator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping("/api/v1/room")
    public ResponseEntity<RoomResponseDTO> addRoom(@RequestParam String houseId, @RequestBody RoomNameDTO roomNameDTO, HttpServletRequest request){
        if(!Validator.isParsable(houseId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        RoomResponseDTO roomResponseDTO = roomService.addRoom(Long.parseLong(houseId),roomNameDTO.getRoomName());
        return new ResponseEntity<>(roomResponseDTO, HttpStatus.OK);
    }
}
