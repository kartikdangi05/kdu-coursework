package com.handson.jpa.controller;

import com.handson.jpa.entity.User;
import com.handson.jpa.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userServices.addUser(user);
        return new ResponseEntity<>("User added successfully", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user){
         userServices.update(user);
         return new ResponseEntity<>("User updated successfully!",HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public Page<User> findAllUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "50") int pageSize) {
        return userServices.findAllUsers(pageNumber, pageSize);
    }
}
