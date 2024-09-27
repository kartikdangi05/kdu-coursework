package com.homework.jdbc.mapper;

import com.homework.jdbc.entities.ShiftType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class ShiftTypeRowMapper implements RowMapper<ShiftType> {
    @Override
    public ShiftType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShiftType shiftType = new ShiftType();
        shiftType.setShiftTypeName(ShiftType.ShiftTypeName.valueOf(rs.getString("shiftTypeName")));
        shiftType.setId(UUID.fromString(rs.getString("id")));
        shiftType.setDescription(rs.getString("description"));
        shiftType.setActive(rs.getBoolean("active"));
        shiftType.setCreatedAt(new Date(rs.getTimestamp("createdAt").getTime()));
        shiftType.setUpdatedAt(new Date(rs.getTimestamp("updatedAt").getTime()));
        shiftType.setTimeZone(rs.getString("timeZone"));
        shiftType.setTenantId(UUID.fromString(rs.getString("tenantId")));
        return shiftType;
    }
}
