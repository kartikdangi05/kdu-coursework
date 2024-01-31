package com.homework.jdbc.controller;

import com.homework.jdbc.dto.TenantRequestAllDTO;
import com.homework.jdbc.dto.TenantRequestDTO;
import com.homework.jdbc.services.TenantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

    @Autowired
    private TenantServices tenantService;

    @PostMapping("/add")
    public ResponseEntity<String> addTenant(@RequestBody TenantRequestDTO tenantRequestDTO) {
        tenantService.addTenant(tenantRequestDTO);
        return new ResponseEntity<>("Tenant added successfully", HttpStatus.OK);
    }

    @PostMapping("/add-in-one-go")
    public ResponseEntity<String> addAll(@RequestBody TenantRequestAllDTO tenantRequestAllDTO){
        tenantService.addAll(tenantRequestAllDTO);
        return new ResponseEntity<>("All entities have been added successfully!",HttpStatus.OK);
    }
}
