package com.homework.jdbc.controller;

import com.homework.jdbc.dto.ShiftTypeRequestDTO;
import com.homework.jdbc.entities.ShiftType;
import com.homework.jdbc.services.ShiftTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shift-type")
public class ShiftTypeController {

    @Autowired
    private ShiftTypeServices shiftTypeService;

    @PostMapping("/add")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeRequestDTO shiftTypeRequestDTO) {
        shiftTypeService.addShiftType(shiftTypeRequestDTO);
        return new ResponseEntity<>("ShiftType added successfully", HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<ShiftType>> getShiftType(@PathVariable String id){
        List<ShiftType> shiftTypes = shiftTypeService.findById(id);
        return new ResponseEntity<>(shiftTypes,HttpStatus.OK);
    }
}
