package com.handson.security.services;

import com.handson.security.dto.UserRequestDTO;
import com.handson.security.dto.UserResponseDTO;
import com.handson.security.dto.UserResponseListDTO;
import com.handson.security.exceptions.UserListEmptyException;
import com.handson.security.exceptions.UserNotFoundException;
import com.handson.security.models.UserModel;
import com.handson.security.repository.UserRepository;
import com.handson.security.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO){
        UserModel newUserModel = new UserModel(userRequestDTO.getUserName(),userRequestDTO.getPassword(),userRequestDTO.getEmail(),userRequestDTO.getRole());
        userRepository.add(newUserModel);
        return new UserResponseDTO(userRequestDTO.getUserName(),userRequestDTO.getEmail(),userRequestDTO.getRole(),
                "User with username ".concat(userRequestDTO.getUserName()).concat(" is created successfully!"));
    }

    public UserResponseListDTO getUsers(){
        List<UserModel> userModels = userRepository.getAllUsers();
        if(userModels.isEmpty())
            throw new UserListEmptyException("Users list is empty!");
        List<UserResponseDTO> userResponseDTOList = Utils.userToUserDTO(userModels);
        return new UserResponseListDTO(userResponseDTOList,"Fetched users successfully");
    }

    public UserResponseDTO getUserByName(String name){
        UserModel userModel = userRepository.findUser(name);
        if(userModel == null)
            throw new UserNotFoundException("User with name : ".concat(name).concat(" not found!"));
        return new UserResponseDTO(userModel.getUserName(), userModel.getEmail(), userModel.getRole(),"Fetched user successfully!");
    }
}
