package com.handson.jpa.service;

import com.handson.jpa.entity.ShiftUser;
import com.handson.jpa.exception.NotFoundException;
import com.handson.jpa.repo.ShiftUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.UUID;

@Service
public class ShiftUsersServices {

    @Autowired
    private ShiftUsersRepo shiftUserRepository;

    public void addShiftUser(ShiftUser shiftUser) {
        shiftUserRepository.save(shiftUser);
    }

    public void deleteShiftUser(UUID id) {
        LocalTime endTime = LocalTime.parse("23:00");
        shiftUserRepository.findShiftUserByIdAndShiftEndTime(id,endTime)
                .orElseThrow(() -> new NotFoundException("Shift user not found or shift does not end at 23:00 UTC"));

        shiftUserRepository.deleteById(id);
    }
}
