package com.homework.jdbc.repository;

import com.homework.jdbc.dto.UserRequestDTO;
import com.homework.jdbc.dto.UserUpdateDTO;
import com.homework.jdbc.entities.User;
import com.homework.jdbc.exceptions.NotFoundException;
import com.homework.jdbc.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(UserRequestDTO userRequestDTO) {
        String sql = "INSERT INTO users (username, loggedIn, timeZone, tenantId) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, userRequestDTO.getUsername(), userRequestDTO.getLoggedIn(),
                userRequestDTO.getTimeZone(), userRequestDTO.getTenantId());
    }

    public List<User> findById(String id){
        String sql = "SELECT * FROM users WHERE tenantId = ?";
        try{
            return jdbcTemplate.query(sql, new UserRowMapper(), UUID.fromString(id));
        }catch (NotFoundException ex){
            throw new NotFoundException("No valid User Found!");
        }
    }

    public void update(UserUpdateDTO user){
        String sql = "UPDATE users SET username = ?, loggedIn = ?, timeZone = ? WHERE id = ?";
        try{
            jdbcTemplate.update(sql, user.getUsername(), user.getLoggedIn(), user.getTimeZone(), user.getId());
        }catch (NotFoundException ex){
            throw new NotFoundException("No valid User Found!");
        }
    }
}
