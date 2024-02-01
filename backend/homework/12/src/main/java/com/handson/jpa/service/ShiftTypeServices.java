package com.handson.jpa.service;

import com.handson.jpa.entity.ShiftType;
import com.handson.jpa.repo.ShiftTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftTypeServices {

    @Autowired
    private ShiftTypeRepo shiftTypeRepository;

    public void addShiftType(ShiftType shiftType) {
        shiftTypeRepository.save(shiftType);
    }

}
