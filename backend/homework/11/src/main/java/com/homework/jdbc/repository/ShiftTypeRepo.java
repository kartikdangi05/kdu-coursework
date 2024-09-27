package com.homework.jdbc.repository;

import com.homework.jdbc.dto.ShiftTypeRequestDTO;
import com.homework.jdbc.entities.ShiftType;
import com.homework.jdbc.exceptions.NotFoundException;
import com.homework.jdbc.mapper.ShiftTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShiftTypeRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(ShiftTypeRequestDTO shiftTypeRequestDTO) {
        String sql = "INSERT INTO shift_types (uq_name, description, active, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, shiftTypeRequestDTO.getShiftTypeName().name(), shiftTypeRequestDTO.getDescription(),
                shiftTypeRequestDTO.isActive(),
                shiftTypeRequestDTO.getTimeZone(), shiftTypeRequestDTO.getTenantId());
    }

    public List<ShiftType> findById(String id){
        String sql = "SELECT * FROM shift_types WHERE tenantId = ?";
        try{
            return jdbcTemplate.query(sql, new ShiftTypeRowMapper(), id);
        }catch (NotFoundException ex){
            throw new NotFoundException("No valid shift-type found!");
        }
    }
}
