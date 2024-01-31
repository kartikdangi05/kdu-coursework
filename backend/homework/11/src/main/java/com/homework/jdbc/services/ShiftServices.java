package com.homework.jdbc.services;

import com.homework.jdbc.dto.ShiftRequestDTO;
import com.homework.jdbc.entities.Shift;
import com.homework.jdbc.repository.ShiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServices {

    @Autowired
    private ShiftRepo shiftRepository;

    public void addShift(ShiftRequestDTO shiftRequestDTO) {
        shiftRepository.add(shiftRequestDTO);
    }

    public List<Shift> findById(String uuid){
        return shiftRepository.findById(uuid);
    }
}
