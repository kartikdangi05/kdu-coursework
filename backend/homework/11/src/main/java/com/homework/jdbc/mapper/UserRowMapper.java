package com.homework.jdbc.mapper;

import com.homework.jdbc.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setUsername(rs.getString("username"));
        user.setLoggedIn(rs.getInt("loggedIn"));
        user.setTimeZone(rs.getString("timeZone"));
        user.setTenantId(UUID.fromString(rs.getString("tenantId")));
        user.setCreatedAt(new Date(rs.getTimestamp("createdAt").getTime()));
        user.setUpdatedAt(new Date(rs.getTimestamp("updatedAt").getTime()));
        return user;
    }
}
