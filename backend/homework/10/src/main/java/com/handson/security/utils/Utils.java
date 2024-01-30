package com.handson.security.utils;

import com.handson.security.dto.UserResponseDTO;
import com.handson.security.models.UserModel;
import java.util.List;

public class Utils {

    private Utils(){}
    public static List<UserResponseDTO> userToUserDTO(List<UserModel> userModelList){
        return userModelList.stream()
                .map(user -> new UserResponseDTO(user.getUserName(), user.getEmail(), user.getRole(), null))
                .toList();
    }
}
