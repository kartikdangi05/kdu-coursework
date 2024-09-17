package com.assessment.two.service;

import com.assessment.two.entity.Users;
import com.assessment.two.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Users findUser(String username){
        return userRepo.findUser(username);
    }

    public Users addUser(Users user){
        return userRepo.save(user);
    }

}
