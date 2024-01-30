package com.handson.security.repository;

import com.handson.security.models.UserModel;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<UserModel> usersList = new ArrayList<>();

    public void add(UserModel userModel){
        usersList.add(userModel);
    }

    public List<UserModel> getAllUsers(){
        return usersList;
    }

    public UserModel findUser(String name){
        for(UserModel userModel : usersList){
            if(userModel.getUserName().equals(name)) return userModel;
        }
        return null;
    }
}
