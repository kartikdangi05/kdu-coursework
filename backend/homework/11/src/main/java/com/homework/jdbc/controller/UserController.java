package com.homework.jdbc.controller;

import com.homework.jdbc.dto.UserRequestDTO;
import com.homework.jdbc.dto.UserUpdateDTO;
import com.homework.jdbc.entities.User;
import com.homework.jdbc.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDTO userRequestDTO){
        userServices.addUser(userRequestDTO);
        return new ResponseEntity<>("User added successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<User>> findById(@PathVariable String id){
        List<User> users = userServices.findById(id);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserUpdateDTO user){
         userServices.update(user);
         return new ResponseEntity<>("User updated successfully!",HttpStatus.OK);
    }
}
