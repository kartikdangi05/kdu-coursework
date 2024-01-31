package com.homework.jdbc.services;

import com.homework.jdbc.dto.ShiftTypeRequestDTO;
import com.homework.jdbc.entities.ShiftType;
import com.homework.jdbc.repository.ShiftTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftTypeServices {

    @Autowired
    private ShiftTypeRepo shiftTypeRepository;

    public void addShiftType(ShiftTypeRequestDTO shiftTypeRequestDTO) {
        shiftTypeRepository.add(shiftTypeRequestDTO);
    }

    public List<ShiftType> findById(String id){
        return shiftTypeRepository.findById(id);
    }
}
