package com.homework.jdbc.mapper;

import com.homework.jdbc.entities.Shift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class ShiftRowMapper implements RowMapper<Shift> {

    @Override
    public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shift shift = new Shift();
        shift.setId(UUID.fromString(rs.getString("id")));
        shift.setShiftTypeId(UUID.fromString(rs.getString("shiftTypeId")));
        shift.setName(rs.getString("name"));
        shift.setDateStart(new Date(rs.getTimestamp("dateStart").getTime()));
        shift.setDateEnd(new Date(rs.getTimestamp("dateEnd").getTime()));
        shift.setTimeStart(rs.getTime("timeStart"));
        shift.setTimeEnd(rs.getTime("timeEnd"));
        shift.setCreatedAt(new Date(rs.getTimestamp("createdAt").getTime()));
        shift.setUpdatedAt(new Date(rs.getTimestamp("updatedAt").getTime()));
        shift.setTimeZone(rs.getString("timeZone"));
        shift.setTenantId(UUID.fromString(rs.getString("tenantId")));
        return shift;
    }
}
