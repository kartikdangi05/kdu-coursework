package com.handson.security.controller;

import com.handson.security.dto.UserRequestDTO;
import com.handson.security.dto.UserResponseDTO;
import com.handson.security.dto.UserResponseListDTO;
import com.handson.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public ResponseEntity<UserResponseListDTO> getUsers(){
        UserResponseListDTO userResponseListDTO = userService.getUsers();
        return new ResponseEntity<>(userResponseListDTO, HttpStatus.OK);
    }

    @GetMapping("/getUser/{name}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String name){
        UserResponseDTO userResponseDTO = userService.getUserByName(name);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.addUser(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }
}
