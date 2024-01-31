package com.homework.jdbc.controller;

import com.homework.jdbc.dto.ShiftUserRequestDTO;
import com.homework.jdbc.services.ShiftUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shift-user")
public class ShiftUsersController {

    @Autowired
    private ShiftUsersServices shiftUserService;

    @PostMapping("/add")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserRequestDTO shiftUserRequestDTO) {
        shiftUserService.addShiftUser(shiftUserRequestDTO);
        return new ResponseEntity<>("ShiftUser added successfully", HttpStatus.OK);
    }


}
