package com.homework.jdbc.services;

import com.homework.jdbc.dto.TenantRequestAllDTO;
import com.homework.jdbc.dto.TenantRequestDTO;
import com.homework.jdbc.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantServices {

    @Autowired
    private TenantRepo tenantRepository;

    public void addTenant(TenantRequestDTO tenantRequestDTO) {
        tenantRepository.add(tenantRequestDTO);
    }

    public void addAll(TenantRequestAllDTO tenantRequestAllDTO){
        tenantRepository.addAll(tenantRequestAllDTO);
    }
}
