package com.homework.jdbc.repository;

import com.homework.jdbc.dto.ShiftRequestDTO;
import com.homework.jdbc.entities.Shift;
import com.homework.jdbc.exceptions.NotFoundException;
import com.homework.jdbc.mapper.ShiftRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShiftRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(ShiftRequestDTO shiftRequestDTO) {
        String sql = "INSERT INTO shifts (name, date_start, date_end, time_start, time_end, time_zone, tenant_id, shift_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, shiftRequestDTO.getName(),
                shiftRequestDTO.getDateStart(), shiftRequestDTO.getDateEnd(), shiftRequestDTO.getTimeStart(),
                shiftRequestDTO.getTimeEnd(), shiftRequestDTO.getTimeZone(),
                shiftRequestDTO.getTenantId(), shiftRequestDTO.getShiftTypeId());
    }

    public List<Shift> findById(String uuid){
        String sql = "SELECT * FROM shifts WHERE tenantId = ?";
        try{
            return jdbcTemplate.query(sql, new ShiftRowMapper(), uuid);
        }catch (NotFoundException ex){
            throw new NotFoundException("No valid shift found");
        }
    }
}
