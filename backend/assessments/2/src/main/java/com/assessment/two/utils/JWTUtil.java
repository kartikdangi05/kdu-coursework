package com.assessment.two.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class JWTUtil {

    String username;
    public void addUser(String username){
        this.username = username;
    }

    public String getUser(){
        return this.username;
    }

}
