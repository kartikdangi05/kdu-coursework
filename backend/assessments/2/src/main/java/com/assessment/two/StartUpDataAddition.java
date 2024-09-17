package com.assessment.two;

import com.assessment.two.entity.Users;
import com.assessment.two.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StartUpDataAddition implements CommandLineRunner {

    UserRepo userRepository;
    PasswordEncoder passwordEncoder;
    Environment environment;

    @Autowired
    public StartUpDataAddition(UserRepo userRepository, PasswordEncoder passwordEncoder, Environment environment){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {

        Users user = new Users();
        user.setAddress(new ArrayList<>());
        user.setCart(null);
        user.setUsername("kartik");
        user.setEmail("wfeh");
        user.setPassword(passwordEncoder.encode("kartik"));
        userRepository.save(user);
    }
}
