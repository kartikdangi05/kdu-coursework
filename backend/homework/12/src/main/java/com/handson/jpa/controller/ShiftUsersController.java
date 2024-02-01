package com.handson.jpa.controller;

import com.handson.jpa.entity.ShiftUser;
import com.handson.jpa.service.ShiftUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/shift-user")
public class ShiftUsersController {

    @Autowired
    private ShiftUsersServices shiftUserService;

    @PostMapping("/add")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.addShiftUser(shiftUser);
        return new ResponseEntity<>("ShiftUser added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteShiftUser(@PathVariable UUID userId) {
        shiftUserService.deleteShiftUser(userId);
    }


}
