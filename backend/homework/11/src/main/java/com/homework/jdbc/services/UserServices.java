package com.homework.jdbc.services;

import com.homework.jdbc.dto.UserRequestDTO;
import com.homework.jdbc.dto.UserUpdateDTO;
import com.homework.jdbc.entities.User;
import com.homework.jdbc.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    public void addUser(UserRequestDTO userRequestDTO){
        userRepo.add(userRequestDTO);
    }

    public List<User> findById(String id){
        return userRepo.findById(id);
    }

    public void update(UserUpdateDTO user){
        userRepo.update(user);
    }
}
