package com.handson.jpa.service;

import com.handson.jpa.entity.User;
import com.handson.jpa.exception.NotFoundException;
import com.handson.jpa.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    public void addUser(User user){
        userRepo.save(user);
    }

    public void update(User user){
        if(userRepo.updateUser(user.getUsername(), user.getLoggedIn(), user.getTimeZone(), user.getId()) == 0){
            throw new NotFoundException("User not found!");
        }
    }


    public Page<User> findAllUsers(int pageNumber, int pageSize) {
        pageNumber = Math.max(0, pageNumber);
        pageSize = Math.min(50, Math.max(1, pageSize));

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return userRepo.findAll(pageRequest);
    }
}
