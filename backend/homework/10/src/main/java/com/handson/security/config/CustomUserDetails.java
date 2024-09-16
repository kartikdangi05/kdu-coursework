package com.handson.security.config;

import com.handson.security.models.UserModel;
import com.handson.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findUser(username);
        String personUserName;
        String personPassword;
        List<GrantedAuthority> authorities;

        if (userModel == null) {
            throw new UsernameNotFoundException("User details not found for user: ".concat(username).concat(". Please register first."));
        } else {
            personUserName = userModel.getUserName();
            personPassword = userModel.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(userModel.getRole()));
        }
        return new User(personUserName, personPassword, authorities);
    }
}
