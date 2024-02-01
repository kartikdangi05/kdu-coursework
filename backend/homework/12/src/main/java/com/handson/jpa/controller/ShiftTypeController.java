package com.handson.jpa.controller;

import com.handson.jpa.entity.ShiftType;
import com.handson.jpa.service.ShiftTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shift-type")
public class ShiftTypeController {

    @Autowired
    private ShiftTypeServices shiftTypeService;

    @PostMapping("/add")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftType shiftType) {
        shiftTypeService.addShiftType(shiftType);
        return new ResponseEntity<>("ShiftType added successfully", HttpStatus.OK);
    }

}
