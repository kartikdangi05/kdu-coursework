package com.handson.jpa.service;

import com.handson.jpa.entity.Tenant;
import com.handson.jpa.repo.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantServices {

    @Autowired
    private TenantRepo tenantRepository;

    public void addTenant(Tenant tenant) {
        tenantRepository.save(tenant);
    }

}
