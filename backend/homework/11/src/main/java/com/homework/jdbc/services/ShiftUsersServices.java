package com.homework.jdbc.services;

import com.homework.jdbc.dto.ShiftUserRequestDTO;
import com.homework.jdbc.repository.ShiftUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftUsersServices {

    @Autowired
    private ShiftUsersRepo shiftUserRepository;

    public void addShiftUser(ShiftUserRequestDTO shiftUserRequestDTO) {
        shiftUserRepository.add(shiftUserRequestDTO);
    }
}
