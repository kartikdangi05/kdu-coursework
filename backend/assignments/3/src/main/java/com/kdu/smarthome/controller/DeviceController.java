package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.device.DeviceAddDTO;
import com.kdu.smarthome.dto.device.DeviceRequestDTO;
import com.kdu.smarthome.dto.ResponseInfoDTO;
import com.kdu.smarthome.services.DeviceService;
import com.kdu.smarthome.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/api/v1/device/register")
    public ResponseEntity<ResponseInfoDTO> registerDevice(@RequestBody DeviceRequestDTO deviceRequestDTO){
        ResponseInfoDTO responseInfoDTO = deviceService.registerDevice(deviceRequestDTO);
        return new ResponseEntity<>(responseInfoDTO, HttpStatus.OK);
    }

    @PostMapping("/api/v1/device/add")
    public ResponseEntity<ResponseInfoDTO> addDevice(@RequestBody DeviceAddDTO deviceAddDTO){
        if(!Validator.isParsable(deviceAddDTO.getHouseId()) || !Validator.isParsable(deviceAddDTO.getRoomId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ResponseInfoDTO responseInfoDTO = deviceService.addDevice(deviceAddDTO);
        return new ResponseEntity<>(responseInfoDTO,HttpStatus.OK);
    }
}
