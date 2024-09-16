package com.handson.security;

import com.handson.security.models.UserModel;
import com.handson.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartUpDataAddition implements CommandLineRunner {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    Environment environment;

    @Autowired
    public StartUpDataAddition(UserRepository userRepository, PasswordEncoder passwordEncoder, Environment environment){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {

        String rohitPassword = environment.getProperty("rohit.password");
        String ajayPassword = environment.getProperty("ajay.password");
        userRepository.add(new UserModel("Rohit", passwordEncoder.encode(rohitPassword), "s@gmail.com", "ROLE_ADMIN"));
        userRepository.add(new UserModel("Ajay", passwordEncoder.encode(ajayPassword), "a@gmail.com", "ROLE_BASIC"));
    }
}
