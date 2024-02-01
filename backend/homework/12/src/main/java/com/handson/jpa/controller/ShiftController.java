package com.handson.jpa.controller;

import com.handson.jpa.entity.Shift;
import com.handson.jpa.service.ShiftServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {

    @Autowired
    private ShiftServices shiftService;

    @PostMapping("/add")
    public ResponseEntity<String> addShift(@RequestBody Shift shift) {
        shiftService.addShift(shift);
        return new ResponseEntity<>("Shift added successfully", HttpStatus.OK);
    }

    @GetMapping("/top3")
    public List<Shift> getTop3ShiftsByDateRange(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
    ) {
        return shiftService.findTop3Shifts(startDate, endDate);
    }
}