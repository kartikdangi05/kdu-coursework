package com.homework.jdbc.repository;

import com.homework.jdbc.dto.ShiftUserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShiftUsersRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(ShiftUserRequestDTO shiftUserRequestDTO) {
        String sql = "INSERT INTO shift_user (shift_id, user_id, tenant_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, shiftUserRequestDTO.getShiftId(), shiftUserRequestDTO.getUserId(), shiftUserRequestDTO.getTenantId());
    }
}
