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
        if (!shiftUserRepository.existsById(id)) {
            throw new NotFoundException("ShiftUser not found with id: " + id);
        }

        LocalTime localTime = LocalTime.parse("23:00");
        shiftUserRepository.findById(id).ifPresent(shiftUser -> {
            if (shiftUser.getShift().getTimeEnd().equals(localTime)) {
                shiftUserRepository.deleteById(id);
            } else {
                throw new NotFoundException("ShiftUser does not work in a shift ending at 23:00 UTC");
            }
        });
    }
}
