package com.homework.jdbc.controller;

import com.homework.jdbc.dto.ShiftRequestDTO;
import com.homework.jdbc.entities.Shift;
import com.homework.jdbc.services.ShiftServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {

    @Autowired
    private ShiftServices shiftService;

    @PostMapping("/add")
    public ResponseEntity<String> addShift(@RequestBody ShiftRequestDTO shiftRequestDTO) {
        shiftService.addShift(shiftRequestDTO);
        return new ResponseEntity<>("Shift added successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Shift>> getShift(@PathVariable String id){
        List<Shift> shifts = shiftService.findById(id);
        return new ResponseEntity<>(shifts,HttpStatus.OK);
    }
}