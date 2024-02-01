package com.handson.jpa.service;

import com.handson.jpa.dto.UserRequestDTO;
import com.handson.jpa.entity.User;
import com.handson.jpa.exception.NotFoundException;
import com.handson.jpa.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    public void addUser(User user){
        userRepo.save(user);
    }

    public void update(UserRequestDTO user){
        userRepo.updateUser(user.getUsername(),user.getLoggedIn(),user.getTimeZone(),user.getUuid())
                .orElseThrow(() -> new NotFoundException("No such user is available!"));
    }


    public Page<User> findAllUsers(int pageNumber, int pageSize) {
        pageNumber = Math.max(0, pageNumber);
        pageSize = Math.min(50, Math.max(1, pageSize));

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return userRepo.findAll(pageRequest);
    }
}
