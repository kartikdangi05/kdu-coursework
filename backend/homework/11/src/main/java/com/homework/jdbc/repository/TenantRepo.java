package com.homework.jdbc.repository;

import com.homework.jdbc.dto.TenantRequestAllDTO;
import com.homework.jdbc.dto.TenantRequestDTO;
import com.homework.jdbc.exceptions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TenantRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ShiftTypeRepo shiftTypeRepo;
    @Autowired
    private ShiftUsersRepo shiftUsersRepo;
    @Autowired
    private ShiftRepo shiftRepo;

    public void add(TenantRequestDTO tenantRequestDTO) {
        String sql = "INSERT INTO tenants (name) VALUES (?)";
        jdbcTemplate.update(sql, tenantRequestDTO.getName());
    }

    @Transactional
    public void addAll(TenantRequestAllDTO tenantRequestAllDTO){
        try{
            userRepo.add(tenantRequestAllDTO.getUserRequestDTO());
            shiftRepo.add(tenantRequestAllDTO.getShiftRequestDTO());
            shiftTypeRepo.add(tenantRequestAllDTO.getShiftTypeRequestDTO());
            shiftUsersRepo.add(tenantRequestAllDTO.getShiftUserRequestDTO());

        }catch (TransactionException ex){
            throw new TransactionException("Transaction failed !");
        }
    }
}
