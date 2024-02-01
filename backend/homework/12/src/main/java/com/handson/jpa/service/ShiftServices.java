package com.handson.jpa.service;

import com.handson.jpa.entity.Shift;
import com.handson.jpa.repo.ShiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShiftServices {

    @Autowired
    private ShiftRepo shiftRepository;

    public void addShift(Shift shift) {
        shiftRepository.save(shift);
    }

    public List<Shift> findTop3Shifts(LocalDate startDate, LocalDate endDate) {
        return shiftRepository.findTop3ByDateRange(startDate, endDate);
    }
}
